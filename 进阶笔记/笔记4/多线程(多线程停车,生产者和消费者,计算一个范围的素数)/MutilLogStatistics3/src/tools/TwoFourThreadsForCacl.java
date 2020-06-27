package tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;

import module.CarLog;
import module.TimemillsAndTimesAndMoney;

public class TwoFourThreadsForCacl extends Thread{
	
	private ArrayList<CarLog> monthList = new ArrayList<>();
	private ArrayList<CarLog> inOfMonth = new ArrayList<>();
	private ArrayList<CarLog> outOfMonth = new ArrayList<>();
	
	private int indexOfMonths = 0;
	private int stepOfMonths = 0;
	
	
	
	public TwoFourThreadsForCacl(int indexOfMonths, int stepOfMonths) {
		this.indexOfMonths =indexOfMonths;
		this.stepOfMonths = stepOfMonths;
	}

	private void parseInOutLists() {
		inOfMonth.clear();
		outOfMonth.clear();
		int size = monthList.size();
		CarLog carLog = null;
		for(int i=0;i<size;i++) {
			carLog = monthList.get(i);
			if("in".equals(carLog.getType())) {
				inOfMonth.add(carLog);
			}else {
				outOfMonth.add(carLog);
			}
		}
	}
	
	@Override
	public void run() {
		int monthsSize = FileTools.monthsList.size();
		while(indexOfMonths < monthsSize) {
			String monthString = FileTools.monthsList.get(indexOfMonths);
			this.monthList = FileTools.monthOfMap.get(monthString);
			parseInOutLists();
			
			Iterator<CarLog> inIterator = inOfMonth.iterator();
			Iterator<CarLog> outIterator = null;
			CarLog tempInCarLog = null;
			CarLog tempOutCarLog = null;
			boolean hadFound = false;
			int times = 0;
			long timesMills = 0;
			long money = 0;
			while(inIterator.hasNext()) {
				tempInCarLog = inIterator.next();
				outIterator = outOfMonth.iterator();
				hadFound = false;
				while(outIterator.hasNext()) {
					tempOutCarLog = outIterator.next();
					if(tempInCarLog.getCarId().equals(tempOutCarLog.getCarId())) {
						times += 1;
						long tempTimeMills =  tempOutCarLog.getTimeMills() - tempInCarLog.getTimeMills();
						timesMills = timesMills + tempTimeMills;
						if(tempTimeMills <= 1800) {
							money += 0;
						}else if(tempTimeMills <= 7200) {
							money += 10;
						}else {
							int hours = (int) (tempTimeMills/3600);
							money = money + (hours-2)*2+10;
						}
						outIterator.remove();
						hadFound = true;
						break;
					}
				}
				if(hadFound) {
					inIterator.remove();
				}
			}
			
			FileTools.resultOfMonths.put(monthString, new TimemillsAndTimesAndMoney(timesMills, times,money));
			// 未能成功配对的加入公共区
			int inSize = inOfMonth.size();
			for(int i=0;i<inSize;i++) {
				FileTools.crossedCarLog.add(inOfMonth.get(i));
			}
			int outSize = outOfMonth.size();
			for(int i=0;i<outSize;i++) {
				FileTools.crossedCarLog.add(outOfMonth.get(i));
			}
			indexOfMonths += stepOfMonths;
		}
		
	}

}
