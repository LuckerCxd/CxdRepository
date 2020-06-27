package tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;

import module.CarLog;
import module.TimemillsAndTimes;

public class FileTools {
	public static HashMap<String, LinkedList> dateOfMap = new HashMap<String, LinkedList>();
	public static ArrayList<CarLog> allMyStoreCarlog = new ArrayList<CarLog>(5000);
	public static LinkedList<CarLog> crossedCarLog = new LinkedList<CarLog> ();
	public static Date startMillis = Calendar.getInstance().getTime();
	
	
	
	public static ArrayList<CarLog>twoThreadsInCarLogs = new ArrayList<CarLog>();
	public static CopyOnWriteArrayList<CarLog>twoThreadsOutCarLogs = new CopyOnWriteArrayList<CarLog>();
	
	
	public static int outListSize() {
		return twoThreadsOutCarLogs.size();
	}
	
	public static CarLog getIndexOfOutList(int index) {
		return twoThreadsOutCarLogs.get(index);
	}
	
	
	// 双线程分in-out
	public static void loadAndCaclOnTwoThreads() throws IOException {
		System.out.println("Start ReadFile: "+new SimpleDateFormat("HH:mm:ss.SSS").format(startMillis));
		BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\cars2.txt"));
	    String tempContent = null;
		while((tempContent = bufferedReader.readLine()  )!= null) {
	    	String[] carLogInformations = tempContent.split(",");
	    	if("201725010402".equals(carLogInformations[1])) {
	    		if("in".equals(carLogInformations[3])) {
	    			twoThreadsInCarLogs.add(new CarLog(carLogInformations[0], carLogInformations[1], 
			    			carLogInformations[2], carLogInformations[3]));
	    		}else {
	    			twoThreadsOutCarLogs.add(new CarLog(carLogInformations[0], carLogInformations[1], 
			    			carLogInformations[2], carLogInformations[3]));
	    		}
	    	}
		}
		System.out.println("End ReadFile: "+new SimpleDateFormat("HH:mm:ss.SSS").format(Calendar.getInstance().getTime().getTime()));

	}
	
	// 单线程直接扫描一遍,并计算
	public static TimemillsAndTimes loadAndCaclOnSingleThread() throws IOException {
		System.out.println("Start ReadFile: "+new SimpleDateFormat("HH:mm:ss.SSS").format(startMillis));
		BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\cars2.txt"));
	    String tempContent = null;
		while((tempContent = bufferedReader.readLine()  )!= null) {
	    	String[] carLogInformations = tempContent.split(",");
	    	if("201725010402".equals(carLogInformations[1])) {
	    		allMyStoreCarlog.add(new CarLog(carLogInformations[0], carLogInformations[1], 
		    			carLogInformations[2], carLogInformations[3]));
	    	}
		}
		System.out.println("End ReadFile: "+new SimpleDateFormat("HH:mm:ss.SSS").format(Calendar.getInstance().getTime().getTime()));

		int times = 0;
		long timesMills = 0;
		for (int i = 0; i < allMyStoreCarlog.size();) {
			CarLog tempCarLog = allMyStoreCarlog.get(i);
			if("in".equals(tempCarLog.getType())) {
				String carId = tempCarLog.getCarId();
				for(int j=i+1;j<allMyStoreCarlog.size();j++) {
					if(carId.equals(allMyStoreCarlog.get(j).getCarId())) {
						times += 1;
						timesMills = timesMills + allMyStoreCarlog.get(j).getTimeMills()-allMyStoreCarlog.get(i).getTimeMills();
						//System.out.println("  "+carlogLists.get(i).getCarId()+"  "+carlogLists.get(i).getTimeMills() +" /n   "+carlogLists.get(j).getCarId()+"  "+carlogLists.get(j).getTimeMills()
						//		+"  使用"+(carlogLists.get(j).getTimeMills()-carlogLists.get(i).getTimeMills()));
						allMyStoreCarlog.remove(j);
						break;
					}
				}
				allMyStoreCarlog.remove(i);
			}
		}
		
		return new TimemillsAndTimes(timesMills, times);
	}
	
	

	
	// 多线程使用map
	public static void loadAndParseFileOnMutilThreads() throws IOException {
		System.out.println("Start ReadFile: "+new SimpleDateFormat("HH:mm:ss.SSS").format(startMillis));
		BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\cars2.txt"));
	    String tempContent = null;
		while((tempContent = bufferedReader.readLine()  )!= null) {
	    	String[] carLogInformations = tempContent.split(",");
	    	if("201725010402".equals(carLogInformations[1])) {
	    		allMyStoreCarlog.add(new CarLog(carLogInformations[0], carLogInformations[1], 
		    			carLogInformations[2], carLogInformations[3]));
	    	}
		}
		int sizeOfArrayList = allMyStoreCarlog.size();
		for (int i = 0; i < sizeOfArrayList; i++) {
			CarLog tempCarLog = allMyStoreCarlog.get(i);
			String tempDayKey = tempCarLog.getDateyyMMdd();
			if(dateOfMap.containsKey(tempDayKey)){
				dateOfMap.get(tempDayKey).add(tempCarLog);
			}else {
				LinkedList<CarLog> dateOfList = new LinkedList<CarLog>();
				dateOfList.add(tempCarLog);
				dateOfMap.put(tempDayKey,dateOfList);
			}
		}
		System.out.println("End ReadFile: "+new SimpleDateFormat("HH:mm:ss.SSS").format(Calendar.getInstance().getTime().getTime()));
		
	}
	
	// 多线程最后一步公共区计算
	public static TimemillsAndTimes caclCrossCarLogs() throws Exception {
		Collections.sort(crossedCarLog,new CompartorDate());
		long timesMills = 0;
		int  times = 0;
		// 处理一对in-out,每一个in 找第一个匹配的out，
		// 源有序，一定找得到
		// 找到就删除这一对
		// 顺序一定是先in_out
		for (int i = 0; i < crossedCarLog.size();) {
			CarLog tempCarLog = crossedCarLog.get(i);
			if("in".equals(tempCarLog.getType())) {
				String carId = tempCarLog.getCarId();
				for(int j=i+1;j<crossedCarLog.size();j++) {
					if(carId.equals(crossedCarLog.get(j).getCarId())) {
						times += 1;
						timesMills = timesMills + crossedCarLog.get(j).getTimeMills()-crossedCarLog.get(i).getTimeMills();
						//System.out.println("  "+carlogLists.get(i).getCarId()+"  "+carlogLists.get(i).getTimeMills() +" /n   "+carlogLists.get(j).getCarId()+"  "+carlogLists.get(j).getTimeMills()
						//		+"  使用"+(carlogLists.get(j).getTimeMills()-carlogLists.get(i).getTimeMills()));
						crossedCarLog.remove(j);
						break;
					}
				}
				crossedCarLog.remove(i);
			}
		}
		
		return new TimemillsAndTimes(timesMills, times);
	}
	
	public static synchronized void addCarLogToCrossed(CarLog carLog) {
		crossedCarLog.add(carLog);
	}
	
}

