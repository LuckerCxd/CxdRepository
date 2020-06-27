package Day_9;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.xdevapi.PreparableStatement;


public class JdbcMain {
	public static void main (String[] args)  throws Exception {	
		
		
	}
	
	
	//DML:��
	@Test
	public void test() throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(MySqlDruid.getDataSource());
		String sql = "update student set score = ? where id = ? ";
		System.out.println(jdbcTemplate.update(sql, 55,2354));
	}
	
	//DML:��
	@Test
	public void test1() throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(MySqlDruid.getDataSource());
		String sql = "insert into student (id,NAME,score) values (?,?,?) ";
		System.out.println(jdbcTemplate.update(sql, 222,"æk��",23));
	}
	
	//DML:��
	@Test
	public void test12() throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(MySqlDruid.getDataSource());
		String sql = "create database db4 ";
		System.out.println(jdbcTemplate.update(sql));
	}

	//DML:ɾ
	@Test
	public void test2() throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(MySqlDruid.getDataSource());
		String sql = "DELETE FROM student WHERE id = ?";
		System.out.println(jdbcTemplate.update(sql, 222));
	}
	
	//DQL:��1 QueryForMap����ֻ�ܲ�һ��
	@Test
	public void test3() throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(MySqlDruid.getDataSource());
		String sql = "select * from student where id = ? ";
		System.out.println(jdbcTemplate.queryForMap(sql, 72567));
	}
	
	//DQL:��2 QueryForList���Ͽ��Բ���,��Ϊmap����
		@Test
		public void test4() throws SQLException {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(MySqlDruid.getDataSource());
			String sql = "select * from student where id = ? ";
			System.out.println(jdbcTemplate.queryForList(sql, 2354));
		}
	
	/*DQL:��3 public <T> List<T> query(String sql,
        RowMapper<T> rowMapper,
        @Nullable
        Object... args)*/
		@Test
		public void test5() throws SQLException {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(MySqlDruid.getDataSource());
			String sql = "select * from student where id = ? ";
			List<Student> query = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Student>(Student.class),2354);
			for(Student s:query) {
				System.out.println(s);
			}
		}	
		
		@Test
		public void test6() throws SQLException {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(MySqlDruid.getDataSource());
			String sql = "select count(id) from student where id = ?";
			System.out.println(jdbcTemplate.queryForObject(sql, int.class,2354));
		}
		
		
		
	public List<Student> addAllStudents(Statement stmt,Connection conn){
		List<Student> students = new ArrayList<Student>();
		// 4.����sql���
		String sql2 = "select * from student";
		
		ResultSet rs = null;
		Student student = null;
		try {
			rs = stmt.executeQuery(sql2);
			while(rs.next()){
			    // ͨ���ֶμ���
			  int id  = rs.getInt("id");
			  String name = rs.getString("name");
			  String master= rs.getString("master");
			  String className = rs.getString("class");
			  String subject = rs.getString("subject");
			  int score = rs.getInt("score");
			  
			  student = new Student();
			  student.setId(id);
			  student.setClassName(className);
			  student.setMaster(master);
			  student.setName(name);
			  student.setSubject(subject);
			  student.setScore(score);
			  
			  students.add(student);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// 5.ִ��sql���,�����������ͷ���Դ
			DealMysql.close(rs, stmt,conn);
		}
		return students;
	}
	
	
}

class Student{
	private Integer id;
	private String name;
	private String className;
	private String master;
	private String subject;
	private int score;
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", className=" + className + ", master=" + master + ", subject="
				+ subject + ", score=" + score + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
		
}

class DealMysql{
	private static String driverJar;
	private static String user;
	private static String password;
	private static String url;
	
	
	//��̬������н�������ע��
	static {
		//i.ʹ��Properties������
		Properties properties = new Properties();
		
		//ii.ʹ��ͬ����������һ����������������ļ���Դ���ص������в�תΪ��
		ClassLoader classLoader = Day_1.array_len.class.getClassLoader();
		InputStream resourceAsStream = classLoader.getResourceAsStream("jdbc.properities");
		
		try {
			//iii.ʹ��properties���󽫸������ص��ڴ���
			properties.load(resourceAsStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//iv.ʹ��properities��������ȡ�����ļ������ԣ������ͷ���ֵ��ΪString
		driverJar = properties.getProperty("driverJar");
		url = properties.getProperty("url");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		

		// 1. ע������  DriverManager  jar���ѵ���
		try {
			Class.forName(driverJar);
		} catch (ClassNotFoundException e) {
			System.out.println("����ע��ʧ��");
			e.printStackTrace();
		}
	
	}
	

	// 2.ͨ��DriverManager ��ȡConnection����
	static public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			System.out.println("����Connection����ʧ��");
			e.printStackTrace();
		}
		return conn;
	}
	
	//3.ͨ��Connection�����ȡ����ִ��sql������PreparedStatement
	static public PreparedStatement prepareStatement(Connection conn,String sql) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("����PreparedStatement����ʧ��");
			e.printStackTrace();
		}
		return pstmt;
	}
	
	//last.�ͷ���Դ(��;��ִ��sqlҪ�Լ��� [����sql���,��ִ��])
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
	
	static public void close(Statement stmt,Connection conn) {
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
	
	static public void close(List<Statement> stmts,Connection conn) {
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

class MySqlDruid{
	
	private static DataSource dSource;
	
	static {
		try {
			//����Properties����
			Properties properties = new Properties();
			//ʹ��ͬ��classloader
			ClassLoader classLoader = Day_1.array_len.class.getClassLoader();
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

