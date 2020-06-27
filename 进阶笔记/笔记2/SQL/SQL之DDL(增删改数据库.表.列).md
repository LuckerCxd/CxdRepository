	1.SQL:Structured Query Language: 结构化查询语言
	2.SQL语句不区分大小写，关键字建议使用大写
	3. 注释	单行 （--    xxx):有空格
			多行    /* xxx */
	4.分类：
		DDL(Definition):操作数据库和表
		DML(Manipulation):对数据库的表中数据进行增删改
		DQL(Query):对数据库的表中数据进行查
		DCL(Control):基于客户端控制数据库的
	

	
DDL(Definition):操作数据库和表  
 
	1.CRUD数据库
	2.CRUD数据库中的表
	
1.CRUD数据库：

	1.C(create)：创建
		格式：
			create database 数据库名;
		演示：	
			create database if not exists db1 
			create database db1 character set gbk;
			create database if not exists db1 character set gbk;
			-- db1新建的数据库名称   gbk: 字符集编码
	
	2.R(retrieve):查询
		格式+演示：	
			show databases; -- 查询所有数据库名称
			show create database db3； -- 查询db3数据库的字符集编码
			
	3.U(update)：修改
		格式+演示：
			alter database db3 character set utf8; -- 修改db3数据库的字符集编码为utf8
	
	4.D(delete)：删除
		格式+演示：
			drop database if exists db3; -- 如果db3数据库存在,则删除
	
	5.使用数据库	
		格式+演示：
			use db3; -- db3为数据库名称
			select database(); -- 查询正在使用的数据库
			
2.CRUD数据库中的表：
	1.C(create)：创建 

		格式：
			1.创建
				create table 表名 (
					列名1  数据类型，
					列名x 数据类型，
					....
					列名n 数据类型 
				);
			
			2.复制
				create 表名 like 被复制的表名;
				
数据类型：

	表示			类型(参数)							说明
	int        		 整型          							4bytes
	double 			 浮点型(总长度n1，精确到小数后几位n2)   				8bytes 
	date 			yyyy-MM-dd 							3bytes
	datetime 		yyyy-MM-dd HH:mm:ss						8bytes
	varchar    		 字符串(总长度n)							0~65535bytes
	timestamp		yyyy-MM-dd HH:mm:ss   						4bytes
		将timestamp数据类型设定为:TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP 将默认获取系统时间并自动更新										
		演示：	
			create table if not exists student(
				id int(11),
				name varchar(20),
				birthday date,
				score double(3,1)
				);
		
	2.R(retrieve):查询
		格式+演示:
			show tables; -- 查询正在使用的数据库中的所有表名称
			desc password_history -- password_history为正在使用的数据库中的表名称
			show create table student; -- 查询student表的字符编码
	
	3.U(update)：修改
		1.重命名表名：
			alter table 表名 rename to 新的表名;
		2.修改表的字符编码：	
			alter table 表名 character set utf8; 
		3.添加列
			alter table 表名 add 列名 数据类型;
		4.修改列和列的数据类型：(可重复相当于未改动)
			alter table 表名 change 列名  新列名 新数据类型;    (列名和数据类型都改)
			alter table 表名 modify 列名  新数据类型;                (仅改数据类型)
		5.删除列：
			alter table 表名 drop 列名;
	4.D(delete)：删除
		格式+演示：
			drop table if exists 表名; -- 如果student表存在,则删除