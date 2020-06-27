数据库备份、还原

	命令行：
		备份	： mysqldump -u用户名 -p密码 数据库名称 > 保存路径.sql 
				保存路径不是字符串 -- 例如:  d：\\a.sql

		还原： 	mysql -u用户名 -p密码
				create database 数据库名称
			  	use 数据库名称
				source 备份文件路径
					路径不是字符串 -- 例如:  d：\\a.sql