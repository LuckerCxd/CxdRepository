SQL之多表间的关系：
	
	    1.一对一：任意一方添加另一方主键 作为外键并设为唯一索引

    	2.一对多：多的一方添加一的一方的主键 作为外键 

    	3.多对多：设置中间表，至少得有双方的主键都作为外键，
					并将两个主键作为联合主键

举例：	

		老师 -> 班级 -> 学生 -> 身份证
		    1.各科老师对应各个班级(多对多)   老师(多)-班级(多)
	    	2.一个班级对应多个学生(一对多)   班级(一)-学生(多)
	    	3.一个学生对应一张身份证(一对一) 学生(一)-身份证(一)



实现：

身份证表：`

		CREATE TABLE card(
			id VARCHAR(20) PRIMARY KEY,
			birthday DATE NOT NULL
		)

班级表：
		
		CREATE TABLE class(
			id VARCHAR(20) PRIMARY KEY,
			NAME VARCHAR(32) NOT NULL
		)
	

学生表：
		
		CREATE TABLE student(
			id VARCHAR(20) PRIMARY KEY,
			cardid VARCHAR(20) UNIQUE,
			CONSTRAINT card_id FOREIGN KEY(cardid) REFERENCES card(id),
			classid VARCHAR(20),
			CONSTRAINT class_id FOREIGN KEY(classid) REFERENCES class(id)
		)

老师表：
		
		CREATE TABLE teacher(
			id VARCHAR(20) PRIMARY KEY,
			NAME VARCHAR(32) NOT NULL,
			SUBJECT VARCHAR(32) NOT NULL
		)


中间表：
		
		CREATE TABLE mid_class_teacher(
			cid VARCHAR(20),
			tid VARCHAR(20),
			PRIMARY KEY(cid,tid),  -- (cid,tid)联合起来作主键
			CONSTRAINT cla_id FOREIGN KEY(cid) REFERENCES class(id),
			CONSTRAINT tea_id FOREIGN KEY(tid) REFERENCES teacher(id)	
		);


创建关系过程：

	1.从一对一开始，从关系独立的一方开始insert into
	2.再从一对多关系中，多的一方 insert into
	3.再从多对多关系中，从关系独立的一方 insert into
	4.再对多对多的中间表，进行 insert into



实际情况：
	
    INSERT INTO  card VALUES ("9528","1993-6-9");
	INSERT INTO  card VALUES ("9527","1992-9-9");
	INSERT INTO  card VALUES ("0007","1986-6-6");
	
	
	SELECT * FROM card;
	
	INSERT INTO class VALUES ("1","车辆");
	INSERT INTO class VALUES ("2","茶叶种植");
	INSERT INTO class VALUES ("3","水利");
	
	SELECT * FROM class;
	
	INSERT INTO student VALUES ("20179985","0007","1");
	INSERT INTO student VALUES ("20179966","9527","1");
	INSERT INTO student VALUES ("20179923","9528","3");
	
	SELECT * FROM student;
	
	INSERT INTO teacher VALUES ("001","张春华","语文");
	INSERT INTO teacher VALUES ("002","李玉化","生物");
	INSERT INTO teacher VALUES ("003","白小临","数学");
	
	SELECT * FROM teacher;
	
	INSERT INTO mid_class_teacher VALUES ("1","001");
	INSERT INTO mid_class_teacher VALUES ("2","001");
	INSERT INTO mid_class_teacher VALUES ("3","002");

	SELECT * FROM mid_class_teacher;