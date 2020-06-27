package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import module.CarLog;
import module.TimemillsAndTimes;
import tools.FileTools;
import tools.ThreadsForDateCarLog;
import tools.TwoFourThreadsForCacl;

public class CarLogStatistic {
	public static void main(String[] args) throws IOException{
		
		/* 多线程 
		FileTools.loadAndParseFileOnMutilThreads();
		Set<String> keySet = FileTools.dateOfMap.keySet();
		ArrayList<FutureTask> futureTasks = new ArrayList<FutureTask>();
		ThreadsForDateCarLog t1;
		
		
		for (String dateString : keySet) {
			t1 = new ThreadsForDateCarLog(FileTools.dateOfMap.get(dateString));
			FutureTask<TimemillsAndTimes> futureTask = new FutureTask<TimemillsAndTimes>(t1);
			Thread thread = new Thread(futureTask);
			futureTasks.add(futureTask);
			
			thread.start();
			
		}
		
		int resultTimes = 0;
		long resultTimesMills = 0;
		for(int i=0;i<futureTasks.size();i++) {
			TimemillsAndTimes timemillsAndTimes = null;
			try {
				timemillsAndTimes = (TimemillsAndTimes) futureTasks.get(i).get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			
			resultTimes += timemillsAndTimes.times;
			resultTimesMills += timemillsAndTimes.timesMills;
			
		}
		
		

		
		TimemillsAndTimes caclCrossCarLogs = null;
		try {
			caclCrossCarLogs = FileTools.caclCrossCarLogs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultTimes += caclCrossCarLogs.times;
		resultTimesMills += caclCrossCarLogs.timesMills;
		System.out.println("进出 "+resultTimes+"  次,共花费了"+resultTimesMills/1000+ " s");
		System.out.println("End Process: "+new SimpleDateFormat("HH:mm:ss.SSS").format(Calendar.getInstance().getTime().getTime()));
		
		
		*/
		
		
		/* 单线程
		 
		TimemillsAndTimes timemillsAndTimes = FileTools.loadAndCaclOnSingleThread();
		System.out.println("进出 "+timemillsAndTimes.times+"  次,共花费了"+timemillsAndTimes.timesMills/1000+ " s");
		System.out.println("End Process: "+new SimpleDateFormat("HH:mm:ss.SSS").format(Calendar.getInstance().getTime().getTime()));
		*/
		
		/*
		 * 双线程
		 * 
		 /
		
		
	
	
	*/
		FileTools.loadAndCaclOnTwoThreads();
		
		ArrayList<FutureTask> futureTasks = new ArrayList<FutureTask>();
		TwoFourThreadsForCacl t1;
		
		int step = 2;
		for (int i=0;i<step;i++) {
			t1 = new TwoFourThreadsForCacl(i,step);
			FutureTask<TimemillsAndTimes> futureTask = new FutureTask<TimemillsAndTimes>(t1);
			Thread thread = new Thread(futureTask);
			futureTasks.add(futureTask);
			thread.start();
			
		}
//		
		
		int resultTimes = 0;
		long resultTimesMills = 0;
		for(int i=0;i<futureTasks.size();i++) {
			TimemillsAndTimes timemillsAndTimes = null;
			try {
				timemillsAndTimes = (TimemillsAndTimes) futureTasks.get(i).get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			
			resultTimes += timemillsAndTimes.times;
			resultTimesMills += timemillsAndTimes.timesMills;
		
		}
		
		System.out.println("进出 "+resultTimes+"  次,共花费了"+resultTimesMills+ " s");
		System.out.println("End Process: "+new SimpleDateFormat("HH:mm:ss.SSS").format(Calendar.getInstance().getTime().getTime()));

		
	}
}
