JDBC工具类协同配置文件

	1.在包的同级创建File,作为配置文件
	2.在配置文件中定义driverJar,url,password,user
	3.定义工具类，设置静态成员变量driverJar,user,url,password,
		并设置静态代码块，用于加载配置文件，并进行驱动注册，
		使注册驱动行为在使用类方法(getConnection())使仅被加载一次
	4.为工具类定义类方法getConnection()，prepareStatement()，close()
	5.使用工具类需要自己处理sql语句,并传给prepareStatement()执行和执行结果

1.在包的同级创建File,作为配置文件

	Day_9
		JdbcMain.java
	jdbc.properities
	
2.在配置文件中定义driverJar,url,password,user

	1.用于Class.forName(xxx)注册驱动的driverJar
	2.用于DriverManager.getConnection(xxx)的url user password

	driverJar = com.mysql.cj.jdbc.Driver
	url = jdbc:mysql://localhost:3306/db1?useSSL=false&serverTimezone=UTC
	user = root
	password = 123456

3.定义工具类，设置静态成员变量driverJar,user,url,password,
	并设置静态代码块，用于加载配置文件，并进行驱动注册，
	使注册驱动行为在使用类方法(getConnection())使仅被加载一次
	
	在static静态代码块中：
		1.创建Properities对象
		2.使用同级包中任意一个类加载器对配置文件资源加载到程序中并转为流
		3.使用properties对象将该流加载到内存中
		4.使用properities对象通过传入字符串来获取配置文件的所有属性值并复制给
			对应的静态成员变量driverJar,url,password,user
		5.通过获取的driverJar进行注册驱动
	

//静态代码块中进行驱动注册
	
	static {
		//i.创建Properities对象
		Properties properties = new Properties();
		
		//ii.使用同级包中任意一个类加载器对配置文件资源加载到程序中并转为流
		ClassLoader classLoader = Day_1.Dog.class.getClassLoader();
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
		

4.为工具类定义类方法getConnection()，prepareStatement()，close()

	getConnection():内使用静态代码块获取的url,user,password传入参数
		获取Connection对象

	createStatement(Connection xx):传入Connection变量参数
		获取执行sql的Statement对象

	close:
		1.close(ResultSet rs,Statement stmt,Connection conn)
			用于返回值为ResultSet的Statement.executeQuery(String sql)
	  	2.close(Statement stmt,Connection conn)
				用于返回值为int的Statement.executeUpdate(String sql)
		为了避免空指针异常，每一个资源的释放，都要先做一个null判断，再进行释放	
			

// 通过DriverManager 获取Connection对象

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

//通过Connection对象获取用来执行sql语句对象PreparedStatement

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
		if(conn != null) {
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

5.使用工具类需要自己处理sql语句和执行结果
	
	//使用工具类获取Connection对象
	Connection conn = DealMysql.getConnection();

	//定义sql语句
	String sql2 = "select * from student";
	ResultSet rs = null;
	
	//使用工具类获取PreparedStatement对象
	PreparedStatement pstmt = DealMysql.prepareStatement(conn, sql);

	//处理执行结果
	try {
		rs = pstmt.executeQuery();
		while(rs.next()){
		    // 通过字段检索
		    int id  = rs.getInt("id");
		    String name = rs.getString("name");
		    String  master= rs.getString("master");

		    // 输出数据
		    System.out.print("id: " + id);
		    System.out.print(", name " + name);
		    System.out.print(", master: " + master);
		    System.out.print("\n");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {

		//资源释放
		DealMysql.close(rs, stmt,conn);
	}

工具类完整代码：
	
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
			if(conn != null) {
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
	}