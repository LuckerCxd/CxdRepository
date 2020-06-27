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
			//����Properties����
			Properties properties = new Properties();
			//ʹ��ͬ��classloader
			ClassLoader classLoader = MySqlDruid.class.getClassLoader();
			//classloader���������ļ���תΪ��
			InputStream inputStream = classLoader.getResourceAsStream("druid.properties");
			//properties���������
			properties.load(inputStream);
			//�������ӳ�
			
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
		if(conn != null) { //�黹����
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
		if(conn != null) { //�黹����
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