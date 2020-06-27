### 1.引入jar:

​        /WEB-INF/lib
​           aop,aspect,aoplliance,asopectjweaver,
​           c3p0,machange-commons,mysql-connector
​           jackson-annotation,jackson-core,jackson-databind
​           bean,context,core,expression
​           jdbc,orm,tx
​           web,webmvc
​           commons-logging
​           mybatis
​           mybatis-spring

### 2.建立conf文件夹(作为源文件夹sources folder)

​    mybatis-config.xml (仅保留setting 和 db-provider项)
​    db.properties
​    log4j.properties

### 3.web.xml 中配置 springmvc的dispatcherServlet + spring的contextloaderListener

​        <servlet>
​            <servlet-name>springmvcServlet</servlet-name>
​            <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
​            <init-param>
​                <param-name>contextConfigLocation</param-name>
​                <param-value>classpath:spring-mvc.xml</param-value>
​            </init-param>
​            <load-on-startup>1</load-on-startup>
​        </servlet>
​        <servlet-mapping>
​            <servlet-name>springmvcServlet</servlet-name>
​            <url-pattern>/</url-pattern>
​        </servlet-mapping>

        <context-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:applicationContext.xml</param-value>
        </context-param>
        <listener>
            <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
        </listener>

### 4.在conf源文件夹下 (sources folder)添加：applicationContext.xml + spring-mvc.xml

    1. spring-mvc.xml （aop + bean + context + mvc 命名空间+schemas）
        a.<context:component-scan ...> 只扫controller类
        b.<mvc:annotation-driven></mvc:annotation-driven>  ->动态资源
        c.<mvc:default-servlet-handler></mvc:default-servlet-handler>  ->静态资源
        d.<bean 视图解析器>
        2. application.xml (aop + bean + context + tx + jdbc + mybatis 命名空间+schemas)
        a.<aop:aspectj-autoproxy/>
        b.<context:component-scan ...>除了controller类都扫
        c.<context:property-placeholder ...>引入外部db配置文件
        d.<bean dataSource数据源>
        e.<bean dataSourceTranscationManager基于数据源的事务管理器> dataSource成员
        f.<tx:annotation-driven transaction-manager属性>
        g.<bean SqlSessionFactoryBean>
                成员变量： dataSource 
                          configLocation : mybatis-xml的位置
                          mapperLocations : 各个mapper.xml的位置
        h.<mybatis:scan base-package="cxd.dao"></mybatis:scan> -> mapper接口的位置

spring-mvc.xml

    1. context扫描
       2. 动静态资源解析
       3. 视图解析器

    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:context="http://www.springframework.org/schema/context"
           xmlns:mvc="http://www.springframework.org/schema/mvc"
           xmlns:aop="http://www.springframework.org/schema/aop"
           xsi:schemaLocation="
            http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/context
            http://www.springframework.org/schema/context/spring-context.xsd
            http://www.springframework.org/schema/mvc
            http://www.springframework.org/schema/mvc/spring-mvc.xsd
            http://www.springframework.org/schema/aop
            http://www.springframework.org/schema/aop/spring-aop.xsd
            ">
        <context:component-scan base-package="cxd" use-default-filters="false">
            <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
        </context:component-scan>
    
        <mvc:annotation-driven></mvc:annotation-driven>
        <mvc:default-servlet-handler></mvc:default-servlet-handler>
    
        <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
            <property name="prefix" value="/WEB-INF/pages/"></property>
            <property name="suffix" value=".jsp"></property>
        </bean>
    </beans>

   

application.xml：

1.dataSource

2.transcationManger

3.SqlSessionFactoryBean

4.事务的注解支持

5.mybatis的dao扫描包

    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:tx="http://www.springframework.org/schema/tx"
           xmlns:jdbc="http://www.springframework.org/schema/jdbc"
           xmlns:context="http://www.springframework.org/schema/context"
           xmlns:mybatis="http://mybatis.org/schema/mybatis-spring"
           xmlns:aop="http://www.springframework.org/schema/aop"
           xsi:schemaLocation="http://www.springframework.org/schema/context
           http://www.springframework.org/schema/context/spring-context.xsd
         http://www.springframework.org/schema/beans
         http://www.springframework.org/schema/beans/spring-beans.xsd
         http://www.springframework.org/schema/jdbc
         http://www.springframework.org/schema/jdbc/spring-jdbc.xsd
         http://www.springframework.org/schema/tx
         http://www.springframework.org/schema/tx/spring-tx.xsd
         http://mybatis.org/schema/mybatis-spring
         http://mybatis.org/schema/mybatis-spring.xsd
         http://www.springframework.org/schema/tx
         http://www.springframework.org/schema/tx/spring-tx.xsd
         http://www.springframework.org/schema/aop
         http://www.springframework.org/schema/aop/spring-aop.xsd">
    
        <aop:aspectj-autoproxy/>
    
        <context:component-scan base-package="cxd">
            <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
        </context:component-scan>
    
        <context:property-placeholder location="classpath:db.properties"/>


​        <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
​            <property name="driverClass" value="${driverClass}"></property>
​            <property name="user" value="${user}"></property>
​            <property name="password" value="${password}"></property>
​            <property name="jdbcUrl" value="${jdbcUrl}"></property>
​            <property name="initialPoolSize" value="${initialPoolSize}"></property>
​            <property name="maxPoolSize" value="${maxPoolSize}"></property>
​        </bean>
​    

        <bean id="dataSourceTranscation" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
            <property name="dataSource" ref="dataSource"></property>
        </bean>
    
        <tx:annotation-driven transaction-manager="dataSourceTranscation"></tx:annotation-driven>
    
        <bean class="org.mybatis.spring.SqlSessionFactoryBean">
            <property name="dataSource" ref="dataSource"></property>
            <property name="configLocation" value="classpath:mybatis_sqlconfig.xml"></property>
            <property name="mapperLocations" value="classpath:mapper/*.xml"></property>
        </bean>
    
        <mybatis:scan base-package="cxd.dao"></mybatis:scan>
    </beans>

### 5.src/cxd

controller
dao（存放mapper接口位置）
model
service 

@Controller
AController{ @Autowired AService ... @RequestMapping...}

@Service
AController{ @Autowired AMapper ...}

完成这Controller 和 Service 放入ioc容器中 ， 然后加上@Autowired即可

不用管dao，mapper