package tools;

import java.util.LinkedList;
import java.util.concurrent.Callable;

import module.CarLog;
import module.TimemillsAndTimes;

public class ThreadsForDateCarLog implements Callable<TimemillsAndTimes>{

	private LinkedList<CarLog> carlogLists ;
	
	
	// ʱ�������carlogList
	public ThreadsForDateCarLog(LinkedList<CarLog> carlogLists) {
		this.carlogLists = carlogLists;
	}


	public ThreadsForDateCarLog() {}
	
	// ��ÿһ��map�ж�Ӧ��������ȡ��һ��list,���м���
	@Override
	public TimemillsAndTimes call() throws Exception {
		long timesMills = 0;
		int  times = 0;

		
		boolean isFindOnTheDate = false;
		
		
		// ����һ��in-out,ÿһ��in �ҵ�һ��ƥ���out��
		// Դ�������û���ҵ��ͼ��뵽������,��ɾһ�������
		// �ҵ���ɾ����һ��
		// ���ɨ����out,ֱ�Ӽ��뵽������,��ɾһ������Ϊ����Ӧ�ñ����ѭ��ɨ��
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
						//		+"  ʹ��"+(carlogLists.get(j).getTimeMills()-carlogLists.get(i).getTimeMills()));
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
