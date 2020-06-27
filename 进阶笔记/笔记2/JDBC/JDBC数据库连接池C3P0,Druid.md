JDBC数据库连接池C3P0,Druid

	1.C3P0
	2.Druid

c3p0

	1.导入jar包
	2.使用配置文件 c3p0-config.xml 或是 c3p0.properties文件		
	3.创建连接池DataSource对象(自动使用配置文件)
	4.通过DataSource对象，创建连接对象Connection

1.导入jar包：

		c3p0-0.9.5.2.jar
		mchange-commons-java-0.2.12.jar
		mysql-connector-java-8.0.15.jar


2.使用配置文件 c3p0-config.xml 或是 c3p0.properties文件	
		1.c3p0-config.xml	
-	
	位置：
		Day_9
			JdbcMain.java
		c3p0-config.xml
	内容
		<?xml version="1.0" encoding="utf-8"?>
		<c3p0-config>
		  <default-config>
		    <property name="driverClass">com.mysql.cj.jdbc.Driver</property>
		    <property name="jdbcUrl">jdbc:mysql://localhost:3306/db1?useSSL=false&amp;serverTimezone=UTC</property>
		    <property name="user">root</property>
		    <property name="password">123456</property>
		    
		    <property name="initialPoolSize">5</property>
		    <property name="maxPoolSize">10</property>
		    <property name="checkoutTimeout">3000</property>
		  </default-config>
		
		  <named-config name="otherc3p0"> 
		  </named-config>
		</c3p0-config>	
		
		
2.c3p0.properities
-
	位置：
	
		Day_9
			JdbcMain.java
		c3p0.properties
	内容：
	
		c3p0.driverClass = com.mysql.cj.jdbc.Driver
		c3p0.jdbcUrl = jdbc:mysql://localhost:3306/db1?useSSL=false&serverTimezone=UTC
		c3p0.user = root
		c3p0.password = 123456
		c3p0.initialPoolSize = 5
		c3p0.maxPoolSize = 10
		c3p0.checkoutTimeout = 3000

3.创建连接池DataSource对象(自动使用配置文件)
	
	//1.默认xml配置的默认配置信息,"xxx"参数可以使用xml中的其他配置信息(自动)
	DataSource dSource = new ComboPooledDataSource();	
	
	//2.使用properities文件配置（自动）
	DataSource dSource = new ComboPooledDataSource();
		


4.通过DataSource对象，创建连接对象Connection

	Connection conn = dSource.getConnection();


Druid	
 	
	1.导入jar包 
	2.使用配置文件 properities文件，需要创建Properties对象来加载
		Classloader加载转换后的流，该文件要满足格式，
		才能当参数传入后自动使用配置信息内容
	3.使用DruidDataSourceFactory获取连接池对象DataSource，
		接收Properties参数对象，实现自动配置
	4.创建连接对象Connection对象

1.导入jar包 

	druid-1.0.13.jar

2.使用配置文件 properities文件，需要创建Properties对象来加载
	Classloader加载转换后的流，该文件要满足格式，
	才能当参数传入后自动使用配置信息内容

		//创建Properties对象
		Properties properties = new Properties();
		//获取同包的C	lassLoader以用于加载配置文件
		ClassLoader classLoader = Day_1.array_len.class.getClassLoader();
		//使用Classloader加载配置文件并将其转为流
		InputStream inputStream = classLoader.getResourceAsStream("druid.properties");
		//使用properity对象加载 流
		properties.load(inputStream);
		
druid.properties
-
	driverClassName = com.mysql.cj.jdbc.Driver
	url = jdbc:mysql://localhost:3306/db1?useSSL=false&serverTimezone=UTC
	username = root
	password = 123456
	initialSize = 5
	maxActive = 10
	maxWait = 3000
	

3.使用DruidDataSourceFactory获取连接池对象DataSource，
	接收Properties参数对象，实现自动配置
	
	DataSource dSource = DruidDataSourceFactory.createDataSource(properties);
	
4.通过DataSource对象，创建连接对象Connection
		
	Connection conn = dSource.getConnection();

