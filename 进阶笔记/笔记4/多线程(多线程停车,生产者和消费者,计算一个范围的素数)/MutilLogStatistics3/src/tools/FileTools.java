package tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;

import module.CarLog;
import module.TimemillsAndTimesAndMoney;

public class FileTools {
	public static HashMap<String, ArrayList<CarLog>> monthOfMap = new HashMap<String,  ArrayList<CarLog>>();
	public static ArrayList<String> monthsList  = new ArrayList<String>();
	
	public static Hashtable<String, TimemillsAndTimesAndMoney> resultOfMonths = new Hashtable<>();
	public static ArrayList<CarLog> crossedCarLog = new ArrayList<CarLog> ();
	public static Date startMillis = Calendar.getInstance().getTime();
	
	private static ArrayList<CarLog> crossedInCarLog = new ArrayList<CarLog> ();
	private static ArrayList<CarLog> crossedOutCarLog = new ArrayList<CarLog> ();
	
	// 单-多线程使用map
	public static void loadAndParseFileOnMutilThreads() throws IOException {
		System.out.println("Start ReadFile: "+new SimpleDateFormat("HH:mm:ss.SSS").format(startMillis));
		BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\cars2.txt"));
	    String tempContent = null;
	    CarLog currentCarLog = null;
		while((tempContent = bufferedReader.readLine()  )!= null) {
	    	String[] carLogInformations = tempContent.split(",");
	    	if("201725010402".equals(carLogInformations[1])) {
	    		currentCarLog = new CarLog(carLogInformations[0], carLogInformations[1], 
		    			carLogInformations[2], carLogInformations[3]);
				String currentMonthKey = currentCarLog.getDateyyMM();
				if(monthOfMap.containsKey(currentMonthKey)){
					monthOfMap.get(currentMonthKey).add(currentCarLog);
				}else {
					ArrayList<CarLog>monthOfList = new  ArrayList<CarLog>();
					monthOfList.add(currentCarLog);
					monthOfMap.put(currentMonthKey,monthOfList);
					monthsList.add(currentMonthKey);
				}
	    	}
		}
		System.out.println("End ReadFile: "+new SimpleDateFormat("HH:mm:ss.SSS").format(Calendar.getInstance().getTime().getTime()));
	}
	
	private static void parseInOutLists() {
		int size = crossedCarLog.size();
		CarLog carLog = null;
		for(int i=0;i<size;i++) {
			carLog = crossedCarLog.get(i);
			if("in".equals(carLog.getType())) {
				crossedInCarLog.add(carLog);
			}else {
				crossedOutCarLog.add(carLog);
			}
		}
	}
	
	public static void caclCrossCarLogs() throws Exception {
		Collections.sort(crossedCarLog,new CompartorDate());
		parseInOutLists();
		Iterator<CarLog> inIterator = crossedInCarLog.iterator();
		Iterator<CarLog> outIterator = null;
		CarLog tempInCarLog = null;
		CarLog tempOutCarLog = null;
		while(inIterator.hasNext()) {
			tempInCarLog = inIterator.next();
			outIterator = crossedOutCarLog.iterator();
			while(outIterator.hasNext()) {
				tempOutCarLog = outIterator.next();
				if(tempInCarLog.getCarId().equals(tempOutCarLog.getCarId())) {
					TimemillsAndTimesAndMoney result = resultOfMonths.get(tempOutCarLog.getDateyyMM());
					result.times += 1;
					long timesMills = tempOutCarLog.getTimeMills() - tempInCarLog.getTimeMills();
					result.timesMills += timesMills;
					if(timesMills <= 1800) {
						result.money += 0;
					}else if(timesMills <= 7200) {
						result.money += 10;
					}else {
						int hours = (int) (timesMills/3600);
						result.money = result.money + (hours-2)*2+10;
					}
					outIterator.remove();
					break;
				}
			}
		}
		

	}
		
	
	
}

