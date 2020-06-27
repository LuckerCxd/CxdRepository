SQL之3大范式：

前置知识：

	函数依赖关系：
	完全依赖：
		通过A属性组中的所有部分，可以唯一确定B属性的值
			A (all) -> B

	部分依赖：
		通过A属性组中的一部分，可以唯一确定B属性的值
			A(part) ->B

	传递依赖：
		通过A属性(组)，可以唯一确定B属性(组)的值，
		并通过B属性(组)的值，可以唯一确定C属性(组)的值
			A -> B -> C


3大范式：
	
    1NF:字段单一属性，不可再分
	2NF:基于1NF下，消除非主属性对主属性的部分依赖
	3NF:基于2NF下，消除非主属性对主属性的传递依赖

举例：
	
创建一张仅满足范式1的表：

student 表
	
	`CREATE TABLE student(
		id INT PRIMARY KEY,   -- 学号
		NAME VARCHAR(32) NOT NULL, 
		class VARCHAR(32) NOT NULL,
		MASTER VARCHAR(32) NOT NULL, -- 班主任
		SUBJECT VARCHAR(32) NOT NULL,
		score INT NOT NULL
	);`	

添加一系列数据后如下：
	
	![](https://i.imgur.com/IjcNA8Q.jpg)

可以看出这张表存在很多的重复片段，比如：姓名，班级，班主任，学号等，因此需要对这张表进行第二范式调整：
	

	我们可以认为主属性组是 (学号，学科) 通过这个组，
	  可以确定确定出其他所有数据，可以发现：
			1.name 部分依赖于 主属性组的 id
			2.class 部分依赖于 主属性组的 id
			3.master 部分传递依赖于 id 
			4.score 完全依赖于 主属性组
		因此作出调整：(移除部分依赖)
			将name,class，master移除加上id作为另一张表
		此时表结构：
			1. id subject score   表1
			2. id name class master	表2


当进行完第二范式调整后，考虑第三范式，第三范式是基于第二范式调整后的表进行传递依赖的消除..

		主属性组是 (学号，学科) 
		经过第二范式调整后：形成一张新表：
			id name class master 
		对于这张表，主属性为:学号
			name,class 完全依赖于 id
			master 传递依赖于 id,直接依赖于class
		因此作出调整：()
			将master移除加上 class 作为另一张表
		此时表结构：
			1. id subject score 表1
			2. id name class 表2
			3. class master 表3


个人总结：
	
	对于三大范式：
		第一条范式是用于建表的，也就是说，一张表一旦能够建立起来，那它一定符合第一范式的。
		第二范式就是用于纠正第一范式留下的重复问题，做法：将表中的属性仅留下主属性组 和 完全依赖于主属性组的其他属性，
			剩余的其他属性根据其部分依赖的属性 重建出表2。
		第三范式用于纠正第二范式留下的增删各表数据的不合理问题，
			对于表2，将表二的属性消除掉具备与当前主属性(组)具备传递依赖的属性，而对于这些传递依赖根据其直接依赖的属性，重建出表3。
		

	

	