Druid工具类：

	0.定义静态成员变量：DataSource
	1.定义静态代码块，加载配置文件，初始化连接池对象
	2.定义方法： 
		1.获取连接对象
		2.释放连接
		3.获取连接池方法
	3.使用工具类,需要自己定义sql，执行sql并处理结果


0.定义静态成员变量：DataSource

	private static DataSource dSource;

1.定义静态代码块，加载配置文件，初始化连接池对象
	
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
	


2.定义静态方法： 
	1.获取连接对象
	2.释放连接
	3.获取连接池方法

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

3.使用工具类,在获取连接后，需要自己定义sql，执行sql并处理结果以及释放资源,归还连接
	
	Connection conn = MySqlDruid.getConnection();
	String sql = "update student set score = ? where id = ? ";
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setInt(1, 99);
	pstmt.setInt(2,2354);
	System.out.println(pstmt.executeUpdate() +"行受影响");
	MySqlDruid.close(pstmt, conn);
	
工具类完整代码：
	
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
	