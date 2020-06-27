package cn.cxd.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class MySqlDruid{
	
	private static DataSource dSource;
	
	static {
		try {
			//创建Properties对象
			Properties properties = new Properties();
			//使用同包classloader
			ClassLoader classLoader = MySqlDruid.class.getClassLoader();
			//classloader加载配置文件并转为流
			InputStream inputStream = classLoader.getResourceAsStream("druid.properties");
			//properties对象加载流
			properties.load(inputStream);
			//创建连接池
			
			dSource = DruidDataSourceFactory.createDataSource(properties);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	static public  Connection getConnection() throws SQLException {
		return dSource.getConnection();
	}
	
	
	static public DataSource getDataSource() {
		return dSource;
	}
	
	
	static public void close(ResultSet rs,Statement stmt,Connection conn) {
		close(stmt, conn);
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement stmt,Connection conn) {
		if(conn != null) { //归还连接
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static  void close(List<Statement> stmts,Connection conn) {
		if(conn != null) { //归还连接
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		for(Statement stmt: stmts) {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}