package testthisdatelog;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import module.CarLog;
import tools.FileTools;

public class TestSingleThreadForCalu {

	
	@Test
	public void test() throws Exception {
		CarLog log = new CarLog("2018-01-02 01:23:39", "2018001", "ิมG3T391", "in");
		CarLog log2 = new CarLog("2018-01-02 02:23:39", "2018001", "ิมG3T391", "in");
		System.out.println(log.getTimeMills()-log2.getTimeMills());
	}
}
