约束：
	1.非空约束
	2.唯一下标约束
	3.主键约束(常使用自增)
	4.外键约束

    1.非空约束：(值非空)
    	创建：
    		1.建表时创建：
    			create table 表名（
    				...
    				列名 数据类型 not null
    			);
    			
    		2.alter table 表名 modify 列名 数据类型 not null;
			
		当某列具备非空约束时，表中的数据就要保证该列值不为空
		
		取消某列的非空约束：
			alter table 表名 modify 列名 数据类型；
		
	2.唯一下标约束：（值唯一）
		创建：	
			1.建表时创建：
    			create table 表名（
    				...
    				列名 数据类型 unique
    			);
			2.alter table 表名 modify 列名 数据类型 unique;
		
		当某列具备唯一约束时，表中的数据就要保证该列值是唯一的,
			但是null可以是重复的
			
		取消某列的唯一下标约束：
			alter table 表名 drop index 列名；

	3.主键约束：(非空且唯一)每张表中仅有一个主键
		创建：	
			1.建表时创建：
				create table 表名（
	    				...
	    				列名 数据类型 primary key
	    			);

			2.alter table 表名 modify 列名 数据类型 primary key;
			
		当某列具备主键约束时，表中的数据就要保证该列数据非空且唯一

		取消某张表的主键约束：
			alter table 表名 drop primary key;
		
		为主键加上自增选项：
			1.建表时创建：
				create table 表名（
	    			...
	    			列名 数据类型 primary key AUTO_INCREMENT 
	    		);
			2.alter table 表名 modify 列名 数据类型 primary key AUTO_INCREMENT ;
			
			在设定自增选项时，数据项的0号不能被使用，要留出来
			
			取消自增选项：
				alter table 表名 modify 列名 数据类型；
	
	4.外键约束
		创建：
			1.建表时创建：
				create table 表名（
	    			...
	    			列名x 数据类型，
					constraint 外键约束名 foreign key (列名x) REFERENCES 另一张关联表名(其内列名y)
	    		);
			2.alter table 表名 add contraint 外键约束名 foreign key (列名x) references 另一张关联表名(其内列名y)
		
		在某列设定完外键约束后，该列的值不能是关联的表上没有的属性值
		
		取消：
			alter table 表名 drop foreign key 外键约束名;	

		设置级联更新，级联删除
			alter table 表名 add contraint 外键约束名 foreign key (列名x) references 另一张关联表名(其内列名y) on update cascade on delete cascade;
		
		在设定某列外键约束且级联更新，级联删除时，一旦修改该列值或是删除该列值时，相关的表数据都会更改或是被删除