package module;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class CarLog {
	private String dateString;
	private String carStoreId;
	private String carId;
	private String type;
	
	
	
	public CarLog(String dateString, String carStoreId, String carId, String type) {
		this.dateString = dateString;
		this.carStoreId = carStoreId;
		this.carId = carId;
		this.type = type;
	}

	
	

	public String getDateString() {
		return dateString;
	}


	public void setDateString(String dateString) {
		this.dateString = dateString;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carId == null) ? 0 : carId.hashCode());
		result = prime * result + ((carStoreId == null) ? 0 : carStoreId.hashCode());
		return result;
	}









	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarLog other = (CarLog) obj;
		if (carId == null) {
			if (other.carId != null)
				return false;
		} else if (!carId.equals(other.carId))
			return false;
		if (carStoreId == null) {
			if (other.carStoreId != null)
				return false;
		} else if (!carStoreId.equals(other.carStoreId))
			return false;
		return true;
	}









	public String getCarStoreId() {
		return carStoreId;
	}


	public void setCarStoreId(String carStoreId) {
		this.carStoreId = carStoreId;
	}


	public String getCarId() {
		return carId;
	}


	public void setCarId(String carId) {
		this.carId = carId;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public long getTimeMills() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
		try {
			date = simpleDateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        long ts = date.getTime();
        return ts/1000;
	}
	
	public String getDateyyMMdd() {
		String[] split = dateString.split(" ");
		return split[0];
	}


	
}
