package testthisdatelog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.junit.Test;

import module.CarLog;
import module.TimemillsAndTimes;
import tools.CompartorDate;
import tools.FileTools;
import tools.ThreadsForDateCarLog;

public class TestSingleThreadForCalu {
	@Test
	public void result() throws Exception{
		String testDate = new String();
		FileTools.loadAndParseFileOnMutilThreads();
		Set<String> keySet = FileTools.dateOfMap.keySet();
		int count = 1;
		for (String string : keySet) {
			if(count == 2) {
				testDate = string;
				break;
			}
			count ++;
		}
		ThreadsForDateCarLog t1 = new ThreadsForDateCarLog(FileTools.dateOfMap.get(testDate));
		FutureTask<TimemillsAndTimes> futureTask = new FutureTask<>(t1);
		new Thread(futureTask).start();
		TimemillsAndTimes timemillsAndTimes = futureTask.get();
		System.out.println("进出 "+timemillsAndTimes.times+"  次,共花费了"+timemillsAndTimes.timesMills+ " s");
//		LinkedList<CarLog> crossedCarLog = FileTools.crossedCarLog;
//		for (CarLog carLog : crossedCarLog) {
//			System.out.println(carLog.getCarId() + " "+ carLog.getDateString() + " "+carLog.getType());
//		}
	
	}
	
	@Test
	public void allExecute() throws Exception{
		FileTools.loadAndParseFileOnMutilThreads();
		Set<String> keySet = FileTools.dateOfMap.keySet();
		ArrayList<FutureTask> futureTasks = new ArrayList<FutureTask>();
		ThreadsForDateCarLog t1;
		
		int e=1;
		for (String dateString : keySet) {
			t1 = new ThreadsForDateCarLog(FileTools.dateOfMap.get(dateString));
			FutureTask<TimemillsAndTimes> futureTask = new FutureTask<TimemillsAndTimes>(t1);
			Thread thread = new Thread(futureTask);
			futureTasks.add(futureTask);
			
			/*
			
			if(e == 176) {
				System.out.println("为0？"+dateString);
				LinkedList<CarLog> linkedList = FileTools.dateOfMap.get(dateString);
				for(int i = 0;i<linkedList.size();i++) {
					CarLog carLog = linkedList.get(i);
					System.out.println(carLog.getCarId() + " " + carLog.getDateString() + " "+carLog.getType());
				}
			}
			e++;
			
			*/
			
			thread.start();
			
		}
		
		int resultTimes = 0;
		long resultTimesMills = 0;
		for(int i=0;i<futureTasks.size();i++) {
			TimemillsAndTimes timemillsAndTimes = (TimemillsAndTimes) futureTasks.get(i).get();
			
			resultTimes += timemillsAndTimes.times;
			resultTimesMills += timemillsAndTimes.timesMills;
			//System.out.println("进出 "+timemillsAndTimes.times+"  次,共花费了"+timemillsAndTimes.timesMills+ " s");
			
		}
		
		
//		LinkedList<CarLog> crossedCarLog = FileTools.crossedCarLog;
//		System.out.println(crossedCarLog);
//		Collections.sort(crossedCarLog,new CompartorDate());
//		for (CarLog carLog : crossedCarLog) {
//			System.out.println(carLog.getCarId() + " "+ carLog.getDateString() + " "+carLog.getType());
//		}
		
		
		TimemillsAndTimes caclCrossCarLogs = FileTools.caclCrossCarLogs();
		resultTimes += caclCrossCarLogs.times;
		resultTimesMills += caclCrossCarLogs.timesMills;
		System.out.println("进出 "+resultTimes+"  次,共花费了"+resultTimesMills+ " s");

		
//		LinkedList<CarLog> crossedCarLog = FileTools.crossedCarLog;
//		for (CarLog carLog : crossedCarLog) {
//			System.out.println(carLog.getCarId() + " "+ carLog.getDateString() + " "+carLog.getType());
//		}
	
	}
}
