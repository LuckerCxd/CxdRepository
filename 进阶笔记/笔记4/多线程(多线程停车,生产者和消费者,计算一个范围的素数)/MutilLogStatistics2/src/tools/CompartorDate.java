package tools;

import java.util.Comparator;

import module.CarLog;

public class CompartorDate implements Comparator<CarLog>{

	@Override
	public int compare(CarLog o1, CarLog o2) {
		if(o1.getTimeMills() > o2.getTimeMills()){
	          return 1;
		} else if(o1.getTimeMills() == o2.getTimeMills()) {
		      return 0;
		}else {
			return -1;
		}
	}
	

}
