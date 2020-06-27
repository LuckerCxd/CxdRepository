SQL之DCL(管理用户.权限)

管理用户：
	
	1.添加用户
	2.删除用户
	3.修改密码
	  (修改root密码、修改普通用户密码)
	4.查询用户

1.添加用户	
	
	CREATE USER '用户名'@'主机名' IDENTIFIED BY '密码';
	-- localhost:当前主机 、% 任意主机
	举例：
		CREATE USER 'xiaohong'@'localhost' IDENTIFIED BY '123';
		CREATE USER 'xiaoli'@'%' IDENTIFIED BY '123';

2.删除用户
	
	drop user '用户名'@'主机名';
	举例：
		DROP USER 'xiaoli'@'%';

3.修改密码
	
	1.修改普通用户密码：
		
	5.0:
		update user set password = password('新密码') where user = '用户名'；
		set password for '用户名'@'主机名' = password('新密码');	
		
		UPDATE USER SET PASSWORD = PASSWORD("456") WHERE USER = 'zhangsan';
		set password for 'zhangsan'@'localhost' = password('456');

	8.0:
		ALTER USER '用户名'@'主机名' IDENTIFIED WITH mysql_native_password BY '新密码';
		
		ALTER USER 'zhangsan'@'localhost' IDENTIFIED WITH mysql_native_password BY '456';
	
	2.修改root用户密码：
		已知原密码：
			
			ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '123456';
			
		忘记原密码：
			1.先使用管理员cmd执行net stop mysql，
				再执行mysqld --shared-memory --skip-grant-tables
			2.另一个管理员cmd执行mysql回车进入
				再执行FLUSH PRIVILEGES
				再执行ALTER USER 'root'@'localhost' IDENTIFIED BY '新密码'
			

	
4.查询用户
	
	use mysql; -- mysql 数据库中有个user表，里面放了用户数据信息
	select * from user;
	
权限：
	
	1.查询权限
	2.授予权限
	3.撤销权限


1.查询权限
	
	查询权限:
	SHOW GRANTS FOR '用户名'@'主机名';

2.授予权限
	
	授予权限:
	grant 权限列表 on 数据库名.表名 to '用户名'@'主机名';	
	权限列表：SELECT,delete,update,create,reload,drop,insert...（逗号隔开）
	这样,该用户的可视、可操作范围就被限定在： 数据库名.表名
	
	特殊：grant all on *.* to '用户名'@'主机名'; -- 全部权限 所有数据库的任意表

3.撤销权限
	
	撤销权限:
	revoke 权限列表 on 数据库名.表名 from '用户名'@'主机名';	

