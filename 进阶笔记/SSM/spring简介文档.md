#### 一、Application在加载文件时实例化哪些bean?

​    Application在初始化上下文时(读入配置文件时)，实例化(构造)所有单例Bean，采用反射的方式
​    ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
​    < bean class="class's classpath">

#### 二、util命名空间的用处？

​    util命名空间 + utilSchema
​    拉取若干对象成为一个集合，方便重复使用，使用id引用来使用util
​    < util:list>
​    < util:map>
​    < util:properties>

#### 三、p命名空间的用处？

​    p命名空间，没有pSchema
​    方便赋值，不用使用bean's children-property标签
​    <bean class="cn.cxd.model.Car" id="car3" p:brand="AudiA6" p:maxSpeed="260" p:price="260000"/>

#### 四、bean的生命周期是怎么样的？

​    1.构造constructor(已经完成了加载，连接(验证，准备[分配空间，初值]，解析)，初始化[static执行]的对象)
​    2.设置属性setProperty()
​    3.init-method属性引用的方法
​    4.调用该对象的方法
​    5.destroy-method属性引用的方法

#### 五、bean的依赖关系怎么实现？

​    depend-on属性，先构造depend-on列出的对象，再构造当前对象
​    < bean class="cn.cxd.model.Person" depends-on="car">

#### 六、bean的继承关系怎么实现？（autowired -> parent -> child）

​    parent属性，在parent设置property后，再完成children的property设置，可以覆盖property，
​    但是在实例化的顺序上，却并非parent就是先于children
​    abstract属性，标定为模板，不可以被实例化，

#### 七、bean的自动装配和parent继承property的先后顺序？

​    autowire : byType , byName , constructor , default
​    根据已有的bean标签的type 或是 name 对当前的bean进行自动装配
​    但是parent的继承property的顺序要迟于autowire属性，所以自动装配的很可能被覆盖

#### 八、能否引入外部配置文件？

​    xml引入外部文件，PlaceholderConfigurator作为bean的后置处理器，需要context命名空间和Schema

```
<context:property-placeholder location="db.properties"/>
```

​    使用${字段}获取
​    

#### 九、SpringEL,用于赋值内部属性

​    字面值 数值和布尔#{}，字符串#{“”}
​    引用：value = #{xx}  ; （value = #{xx.aa}这是特有的）
​    静态方法或属性: value = #{T(package.Aclass).staticFieldOrMethod}

#### 十、如何使用工厂方法创建出bean

​    工厂方法创建出bean，分为静态方法和实例方法
​    1.静态方法：对bean中的class(即工厂类)仅执行到初始化(static)，还未进行构造(实例化)，因此只能执行静态方法，内部会使用后置处理器，返回出class.factory-method(constructor-avg)对象
​    

    <!-- 静态static方法getCarFromMapS-->
    <bean id="car8" class="cn.cxd.factory.CarBeanFactory" factory-method="getCarFromMapS">
        <constructor-arg name="brand" value="AudiA8"/>
    </bean>


     2.实例方法：先创建一个工厂bean，完成了实例化，再创建bean，指明factory-bean,factory-method，内部会使用后置处理器，返回出factory-bean.factoryName(constructor-avg)对象
    
    <!-- 实例instance方法getCarFromMapI-->
    <bean id="carBeanFactory" class="cn.cxd.factory.CarBeanFactory" />
    <bean class="cn.cxd.model.Car" factory-bean="carBeanFactory" factory-method="getCarFromMapI">
        <constructor-arg name="brand" value="AudiA9"/>
    </bean>

#### 十一、注解创建出bean的方法，如何制定黑白名单？

