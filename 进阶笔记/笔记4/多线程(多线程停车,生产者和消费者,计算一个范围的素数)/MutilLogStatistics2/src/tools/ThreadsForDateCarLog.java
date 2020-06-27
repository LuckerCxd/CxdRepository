package tools;

import java.util.LinkedList;
import java.util.concurrent.Callable;

import module.CarLog;
import module.TimemillsAndTimes;

public class ThreadsForDateCarLog implements Callable<TimemillsAndTimes>{

	private LinkedList<CarLog> carlogLists ;
	
	
	// 时间有序的carlogList
	public ThreadsForDateCarLog(LinkedList<CarLog> carlogLists) {
		this.carlogLists = carlogLists;
	}


	public ThreadsForDateCarLog() {}
	
	// 从每一个map中对应的日期中取出一个list,进行计算
	@Override
	public TimemillsAndTimes call() throws Exception {
		long timesMills = 0;
		int  times = 0;

		
		boolean isFindOnTheDate = false;
		
		
		// 处理一对in-out,每一个in 找第一个匹配的out，
		// 源有序，如果没有找到就加入到公共区,单删一个，如果
		// 找到就删除这一对
		// 如果扫描是out,直接加入到公共区,单删一个，因为它不应该被外层循环扫到
		for (int i = 0; i < carlogLists.size();) {
			isFindOnTheDate = false;
			CarLog tempCarLog = carlogLists.get(i);
			
			if("in".equals(tempCarLog.getType())) {
				String carId = tempCarLog.getCarId();
				for(int j=i+1;j<carlogLists.size();j++) {
					if(carId.equals(carlogLists.get(j).getCarId())) {
						times += 1;
						timesMills = timesMills + carlogLists.get(j).getTimeMills()-carlogLists.get(i).getTimeMills();
						isFindOnTheDate = true;
						//System.out.println("  "+carlogLists.get(i).getCarId()+"  "+carlogLists.get(i).getTimeMills() +" /n   "+carlogLists.get(j).getCarId()+"  "+carlogLists.get(j).getTimeMills()
						//		+"  使用"+(carlogLists.get(j).getTimeMills()-carlogLists.get(i).getTimeMills()));
						carlogLists.remove(j);
						
						break;
					}
				}
				if(!isFindOnTheDate) {
					FileTools.addCarLogToCrossed(tempCarLog);
				}
				carlogLists.remove(i);
			}else {
				FileTools.addCarLogToCrossed(tempCarLog);
				carlogLists.remove(i);
			}
		}
		
		return new TimemillsAndTimes(timesMills, times);
	}
	
}
