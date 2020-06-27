package cn.cxd.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.apache.struts2.util.StrutsTypeConverter;

public class DateConvertor2 extends StrutsTypeConverter{

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if(values != null && values.length > 0 ) {
			String date = values[0];
			if("".equals(date)) 
				return null;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
			try {
				return simpleDateFormat.parse(date);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}

	@Override
	public String convertToString(Map context, Object o) {
		return null;
	}

}