​    需要context命名空间 + schema规则

    <context-component-scan base package> 扫描base-package下的注解
    可以有：黑白名单子标签，精确到类的注解扫描
    黑名单子标签：
    <context:component-scan base-package="cn.cxd">
        <context:exclude-filter type="assignable" expression="cn.cxd.annotationAop.controller.CustomController"/>
    </context:component-scan>
    
    白名单子标签：
    <context:component-scan base-package="cn.cxd" use-default-filters="false">
        <context:include-filter type="assignable" expression="cn.cxd.annotationAop.repository.CustomRepository"/>
    </context:component-scan>
    
    常见的注解
        @Component @Repository @Service @Controller 带参标注出指定的id
    而默认的id是 小写开头的类名
    
    获取时：
        context.getBean(id);
        context.getBean(class)

#### 十二、注解自动装配@Autowired，它的作用域有哪些？查找顺序是什么，冲突了怎么办？

​    AutowiredAnnotationBeanPostProcessor
​    @Autowired 可以作用在：字段，带参(set）方法，构造器
​    对ioc容器中的bean进行自动装配，先type,再name进行唯一装配
​    装配冲突的解决：1. 可确定name,

                  2. @qualified（）指定装配id，入参前或是@autowired后
        属性required = false,非必要的,ioc容器中没有时，也不报错，如果ioc容器有，还是会报错

    @Autowired(required = false)
    @Qualifier("service")
    public void setCustomService(MyService myService) {
        this.myService = myService;
    }
    
    @Autowired
    public void setCustomService(@Qualifier("service") MyService myService) {
        this.myService = myService;
    }

#### 十三、依赖注入的实现机制的理解？

​    泛型父类自动装配成员对象，并实现方法。由放入ioc容器中的子类去指定类型，调用继承而来的方法。
​    父类BaseService<T>，BaseRepository<T>泛型类。其中BaseService设置autowired自动装配BaseRepository成员变量,并实现public方法
​    子类CarService，CarRepository继承父类，指定了Car类型,都使用注解放入ioc容器中，子类在继承父类的public成员方法中所使用的private成员变量，它自动装配的是放入ioc容器中的子类bean。
​    

#### 十四、动态代理有jdk和cglib，这两种代理方式的区别？

​    jdkProxy:proxy.newInstance();
​    newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
​    原理：代理类持有目标对象，生成继承了目标类所有接口的字节码，生成$proxy0，适合目标对象仅仅实现了单个接口的，用于强转Proxy.newInstance();
​    System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");  //可查看字节码文件
​    

    cglib:new Enhancer();
          enhancer.setSuperClass();
          enhancer.setCallback(MethodInterceptor mi);
              methodProxy.invokeSuper(o,args);
              //调用o的父类的方法，所以o是生成的子类，不是目标类对象
          enhancer.create();
    
    原理：代理类持有目标对象，生成继承了目标类的子类的字节码。
    System.getProperties().put(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "target/cglib");  //可查看字节码文件


​    

#### 十五、aop的常用注解，并各自说明作用

jar： org.aspectj.aspectjweaver 
      org.springframework.spring-aop
      aopalliance
      org.springframework.spring-aspects
自动生成字节码对象：
      <aop:aspectj-autoproxy proxy-target-class="true"/> ： cglib
      <aop:aspectj-autoproxy/> : jdk
注意这两种方式的不同：
    如果采用jdk的话，那么在getBean时要使用接口作返回值
    如果采用cglib的话，则应该使用getBean的Class作为返回值
注解：@Order辅助设置优先级，切面方法可以携带JointPoint获取参数或是方法名
     @PointCut: 重用execution( public int *.*(..)))表达式
     @Before: 方法调用前
     @After: 方法结束前，不管是否是正常结束，相当于finally
     @AfterReturing: 方法返回后，可以获得返回值。注解需设置retruning参数名称
     @AfterThrowing：方法出现异常时，注解需设置throwing参数名称
     其中可以由于设置了参数，在切面方法中也要设置对应的参数名称，可以指定参数类型
    

     @Around: 方法环绕，使用try-catch-finally的话可以实现上面的4个注解
     切面方法需要返回Object,携带proceedingJoinPoint参数，用于返回，放行方法。

