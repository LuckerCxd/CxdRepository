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
	
	
	//DML:改
	@Test
	public void test() throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(MySqlDruid.getDataSource());
		String sql = "update student set score = ? where id = ? ";
		System.out.println(jdbcTemplate.update(sql, 55,2354));
	}
	
	//DML:增
	@Test
	public void test1() throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(MySqlDruid.getDataSource());
		String sql = "insert into student (id,NAME,score) values (?,?,?) ";
		System.out.println(jdbcTemplate.update(sql, 222,"忙k孙",23));
	}
	
	//DML:增
	@Test
	public void test12() throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(MySqlDruid.getDataSource());
		String sql = "create database db4 ";
		System.out.println(jdbcTemplate.update(sql));
	}

	//DML:删
	@Test
	public void test2() throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(MySqlDruid.getDataSource());
		String sql = "DELETE FROM student WHERE id = ?";
		System.out.println(jdbcTemplate.update(sql, 222));
	}
	
	//DQL:查1 QueryForMap集合只能查一个
	@Test
	public void test3() throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(MySqlDruid.getDataSource());
		String sql = "select * from student where id = ? ";
		System.out.println(jdbcTemplate.queryForMap(sql, 72567));
	}
	
	//DQL:查2 QueryForList集合可以查多个,内为map对象
		@Test
		public void test4() throws SQLException {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(MySqlDruid.getDataSource());
			String sql = "select * from student where id = ? ";
			System.out.println(jdbcTemplate.queryForList(sql, 2354));
		}
	
	/*DQL:查3 public <T> List<T> query(String sql,
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
		// 4.定义sql语句
		String sql2 = "select * from student";
		
		ResultSet rs = null;
		Student student = null;
		try {
			rs = stmt.executeQuery(sql2);
			while(rs.next()){
			    // 通过字段检索
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
			// 5.执行sql语句,并处理结果及释放资源
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
	
	
	//静态代码块中进行驱动注册
	static {
		//i.使用Properties集合类
		Properties properties = new Properties();
		
		//ii.使用同级包中任意一个类加载器对配置文件资源加载到程序中并转为流
		ClassLoader classLoader = Day_1.array_len.class.getClassLoader();
		InputStream resourceAsStream = classLoader.getResourceAsStream("jdbc.properities");
		
		try {
			//iii.使用properties对象将该流加载到内存中
			properties.load(resourceAsStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//iv.使用properities对象来获取配置文件的属性，参数和返回值都为String
		driverJar = properties.getProperty("driverJar");
		url = properties.getProperty("url");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		

		// 1. 注册驱动  DriverManager  jar包已导入
		try {
			Class.forName(driverJar);
		} catch (ClassNotFoundException e) {
			System.out.println("驱动注册失败");
			e.printStackTrace();
		}
	
	}
	

	// 2.通过DriverManager 获取Connection对象
	static public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			System.out.println("创建Connection对象失败");
			e.printStackTrace();
		}
		return conn;
	}
	
	//3.通过Connection对象获取用来执行sql语句对象PreparedStatement
	static public PreparedStatement prepareStatement(Connection conn,String sql) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("创建PreparedStatement对象失败");
			e.printStackTrace();
		}
		return pstmt;
	}
	
	//last.释放资源(中途的执行sql要自己做 [定义sql语句,并执行])
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
	
	static public void close(List<Statement> stmts,Connection conn) {
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

class MySqlDruid{
	
	private static DataSource dSource;
	
	static {
		try {
			//创建Properties对象
			Properties properties = new Properties();
			//使用同包classloader
			ClassLoader classLoader = Day_1.array_len.class.getClassLoader();
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

