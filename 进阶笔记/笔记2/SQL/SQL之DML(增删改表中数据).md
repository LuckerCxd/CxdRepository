DML(Manipulation):对数据库的表中数据进行增删改

	1.添加数据
		insert into 表名(列名1,列名2,...列名n) values (值1,值2,...值n);  
								--选定特定列进行设置,并将该条数据添加进入表
		insert into 表名 values (所有列的对应值);						
								--直接对全部进行初始化,并将该条数据添加进入表
	2.删除数据
		delete from 表名 where 条件s;  --  将满足条件的数据项从表中删除,条件间可以使用and,or联合
		delete from 表名;		   -- 删除所有表中数据
		truncate table 表名;		   -- 删除所有表中数据，并创建一个完全一致的空表
		
		举例：						
			DELETE FROM stu WHERE name != "米" ;
			DELETE FROM stu WHERE name = "米其林" and id = 1;
			TRUNCATE TABLE stu;
	
	3.修改数据
		update 表名 set 列名1 = 值1 , 列名2 = 值2,...列名n = 值n where 条件s;
			-- 将满足条件s的数据项，修改其对应的列名下的值,条件间可以使用and,or联合
		update 表名 set 	列名1 = 值1 , 列名2 = 值2,...列名n = 值n;
			-- 将表中所有数据修改其对应的列名下的值,条件间可以使用and,or联合
		举例：	
			UPDATE stu SET id = 4,name = "Hello" WHERE NAME = "米其林" OR id = 3;	
								
								