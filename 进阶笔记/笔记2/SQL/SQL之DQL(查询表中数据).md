DQL(Query):对数据库的表中数据进行查询
	1.排序查询
	2.聚合查询
	3.分组查询
	4.分页查询
	
	
	1.排序查询：
		select * from 表名 order by 排序字段1  排序方式1 , 排序字段2  排序方式2...;	
			
		排序方式：
			desc 降序   asc 升序
			仅当前一个排序字段值一致时，才会采用下一个的排序字段来排序,否则忽略后面的排序字段
		举例：		
			SELECT * FROM stu ORDER BY NAME ASC,ADDTIME ASC;
			
	2.聚合查询，对表中的一列作为对象进行count,max,min,avg(),sum()等查询
		select count(列名)/max(列名)/min(列名),avg(列名)/sum(列名) from 表名;	
			说明：列中为null的值，不被参与聚合，可以使用 ifnull(列名，用于替换null的值)来避开null值
			举例：
				SELECT COUNT(id) FROM stu;
				SELECT MAX(chinese) FROM stu;
				SELECT MIN(IFNULL(chinese,0)) FROM stu;
				SELECT SUM(IFNULL(chinese,0)) FROM stu;
				SELECT AVG(IFNULL(chinese,0)) FROM stu;
				
	3.分组查询，对表中的一列的列名作为分组字段，可以加条件限定其值
		select 分组字段(分组列名)，聚合函数.. 
			from 表名 where 分组前条件 group by 分组列名 having 分组后条件
		
		说明：
			select 后面是跟着显示输出的内容
			分组前条件是使用where来引导的，其条件可以是字段名(列名)
			分组后条件是使用having来引导的，其条件是 经过分组后的情况(可以是聚合函数)
		举例：
			SELECT sex,COUNT(id) ,AVG(chinese)
			,AVG(math),AVG(english)  
			FROM stu WHERE math > 60 GROUP BY sex HAVING count(id) > 2;

	4.分页查询：对表中的数据设定开始索引和每页条数	
		select * from 表名 limit 开始的索引，每页条数  
			-- limit为mysql用于分页的关键字，不通用于其他数据库
		
		举例：
			SELECT * FROM stu LIMIT 4,3 ;
		
	
	查询所有：
		select * from 表名;
	查询指定字段：
		select 列名1,列名2,...列名n from 表名；
	对查询结果进行去重：
		select distinct 列名1，列名2,...列名n from 表名；
			-- 对查询结果 列名1，列名2,...列名n 进行去重
	对查询字段重命名：
		select 列名1,列名2 as 重命名1,...列名n as 重命名2 from 表名;
	对同一行的数据进行加和：
		select 列名1,列名2,列名y+列名x from 表名；
			-- 一般是数字类型才进行加和操作	
	
	运算符：
		<,>,<=,>=,=,<>   	-- 数值判断
		between...and	  	-- 数值范围判断
		in(集合)			  	-- 数值集合判断
		is null 		  	-- null 值判断，不可以用数值判断运算符
		and or not && || !	-- 逻辑判断，用于条件间
		like           		-- 模糊查询
	
		举例：
			SELECT * FROM stu WHERE chinese BETWEEN 60 AND 100;
			SELECT * FROM stu WHERE chinese IN(99,100,98);
			SELECT * FROM stu WHERE chinese IS NOT NULL;
			SELECT * FROM stu WHERE chinese IS NULL;
	
	模糊查询
		占位符：
			_  -- 下划线单个任意字符，能占一位
			%  -- 百分号能代表任意个任意字符，能占0~n位		
		举例：
			SELECT * FROM stu WHERE NAME LIKE "___";
					-- 名字为3个字
			SELECT * FROM stu WHERE NAME LIKE "_若_"; 
					-- 中间为若字
			SELECT * FROM stu WHERE NAME LIKE "%若%";  
					-- 包含若字即可
	综合查询：
		select 	列名1,列名2 as 重命名1,...列名n as 重命名2 from 表名
			where 对入口数据进行筛选的条件 having 对结果进行筛选的条件 
			order by 排序字段1  排序方式1 , 排序字段2  排序方式2...	
			limit 起始数据索引号，一次取多少条数据号 ;
	
		
			