#### 十六、如何通过xml方式配置aop切面，简述过程？

   1.将service类放入ioc容器中
   2.配置aop-config,指定代理方式：proxy-target-class:jdk / cglib
    proxy-target-class决定了getBean的返回值类型
   3.在aop-config下定义pointCut切点
   4.在aop-config下定义aspect切面
        如果是afterReturning，afterThrowing需添加和方法参数一致的name

#### 十七、对数据库jdbcTemplate的几种常用方法的简述？


针对数据库的版本，5.0和8.0的数据库配置文件中jdbcUrl和driver上有所差距
5.0的配置driver：com.mysql.jdbc.Driver
8.0的配置driver: com.mysql.cj.jdbc.Driver
5.0的jdbcUrl : jdbc:mysql:///databaseName
8.0的jdbcUrl : jdbc:mysql:///databaseName?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true

jdbcTemplate需引入jar: org.springframework.jdbc，需要dataSource属性

常用的有: 
    select * : queryForMap(sql,args) , queryForList(sql,args)
    select field : queryForMap(sql,args) -> 还要提取不方便
                   queryForObject(sql,field.class,args),
                   queryForList(sql,field.class,args)
    select -> CustomClass : 
            queryForObject(sql,new BeanPropertyRowMapper(xxx.class),args)
            query(sql,new BeanPropertyRowMapper(xxx.class),args)

#### 十八、具名参数NameParameterJdbcTemplate的使用？

​    1.使用datasource参数构造NameParamterJdbcTemplate
​    2.构造model类，提供get/set属性方法
​    3.使用model对象，构造BeanPropertyParamterSource
​    4.执行update时传入，sql和BeanPropertyParamterSource
​    NamedParameterJdbcTemplate ，需要dataSource构造参数
​    使用时，要构造Object，放入到BeanPropertyParamterSource中，依赖Object属性的getter方法完成insert，update等操作。
​    SqlParameterSource 的实现类：
​         BeanPropertySqlParameterSource(Object o)
​    String sql = " insert into book (ibsn,bookname,price) values (:ibsn,:name,:price)";
​    冒号与模型属性相对应，操作起来相对复杂

#### 十九、数据库事务注解的使用？传播行为REQUIRED和REQUIRES_NEW的区别？

​    1.配置事务管理器：DataSourceTransactionManager
​    2.添加事务注解支持：<tx:annotation-driven transaction-manager>
​    3.对在ioc容器中的service类的方法上加@Transcation
​    

    传播行为propagation ：Propagation.REQUIRES_NEW , Propagation.REQUIRED
    REQUIRES_NEW : 当这个事务方法，被其他事务方法调用时，在执行到自己时，先挂起调用者事务，开启自己的事务，如果中途失败，那么回滚的也是自己的事务，自己的事务完成后，回到调用者事务。
    REQUIRED : 当这个事务方法，被其他事务方法调用时，在执行到自己时，使用的还是调用者事务，如果中途失败，那么回滚的就是整个调用者事务。

#### 二十、事务的xml配置：

​    1.配置事务管理器<bean>DataSourceTransactionManager
​    2.配置tx:advice - tx:attributes - tx:method 事务属性 
​    3.配置aop-config - aop-pointcut - aop-advice关联起tx:advice
​    

#### 二十一、spring和mybatits整合目标：

​    1.使用springioc容器来管理sessionFactory
​    2.让mybatis使用上spring的声明式事务

#### 二十二、spring的web整合思路:如何创建，如何使用ioc容器

​    1.jar:web webmvc
​    2.实现ServletContextListener接口，在init方法中new出ioc容器，并使用servletContext存起来，在web.xml中声明这个Listener,可配置其相关信息<context-param>(重点在于在init方法中)
​    3.spring帮助我们完成了上面的操作，web.xml直接添加listener，ContextLoaderListener即可，采用getWebApplicationcontext。webapplicationContextUtils.(application) 获取context。然后getBean即可

​    