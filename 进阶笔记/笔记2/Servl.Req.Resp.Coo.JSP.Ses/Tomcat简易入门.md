Tomcat简易入门：
	
	1.部署



1.部署：
	
	1.在webapps下创建文件夹，并在该文件夹下放入项目，访问时：
		http://localhost:8080/xd/Permittion.html
		
		文件夹结构：
			webapps
				  xd
					Permittion.html
	
	2.在conf下server.xml文件中的<Host>标签中配置：
		<Context docBase="项目存放路径" path = "/虚拟路径(为存放路径起别名)"/>
	
	3.在conf\Catalina\localhost文件夹下创建  虚拟路径(为存放路径起别名).xml
		在xml中配置：
			<Context docBase="项目存放路径" />
			<Context docBase = "D:\Java\TryHtml\WebContent"/>

		文件夹结构：
			conf
				Catalina
					localhost
						bbb.xml     //虚拟路径(为存放路径起别名).xml
		
		访问时：
			http://localhost:8080/bbb/Permittion.html


2.在eclipse 中部署 tomcat server

	参考：https://www.cnblogs.com/Gandy/p/7435517.html		