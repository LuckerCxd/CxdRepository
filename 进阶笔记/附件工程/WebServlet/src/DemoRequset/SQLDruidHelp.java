package DemoRequset;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class SQLDruidHelp {
	private static DataSource dSource ;
	static {
		try {
			ClassLoader cLoader = SQLDruidHelp.class.getClassLoader();
			InputStream resourceAsStream = cLoader.getResourceAsStream("druid.properties");
			Properties properties = new Properties();
			properties.load(resourceAsStream);
			dSource =  DruidDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static DataSource getDataSource() {
		return dSource;
	}
}
