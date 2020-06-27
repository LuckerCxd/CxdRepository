maven基本知识：

    1.maven的目录结构：
        1.maven-java
        2.maven-web
    2.添加依赖
    3.maven命令
    4.在eclipse中使用骨架创建maven-web工程


1.maven的目录结构：
-

    maven-java
    目录结构：
        src
            main
                java:(核心代码)
                resources（配置）
            test
                java:(核心代码)
                resources（配置）
        target(maven_build的目标存放处,例如install等等,clean就是清除target)
        pom.xml
    
    maven-web
    目录结构：
        src
            main
                java:(核心代码)
                resources（配置）
                webapp(页面资源)
                    WEB-INF
                        web.xml
                    index.jsp
            test
                java:(核心代码)
                resources（配置）
        target(maven_build的目标存放处,例如install等等,clean就是清除target)
        pom.xml

2.添加依赖：
-

    groupId公司名，artifactId依赖名称，version版本：必有属性
    scope范围：servlet,jsp仅用于编译程序时：provide
              junit用于test:test
    <groupId>javax.servlet</groupId>
    <artifactId>jsp-api</artifactId>
    <version>2.0</version>
    <scope>provided</scope>
   
    先从本地寻找依赖，当本地没有时，去中央仓库寻找，将其添加到本地

3.maven 命令：
-

    clean:清除target编译过的class文件
    compile:编译src/main到target中
    test:compile + src/test 到target中
    package:test + 将项目打包成war + 并安装到本地仓库中
    install：将项目生成 jar 包放在仓库中，以便别的模块调用
    
4.在eclipse中使用骨架创建maven-web工程
-
    步骤：

    1.创建maven project ,选择 maven-archetype-webapp  骨架

    2.填写：
        group id:公司名称
        artifact id:项目名称
        version：版本
       -->finish

    3.对于这个新的maven-web工程,可以发现缺失了:
        src/main/java,src/test/java,src/test/resource,
        对于前两个在build_path中的configure_path中可以看到：
        source下已经包含于其中了，但是最后一个src/test/resource没有
        因此,src/main/java,src/test/java可以是new -> folder
        而src/test/resource,需要new -> source folder
    
    4.修改elipse的本地仓库地址：
        1.先修改maven目录的conf下的setting.xml，添加：
            <localRepository>D:\MavenRepository\repository</localRepository>
        2.在eclipse中修改Settings
            preferences->maven->user Setting->user setting
            (填入上面的地址)
    5.修改pom.xml，添加一下：
        
        在<dependencies>中添加,添加servlet,jsp依赖
    	<dependency>
    	    <groupId>javax.servlet</groupId>
    	    <artifactId>jsp-api</artifactId>
    	    <version>2.0</version>
    	    <scope>provided</scope>
    	</dependency>
        
        
    	<dependency>
    	    <groupId>javax.servlet</groupId>
    	    <artifactId>javax.servlet-api</artifactId>
    	    <version>4.0.1</version>
    	    <scope>provided</scope>
    	</dependency>
      </dependencies>
        
      在<build>改成这样<filename>根据自己的更改,使用tomcat7兼容高版本jdk
      <build>
        <finalName>MavenWebDemo1</finalName>
        <plugins>
    	    <plugin>
    		  <groupId>org.apache.tomcat.maven</groupId>
    		  <artifactId>tomcat7-maven-plugin</artifactId>
    		  <version>2.2</version>
    		</plugin>
        </plugins>
      </build>

    6.在src/main/src下添加包，并在下添加class,继承HttpServlet
        因为不能直接创建sertvlet,因此要自己写class继承
    
    7.在elipse中选择自己的maven插件
        preference->maven->installations->添加自己的maven路径(总包不是bin)
        并将其选择上
    8.elipse对maven工程，使用run as->maven build -> Goal: tomcat7:run

    9.在浏览器中访问即可 http://localhost:8080/MavenWebDemo1/ServletDemo1

    注意：
        此时webapp文件夹中的index.jsp的访问路径就是 index.jsp
        http://localhost:8080/MavenWebDemo1/index.jsp