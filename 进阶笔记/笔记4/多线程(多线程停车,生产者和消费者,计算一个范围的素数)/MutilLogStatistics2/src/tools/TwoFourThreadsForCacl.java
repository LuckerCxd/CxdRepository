package tools;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

import module.CarLog;
import module.TimemillsAndTimes;

public class TwoFourThreadsForCacl implements Callable<TimemillsAndTimes>{
	
	private int startIndex;
	private int step;
	public static CyclicBarrier cyclicBarrier ;
	
	public TwoFourThreadsForCacl() {}
	public TwoFourThreadsForCacl(int startIndex,int step) {
		this.startIndex = startIndex;
		this.step = step;
	}
	
	// 使用in,查找out,适当删out,使用步长step
	@Override
	public TimemillsAndTimes call() throws Exception {
		long timesMills = 0;
		int  times = 0;
		int sizeOfInList = FileTools.twoThreadsInCarLogs.size();
		
		for (int i = startIndex; i < sizeOfInList; i += step) {
			CarLog tempCarLog = FileTools.twoThreadsInCarLogs.get(i);
			String carId = tempCarLog.getCarId();
//			System.out.println("i = "+i);
			int sizeOfOutList = FileTools.outListSize();
			for(int j=0;j<sizeOfOutList;j++) {
//				System.out.println("j:"+j);
				CarLog carLog = FileTools.getIndexOfOutList(j);
				if(carId.equals(carLog.getCarId())) {
						times += 1;
						timesMills = timesMills + FileTools.getIndexOfOutList(j).getTimeMills()-tempCarLog.getTimeMills();
						FileTools.twoThreadsOutCarLogs.remove(carLog);
						break;
						
				}
			}
		}
		
		return new TimemillsAndTimes(timesMills, times);
	}
}
