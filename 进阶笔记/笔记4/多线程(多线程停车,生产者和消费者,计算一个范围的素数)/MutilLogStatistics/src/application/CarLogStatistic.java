package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import module.CarLog;
import tools.FileTools;

public class CarLogStatistic {
	public static void main(String[] args) throws IOException{
		
		FileTools.loadAndParseFile();
		
		
		System.out.println("�Լ���ѧ�ţ�201725010402");
		System.out.println("������������"+FileTools.findTimesOfStoreId("201725010402"));
		System.out.println("�ۼ�ͣ��������"+FileTools.findTimeOfAllCarPark("201725010402"));
		
		
		/*
		HashMap<CarLog, Long> hashCarIdAndStoreId = FileTools.hashCarIdAndStoreId;
		Set<CarLog> keySet = hashCarIdAndStoreId.keySet();
		for (CarLog carLog : keySet) {
			System.out.println(carLog.getCarId()+carLog.getTimeMills());
		}
		
		
		HashMap<String, Long> timesOfCarStoreId = FileTools.timesOfCarStoreId;
		Set<String> keySet2 = timesOfCarStoreId.keySet();
		for (String storeId : keySet2) {
			System.out.println(storeId + hashCarIdAndStoreId.get(storeId));
		}
		
		*/
	}
}
