package application;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

import module.TimemillsAndTimesAndMoney;
import tools.FileTools;
import tools.TwoFourThreadsForCacl;

public class Main {
	

	public static void main(String[] args) {
		try {
			FileTools.loadAndParseFileOnMutilThreads();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int threadNums = 2;
		int step = 2;
		Thread[] threads = new Thread[threadNums];
		for(int i=0;i<threadNums;i++) {
			threads[i] = new TwoFourThreadsForCacl(i, step);
			threads[i].start();
		}
		
		for(int i=0;i<threadNums;i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			FileTools.caclCrossCarLogs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TimemillsAndTimesAndMoney tempResult = null;
		for(String month:FileTools.monthsList) {
			tempResult = FileTools.resultOfMonths.get(month);
			System.out.println(month + "  "+tempResult.money+" ิช "+tempResult.times+" ดฮ");
		}
		System.out.println("End Process: "+new SimpleDateFormat("HH:mm:ss.SSS").format(Calendar.getInstance().getTime().getTime()));
	
	}
		
		
}
