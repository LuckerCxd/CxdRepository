package tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import module.CarLog;

public class FileTools {
	

	
	public static HashMap<CarLog, Long> hashCarIdAndStoreId = new HashMap<CarLog, Long>();
	public static HashMap<String, Long> timesOfCarStoreId = new HashMap<String, Long>();
	public static HashMap<String, Long> timeOfCarStoreId = new HashMap<String, Long>();
	
	public static long findTimesOfStoreId(String carStoreId) {
		Long long1 = timesOfCarStoreId.get(carStoreId);
		return long1;
	}
	
	public static long findTimeOfAllCarPark(String carStoreId) {
		return timeOfCarStoreId.get(carStoreId);
	}
	
	public static void loadAndParseFile() throws IOException {
		System.out.println("¿ªÊ¼½âÎö");
		BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\cars2.txt"));
	    String tempContent = null;
		while((tempContent = bufferedReader.readLine()  )!= null) {
	    	String[] carLogInformations = tempContent.split(",");
	    	CarLog tempCarLog = new CarLog(carLogInformations[0], carLogInformations[1], 
	    			carLogInformations[2], carLogInformations[3]);
	    	
	    	if(!timesOfCarStoreId.containsKey(tempCarLog.getCarStoreId())) {
				timesOfCarStoreId.put(tempCarLog.getCarStoreId(), (long) 0);
			}
	    	if(!timeOfCarStoreId.containsKey(tempCarLog.getCarStoreId())) {
				timeOfCarStoreId.put(tempCarLog.getCarStoreId(), (long) 0);
			}
	    	if(!hashCarIdAndStoreId.containsKey(tempCarLog)) {
	    		hashCarIdAndStoreId.put(tempCarLog, tempCarLog.getTimeMills());
	    	}else {
	    		if(tempCarLog.getType().equals("out")) {		
	    			
	    			Long long1 = hashCarIdAndStoreId.get(tempCarLog);
	    			long long2 = tempCarLog.getTimeMills();
	    			long value = long2-long1;
	    			//System.out.println(tempCarLog.getCarId()+" long 1 :"+long1+" long2:" + long2+" value:"+value);
					hashCarIdAndStoreId.remove(tempCarLog);
					
					
					
					Long times = timesOfCarStoreId.get(tempCarLog.getCarStoreId())+1;
					timesOfCarStoreId.remove(tempCarLog.getCarStoreId());
					timesOfCarStoreId.put(tempCarLog.getCarStoreId(), times);
					
					Long long3 = timeOfCarStoreId.get(tempCarLog.getCarStoreId());
					timeOfCarStoreId.remove(tempCarLog.getCarStoreId());
					timeOfCarStoreId.put(tempCarLog.getCarStoreId(), long3+value);
					

	    		}
	    	}
		}
	}
}

