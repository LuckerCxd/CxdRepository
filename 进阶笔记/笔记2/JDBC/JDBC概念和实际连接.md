JDBC概念和实际连接
	
	概念：(Java database connect)
	是一种用于执行SQL语句的Java API，可以为多种关系数据库提供统一访问，
	它由一组用Java语言编写的类和接口组成。JDBC提供了一种基准，
	据此可以构建更高级的工具和接口，使数据库开发人员能够编写数据库应用程序。
	JDBC可做三件事：与数据库建立连接、发送操作数据库的语句并处理结果。

实现连接并进行操作数据库（Eclipse,mysql-8.0.16）
	
	1.在项目导入包：mysql-connector-java-8.0.16.jar	
	2.注册驱动
	3.通过DriverManager类获取数据库连接对象Connection对象
	4.定义字符串sql语句
	5.通过Connection对象获取用来执行sql语句对象Statement,或是Prepared对象(conn,sql)
	6.执行sql语句
	7.对执行结果进行处理
	8.释放资源


1.在项目导入包：mysql-connector-java-8.0.16.jar

	0.下载jar包：
	https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.16/mysql-connector-java-8.0.16.jar
	注意：这个包的版本要和mysql版本对应上
	1.在项目下新建libs包,将jar包复制进去
	2.右键项目 -> buid path -> configure build path -> libraries -> add jars -> 包中的jar包 -> apply
	
2.注册驱动

	Class.forName("com.mysql.cj.jdbc.Driver");
	
3.通过DriverManager(驱动管理)类获取数据库连接对象Connection对象

	final String  url = "jdbc:mysql://localhost:3306/db1?useSSL=false&serverTimezone=UTC";  
	// jdbc:mysql://localhost:3306/数据库名称?useSSL=false&serverTimezone=UTC";


	final String  url = "jdbc:mysql://localhost:3306/db1?useSSL=false&serverTimezone=UTC";   
	final String user = "root";
	final String password = "123456";
	Connection conn = DriverManager.getConnection(url,user,password);
	
4.定义字符串sql语句

	String sql = "SELECT * FROM student";

5.通过Connection对象获取用来执行sql语句对象Statement,或是Prepared对象(conn,sql)
		
	Statement stmt = conn.createStatement();

6.执行sql语句（这里的sql语句是查询，因此使用executeQuery()）

	ResultSet rs = stmt.executeQuery(sql);
		
7.对执行结果进行处理（student表 结构）
	
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

![](student.jpg)
	
8.释放资源
	
	//在try_catch_finally中finally块中释放，释放前判断不为null,才释放:避免空指针异常
	
	  rs.close();
      stmt.close();
      conn.close();
		
	





		