JDBC连接时使用的类作用及事务

简介DriverManager,Connection,Statement,PreparedStatement
	
DriverManger 
	
	作用1.注册驱动	
	作用2：获取数据库连接对象Connection

	1.注册驱动：使用哪个数据库来驱动jar
		Class.forName("com.mysql.cj.jdbc.Driver");
		1. 因为Driver类下有静态代码块，类加载时通过DriverManager注册Driver
	    DriverManager.registerDriver(new Driver());
		2. 在mysql5之后的版本，jar包中有meta-inf文件，里有注册驱动的语句，因此可以不写
	
	2.获取数据库连接对象Connection
		static Connection getConnection(String url, String user, String password);
		
		url格式：
		 url = "jdbc:mysql://域名:端口号/数据库名称?useSSL=false&serverTimezone=UTC";
		举例：
		 url = "jdbc:mysql://localhost:3306/db1?useSSL=false&serverTimezone=UTC";

Connection
		
	作用1：获取执行sql的Statement,prepareStatement对象
	作用2：管理事务

	1.获取执行sql的Statement,prepareStatement对象
		Statement createStatement()；  
		PreparedStatement prepareStatement(String sql)；

	2. 管理事务
		1. 设置为事务提交方式为手动提交：void setAutoCommit(boolean autoCommit)；  
		2. 提交：void commit()；  
		3. 回滚：void rollback()； 

事务举例说明：
	
	//获取连接对象
	Connection conn = DealMysql.getConnection();

	List<Statement> stmts = new ArrayList<>();
	String sql1 = "update student set score = score - ? where id = ?";
	String sql2 = "update student set score = score + ? where id = ?";
	
	//获取PreparedStatement对象来各自执行sql语句
	PreparedStatement pstmt1 = DealMysql.prepareStatement(conn, sql1);
	PreparedStatement pstmt2 = DealMysql.prepareStatement(conn, sql2);

	stmts.add(pstmt1);
	stmts.add(pstmt2);
	
	try {
		//1.取消conn的自动提交 ->开启事务
		conn.setAutoCommit(false);

		pstmt1.setInt(1, 20);
		pstmt1.setInt(2,2354);
		pstmt2.setInt(1, 20);
		pstmt2.setInt(2,72567);
		pstmt1.executeUpdate();
		pstmt2.executeUpdate();
		
		//2.一切正常运行到了这里就commit提交
		conn.commit();

	} catch (Exception e) {
		e.printStackTrace();
		try {
			//3.一旦有任何的Exception,执行rollback回滚，抓取从开启到提交的异常
			conn.rollback();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}finally {
		
		//释放资源
		DealMysql.close(stmts, conn);
	
	}
	 

Statement
	
  	作用：执行sql语句，并获取返回值
	
	执行sql语句，并获取返回值(execute,executeQuery,executeUpdate)
		1.boolean execute(String sql)； 
			//可执行任何sql语句
		
		2.ResultSet executeQuery(String sql)； 
			//可执行DQL(查询表中数据)语句，返回ResultSet对象，
			具备get数据类型()方法,
				ResultSet对象.getxxx("字段名称") 可获得数据值
				ResultSet对象.getxxx(列名下标) 从1开始,可获得数据值
			具备next()方法，
				将光标从当前位置向前移动一行.(ResultSet光标最初位于第一行之前)
				返回true,已往下一行了,可以读取其内容
				返回false时,光标位于最后一行之后,不可再读取内容
 
		3.int executeUpdate(String sql)；//可执行DDL()    
			//可执行DML(增删改表中数据)或DDL(增删改数据库和表)语句
			//返回值为受影响的行数，>=0 才正常(DDL 结果为0)
		 

PreparedStatement:

	继承于Statement,更高效，使用预编译的sql语句，避免注入问题。
	如果在sql语句中要使用参数，那么就要使用:
			1.拼接:Statement 2.占位:PraparedStatement


1.拼接:Statement 

	String sql =  " select * from student where name = '" + 
				string1 + "' and id = '"+string2 +"'"; 

	//会形成格式 select * from student where id = ' 参数的值 ' 
	//即便是int 型也可以用 单引号'' 所标起来,而不是一定是varchar字符串型	
	//但是会存在注入问题：

	String string2 = "a'" + " or 'a' = 'a";	 某个条件会返回ture值
	String sql =  " select * from student where name = '" + 
			string1 + "' and id = '"+string2 +"'"; 

2.占位:PraparedStatement	
	
	//1.定义sql语句
	String sql = "select * from student where id = ? and name = ? ";
	
	//2.使用connection对象，sql语句作参数传入函数，创建preparedStatement对象
	try {
		PreparedStatement pstmt = conn.prepareStatement(sql);
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
	
	//3.设定占位符的值.set数据类型(占位符下标,值)：下标从1开始计算
		
	pstmt.setString(1, "72567");
	pstmt.setString(2, "伍六七");
	
	//4.执行executeUpdate(),executeQuery()无参函数，
			有sql作参的是其父类Statement的函数
		public interface PreparedStatement extends Statement{
			...
			ResultSet executeQuery();
			int executeUpdate(); 
			...
		}
		 
	pstmt.setString(1, "72567");
	pstmt.setString(2, "伍六七");
	