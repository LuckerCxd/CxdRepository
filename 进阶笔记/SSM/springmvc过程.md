### 一、简单入门springmvc过程

#### 	1.jar包导入至/WEB-INF/lib

​	aop(4个,aop,aspect,aopalliance,aspectjweaver) 

​	beans + core + expression + context 

​	web + webmvc 

​	jstl (jstl + standary)



#### 	2.web.xml 配置DispatcherServlet，关联springmvc的配置文件

​	指定该配置文件名称和位置：src/spring-mvc.xml 

​	如果不指定的话，则应为  /WEB-INF/<servlet-name>servlet.xml 

```
<servlet>
	<servlet-name>springmvcServlet</servlet-name>
	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	<init-param> 
		<param-name>contextConfigLocation</param-name>
		<param-value>classpath:spring-mvc.xml</param-value>
	</init-param>
	<load-on-startup>1</load-on-startup>
</servlet>

<servlet-mapping>
	<servlet-name>springmvcServlet</servlet-name>
	<url-pattern>/</url-pattern>
</servlet-mapping>
```

#### 	3.配置springmvc配置文件

##### 		1. 添加aop,bean,context,mvc命名空间和相关schemas

##### 		2. 配置注解扫描包，只扫@controller注解

```
    <context:component-scan base-package="cxd" use-default-filters="false">
            <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
     </context:component-scan>
```

##### 		3. 配置mvc的静态资源的处理，动态资源的处理

```
	<mvc:annotation-driven></mvc:annotation-driven>
    <mvc:default-servlet-handler></mvc:default-servlet-handler>
```

##### 		4. 配置视图解析器

```
	<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/pages/"></property>
        <property name="suffix" value=".jsp"></property>
    </bean>
```

#### 	4.在controller类上作Mapping映射 （href -> controller）

```
<a href="helloWorld">hello</a> 
```

    @Controller
    public class HelloWorldController {
        private final String SUCCESS = "success";
    
        @RequestMapping("/helloWorld")
        public String hello() {
            System.out.println("hello test");
            return SUCCESS;
        }
    }



-------------------------------------------
### 二、rest风格的占位符

```
<a href="test/restHelp/1">restHelp</a>  
```

```
@RequestMapping("/test/restHelp/{id}")
public String restHelp(@PathVariable("id") int id) {
       System.out.println("id : "+id);
       return SUCCESS;
}
```



-------------------------------------------
### 三、页面form内容交给Controller,封装成一个POJO对象

controller方法会对入参的POJO对象，根据input-name进行参数设置，

且会按照key-value被存储到request域中。

key的值：

	1. 如果目标方法的入参使用了@ModelAttribute的value属性，那么该key即由@ModelAttribute决定
 	2. 否则，那么该key为类名第一个字母小写，例如(User kuser)  --->  key: user

<form action="test/ParamAnno">
    username :<input type="text" name="name">
    <br>
    password :<input type="text" name="password">
    <br>
    email :<input type="text" name="email">
    <br>
    <input type="submit" value="submit">
</form>

​    

    @RequestMapping("/test/ParamAnno")
    public String param(User user) {
        System.out.println("user : "+user);
        return SUCCESS;
    }
    
    @RequestMapping(value = "/test/ParamModel")
    public String param2(@ModelAttribute("kuser") User user) {
    	System.out.println("user : "+user);
    	return SUCCESS;
    }
--------------------------------------------
### 四、页面form内容交给Controller,目标方法多个参数对应input标签的字段

1.如果不使用@RequestParam，那么input标签的name要与入参的各个参数名对应

2.如果使用@RequestParam，那么input标签的name则要与各个@RequestParam的value属性对应

```html
<form action="test/ParamMap">
  username :<input type="text" name="name">
  <br>
  password :<input type="text" name="password">
  <br>
  email :<input type="text" name="email">
  <br>
  <input type="submit" value="submit">
</form>
```


```
  @RequestMapping("/test/ParamMap")
    public String param(@RequestParam(value = "name") String ename,
                        @RequestParam("password")String password,
                        @RequestParam("email") String email) {
        System.out.println("name :"+ename);
        System.out.println("password :"+password);
        System.out.println("email :"+email);
        return SUCCESS;
    }
```



### 五、页面form内容交给Controller，并封装成一个POJO对象到request域的方式

#### 0. 目标方法入参User即可

#### 1. 目标方法入参ModelMap,User    ------->   modelMap.addAttribute()

#### 2. 目标方法入参Model,User    ------>    model.addAttribute()

#### 3. 目标方法入参Map<String,Object>，User    ---->    map.put()

#### 4. 目标方法返回值为ModelandView,入参User   ----->  modelAndView.addObject()

#### 5. 目标方法入参Request对象,User    ------>  request.setAttribute()


    附加：将request域的euser存到session域中：
       @SessionAttributes注解只能在类上使用，不能在方法上使用
      @SessionAttributes(value = {"euser"},types = {String.class})
      如果requestScope域中有euser，就存进去
      如果requestScope域中有String.class类型，也存进去

--------------------------------------------------------
### 六、@ModelAttribute的使用，用于update某属性不可变的POJO对象

1. 使用@ModelAttribute作用一个方法，该方法要存入POJO对象到request域，返回值为void
2. 对目标方法入参一个POJO对象，目的是可以获取到@ModelAttribute方法存入request域的POJO对象

```
<form action="test/testModelAttribute" method="post">
      testModelAttribute--<br/>
      <input type="hidden" name="id" value="1">
      username :<input type="text" name="name" value="Mia">
      email :<input type="text" name="email"  value="mia@qq.com">
      <input type="submit" value="submit">
</form>

@ModelAttribute
public void testModelAttribute(@RequestParam(value = "id",required = false) Integer id
	,ModelMap map) {
	if(id != null){
   	 	User user = new User("kk", "123456", "kk@123456");
  	  	map.addAttribute("maUser",user);
    }
}

@RequestMapping(value = "/test/testModelAttribute")
public String testModelAttribute(@ModelAttribute("maUser") User user) {
	System.out.println("user : "+user);
	return SUCCESS;
}
```



### 七、国际化资源配置

1.  国际化资源的位置命名： src/global/xxxx_zh_CN.properties  ，  src/global/xxxx_en_US.properties

2. 将ResourceBundleMessageSource声明到ioc容器中，**并命名id为messageSource**，配置basename属性为：国际化资源的相对路径  global/xxxx ，IDEA会报错但不影响

   ```
   <bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
           <property name="basename" value="global/i18n"></property>
   </bean>
   ```

3. 1 两种方式显示页面国际化资源，不能在index.jsp中，要经历DispatcherSertvlet映射

   ​	①. 使用jstl的fmt库

   ```
   <%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
   <fmt:message key="username"></fmt:message>
   <fmt:message key="password"></fmt:message>
   ```

   ​	②. 使用spring的tags库

   ```
   <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
   <spring:message code="username"></spring:message>
   <spring:message code="password"></spring:message>
   ```

      国际化资源的例子： src/global/i18n_zh_CN.properties

   ```
   username=\u7528\u6237\u540D
   password=\u5BC6\u7801
   ```
   
   3.2 可以在bean中获取国际化资源
   
   ​	先获取到在springmvc-ioc容器中的bean：ResourceBundleMessageSource
   
   ```
   @Autowired
   private ResourceBundleMessageSource messageSource;
   ```
   
   ​	再获取国际化资源的内容：
   
   ```
   @RequestMapping("/i18n1")
   public String testI18nInBeanAndConvertLocale(Locale locale) {
       String val = messageSource.getMessage("username", null, locale);
       System.out.println(locale.getCountry()+"  "+locale.getLanguage()+" "+val);
       return "i18n1";
   }
   ```
   
   3.3 可以不依赖浏览器设置locale，根据映射本身修改Locale
   
   ​	先在springmvc的ioc容器中配置SessionLocaleResolver,**id=localeResolver**
   
   ```
   <bean id="localeResolver" class="org.springframework.web.servlet.i18n.SessionLocaleResolver"/>
   ```
   
   ​	再配置LocaleChangeInterceptor拦截器，用于识别url上的特殊的参数locale
   
   ```
   <mvc:interceptors>
   	<bean class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor"/>
   </mvc:interceptors>
   ```
   
   ​	页面设置：
   
   ​	需要先将当前的locale存储到request域中：  	
   
   ​				RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
   
   ​	再设置url:"i18n2?locale=en_US" :  】	·去·																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																				LocaleChangeInterceptor拦截器就是对这个locale参数识别
   
   ```
   <%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
   <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
   .....
   <%
   	Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
   	request.getSession().setAttribute("locale",locale);
   %>
   <fmt:message key="password"></fmt:message>
   <br>
   <a href="i18n1">i18n1</a>
   <br>
   <c:if test="${locale.toString() eq 'zh_CN'}">
   	<a href="i18n2?locale=en_US">en_US</a>
   </c:if>
   <br>
   <c:if test="${locale.toString() eq 'en_US'}">
   	<a href="i18n2?locale=zh_CN">zh_CN</a>
   </c:if>
   ```
   
   

### 八、直接映射简单页面，不经过Controller的@RequestMapping方法

​	path是映射路径，view-name会拼接视图解析器的preffix和suffix

​	相当于是@RequestMapping的value，view-name相当于是方法的返回值

```
<mvc:view-controller path="/straightMapping" view-name="straightMapping"/>
```



### 九、自定义视图解析：BeanNameViewResolver

 1. 配置BeanNameViewResolver视图解析器，定义order，越小优先级越高

    ```
    <bean class="org.springframework.web.servlet.view.BeanNameViewResolver">
            <property name="order" value="100"></property>
    </bean>
    ```

 2. 自定义视图类 implements View接口，并注册到ioc容器中，重写getContentType，render方法

    ```
    @Controller
    public class CustomView implements View {
        @Override
        public String getContentType() {
            return "text/html";
        }
        @Override
        public void render(Map<String, ?> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
            httpServletResponse.getWriter().write("CustomView hello");
        }
    }
    ```

    

 3. 需要配置好mvc的静态、动态资源的获取，否则会404

    ```
    <mvc:default-servlet-handler/>
    <mvc:annotation-driven/>
    ```

 4. 直接做一个到Bean的映射，返回值为注册在IOC容器中的Bean的id，默认为小写首字母

    ```
    @RequestMapping("/myCustomView")
    public String customView() {
    	return "customView";
    }
    ```

    

### 十、重定向和转发

​	1.原因：

```
InternalResourceViewResolver  ->	UrlBasedViewResolver 
public static final String REDIRECT_URL_PREFIX = "redirect:";
public static final String FORWARD_URL_PREFIX = "forward:";
```

​	2.使用：return的内容是需要映射的，也就是不能直接返回view-name去拼接视图解析器，而是返回一个映射的路径

```
@RequestMapping("/testRedirect")
public String testRedirect() {
	return "redirect:/myCustomView";
}
@RequestMapping("/testForward")
public String testForward() {
	return "forward:/straightMapping";
}
@RequestMapping("/myCustomView")
public String customView() {
	return "customView";
}
```



### 十一、 Restful风格的curd演示

​	0. 0 实体类知悉

​	![restfulUML](D:\XD学习\MYcode\Java\进阶笔记\leecode\restfulUML.png)

​	

​	0.1 配置一个HiddenHttpMethodFilter，将post请求转换为PUT,DELETE 的http请求

```
 <!-- 配置 HiddenHttpMethodFilter: 把 POST 请求转为 DELETE、PUT 请求 -->
 <filter>
 	<filter-name>HiddenHttpMethodFilter</filter-name>
 	<filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
 </filter>
 <filter-mapping>
 	<filter-name>HiddenHttpMethodFilter</filter-name>
 	<url-pattern>/*</url-pattern>
 </filter-mapping>
```

​	下面是restful风格的curd演示：

#### 1. 显示所有员工

​	要求：url : emps  , method : GET 

```
@RequestMapping(value = "/emps", method = RequestMethod.GET)
public String emps(ModelMap map) {
    map.addAttribute("employees",employeeDao.getAll());
    return "curd/list";
}
```

可以看到映射到了 /emps , method=GET

目标方法入参map，并将employees入参到request域中，之后返回字符串“curd/list”，经过视图解析器，拼装前缀后缀，构成一个完整的url并forward过去，直接转发共享request，这个list.jsp会引入jstl库，并完成request域内容的展示。

```
<c:if test="${empty requestScope.employees}">
        没有任何员工信息
</c:if>
<c:if test="${! empty requestScope.employees}">
    <table border="1" cellpadding="10" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>LastName</th>
            <th>Email</th>
            <th>Gender</th>
            <th>Department</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${requestScope.employees}" var="emp">
            <tr>
                <td>${emp.id}</td>
                <td>${emp.lastName}</td>
                <td>${emp.email}</td>
                <td>${emp.gender == 0 ? '女':'男'}</td>
                <td>${emp.department.deptName}</td>
                <td><a href="">Edit</a></td>
                <td><a href="">DELETE</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
```



#### 2. 新增员工信息

要求：

​	a. url : emp , method : GET  进入新增input界面

​	b. url : emp ,  method : POST  表单post后回显所有员工信息

```
@RequestMapping(value = "/emp", method = RequestMethod.GET)
public String emp(ModelMap map) {
    Map<String,String> genders = new HashMap();
    genders.put("0","Female");
    genders.put("1","Male");
    map.addAttribute("genders",genders);
    map.addAttribute("employee",new Employee());
    map.addAttribute("departments",departmentDao.getAll());
    return "curd/input";
}
```

可以看见，这里映射/emp，method=GET，入参Map，存入了一个map形式的genders，一个无参构造的employee，这个无参构造的employee，其实是给form表单使用的（modelAttribute），这里是对应input的字段的path都应该是这个modelAttribute应该有的成员变量，所以应该被存入到request提供使用，所有的部门信息，而起跳转的curd/input会解释为什么：

```
<%--
    这里的modelAttribute对应的就是在目标方法中存入到map的对象的key，
    表单中直接使用的path属性对应的就是这个key对应的value对象的成员变量的名称
    需要：
    	<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
--%>
<form:form action="${pageContext.request.contextPath}/curd/emp" method="POST"
modelAttribute="employee">

    <!-- 根据存入map的employee对象的id属性是否为null来判断，如果是null的话就是新增-->
    
    <c:if test="${employee.id == null}">
         LastName: <form:input path="lastName"/>
         <form:errors path="lastName"></form:errors>
    </c:if>
    <br>
    <%--
          radiobuttons：单选框
          这个字段对应目标方法存入到map的genders,这个genders是map，items就是genders
    --%>
    Gender: <form:radiobuttons path="gender" items="${genders}"/>
    <br>
   	Email: <form:input path="email"/>
    <br>
    <%--
		select多选框
		department.deptId对应的是form表的modelAttribute属性的对象的department成员变量的deptId成员变量
     	items就是departments
     	itemLabel就是需要显示的选择内容：departments里的department对象的deptName成员变量
     	itemValue就是需要传入的选择内容：departments里的department对象的deptId成员变量
    --%>
    Department:
    <form:select path="department.deptId" items="${departments}" 		itemLabel="deptName" itemValue="deptId"/>
    <br>
    Birth: <form:input path="birth"/>
    <br>
    Salary: <form:input path="salary"/>
    <br>
	<input type="submit" value="Submit">
</form:form>
```



将表单提交过去：直接重定向映射到url上，更加方便。如果return view-name，还是要入参map存储employees。

```
@RequestMapping(value = "/emp", method = RequestMethod.POST)
public String empPut(Employee employee) {
    employeeDao.save(employee);
    return "redirect:/curd/emps";
}
```

#### 3. 删除员工信息

​	要求 ： url : emp/{id}  , method : DELETE ：删完后回显到所有员工信息

这里的url映射是Delete方式，所以不能直接超链接上去：

依靠一张delete-form，里面有input  type: hidden  , name: _method , v alue:DELETE

再使用js代码，事件监听，当点击到delete对应的超链接时，将这个delete-form提交上去

这种方式就让本来为GET的href方式，转为form的POST，再经过过滤器转换成DELETE方式

**DELETE 和 PUT都不可以直接view-name 或是 forward 过去，要redirect重定向才能正常访问。**

```
<!--这个form会在delete超链被点击时，submit上去，这个hidden隐藏域会交给filter去转换成DELETE-->
<form id="deleteForm" method="post">
     <input type="hidden" name="_method" value="DELETE">
</form>
```

```
<td>
    <a class="delete" href="${pageContext.request.contextPath}/curd/emp/${emp.id}">
   		 Delete
    </a>
</td>
```

```
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/jquery-1.9.1.min.js">
</script>
<script type="text/javascript">
    $(function(){
  	  $(".delete").click(function () {
   		 var href =  $(this).attr("href");
   		 $("#deleteForm").attr("action", href).submit();
    		return false;
    	});
    })
</script>
```

```
@RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
public String empDel(@PathVariable("id") Integer id) {
	employeeDao.remove(id);
	return "redirect:/curd/emps";
}
```

#### 4. 修改员工信息

要求：

​	a. url : emp/{id} , method：GET ：进入修改input界面，lastName不能被修改

​	b. url : emp , method : PUT : 表单post后要回显到所有员工信息

这里的url映射是GET方式，所以可以直接超链接上去就可以。

```
 <td>
 	<a class="edit" href="${pageContext.request.contextPath}/curd/emp/${emp.id}">
 		Edit
 	</a>
 </td>
```

这里入参map，存入request的需要是一个指定了id的employee对象，所以不要重定向到 新增员工的url去

如果重定向过去，存到request的是一个空参构造的employee对象。

```
@RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
public String empEdit(@PathVariable("id") Integer id,ModelMap map) {
    Map<String,String> genders = new HashMap();
    genders.put("0","Female");
    genders.put("1","Male");
    map.addAttribute("genders",genders);
    map.put("employee", employeeDao.get(id));
    map.addAttribute("departments",departmentDao.getAll());
    return "curd/input";
}
```

返回到input界面，判断employee.id是否为空，做一个逻辑判断，form提交的仍然是 /emp 

```
<!-- 
	根据存入map的employee对象的id属性是否为null来判断，如果是null的话就是新增，
	如果不为null,那么就是update操作时，目标方法存入request的一个指定id的employee对象
-->
<c:if test="${employee.id == null}">
    LastName: <form:input path="lastName"/>
    <form:errors path="lastName"></form:errors>
</c:if>
<c:if test="${employee.id != null}">
	<!-- 
       这里确定是update操作，要传一个hidden的id 和 _method
       id 是为了给modelAttribute标注的方法用上，以从数据库中拿出这个employee
       再将这个employee存到request域，当下一个requestMapping目标方法的入参Employee时，
       将这个employee放到了这个参数
       _method 是转换表单的Post为PUT
	-->
    <h3>UPDATE EE</h3>
    <input type="hidden" name="id" value="${employee.id}">
    <input type="hidden" name="_method" value="PUT">
</c:if>
```

这个modelAttribute注解的方法：

```
@ModelAttribute(value = "employee")
public void serviceForUpdate(@RequestParam(value = "id",required = false) Integer id,
·ModelMap map) {
    if(id != null){
    	map.addAttribute("employee",employeeDao.get(id));
   		System.out.println("ma: "+employeeDao.get(id));
    }
}
```

modelAttribute服务的目标方法：

**DELETE 和 PUT都不可以直接view-name 或是 forward 过去，要redirect重定向才能正常访问。**

```
@RequestMapping(value = "/emp", method = RequestMethod.PUT)
public String empUpdate(@ModelAttribute("employee") Employee employee
    employeeDao.save(employee);
    System.out.println(employee);
    return "redirect:/curd/emps";
}
```



### 十二、添加自定义的转换器

​	步骤：

​	1. 自定义转换器，实现Converter<String, Employee>接口，String为input的输入类型，Employee为经过转换器后的输出类型，重写convert方法。

```
public class CustomConvertor implements Converter<String, Employee> {
    @Override
    public Employee convert(String s) {
        String[] split = s.split("-");
        if(split != null && split.length == 4){
            Employee employee = new Employee();
            employee.setLastName(split[0]);
            employee.setEmail(split[1]);
            int gender = split[2].equals("女")?0:1;
            employee.setGender(gender);
            employee.setDepartment(DepartmentDao.get(Integer.parseInt(split[3])));
            return employee;
        }
        return null;
    }
}
```

​	2. 为自定义的转换器配置到FormattingConversionServiceFactoryBean的converters属性,

​		并将FormattingConversionServiceFactoryBean配置到mvc:annotation-driven的conversion-service属性

```
<mvc:annotation-driven conversion-service="conversionService"/>
<bean id="conversionService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
	<property name="converters">
        <set>
			<bean class="cn.cxd.crudExam.converters.CustomConvertor"></bean>
		</set>
	</property>
</bean>
```

​	3. 表单：text类型，name是source，这个name和映射的目标方法的入参RequestParam对应上

```
<form:form action="${pageContext.request.contextPath}/curd/empConvertor">
    <input type="text" name="source"/>
    <input type="submit" value="Submit">
</form:form>
```

​	4. 目标方法：入参RequestParam与 input的name属性对应上才能接收

```
@RequestMapping("/empConvertor")
public String empConvertor(@RequestParam(value = "source",required = false) Employee employee) {
	if(employee != null){
		employeeDao.save(employee);
	}
	return "redirect:/curd/emps";
}
```



### 十三、@InitBinder , 失效表单属性

使用要求:  返回void，可以入参WebDataBinder，disallowedfields()

webDataBinder.disallowedfields（xxx）失效 表单 到 bean的xxx属性 的绑定	

守护等级较低，连Modelattribute方法也会引发这个方法

```
@InitBinder
public void disableGender(WebDataBinder webDataBinder){
    webDataBinder.setDisallowedFields("lastName");
}
```

### 十四、数据的格式化

步骤：

1.Bean属性添加注解

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth;
	@NumberFormat(pattern = "#,###")
	private Double salary;
2.**目标方法Bean入参后紧跟BindingResult**，可用来截取格式验证失败的信息,若有错误回显继续输入

​	getErrorCount、getFieldErrors、error.getDefaultMessage（）

```
@RequestMapping(value = "/emp", method = RequestMethod.POST)
public String empPut(Employee employee, BindingResult bindingResult, ModelMap map) {
    if(bindingResult.getErrorCount() > 0){
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for(FieldError error:fieldErrors){
       	 	System.out.println(error.getDefaultMessage());
        }	
    	return "redirect:/curd/emp";
    }
    employeeDao.save(employee);
    map.addAttribute("departments",departmentDao.getAll());
    map.addAttribute("employees",employeeDao.getAll());
    return "curd/list";
}
```



### 十五、hibernate-validator作数据校验：

​	步骤:

#### 0. 导入jar包： 

 1. hibernate-validator-6.1.5.Final 、 
 2. hibernate-validator-annotation-processor-6.1.5.Final
 3. hibernate-validator-cdi-6.1.5.Final
 4. classmate-1.3.4
 5. jakarta.el-3.0.3
 6. jakarta.validation-api-2.0.2
 7. jboss-logging-3.3.2.Final

#### 1.  在bean对象上添加注解

​	在spring-mvc配置文件有< mvc:annotation-driven >的情况下，将@past,@DateTimeFormat,@NotEmpty 等注解加在bean的属性上。

```
private String lastName;
@Email(message = "邮件格式不正确")
private String email;
private Integer gender;
private Department department;
@Past(message = "需要是过去的时间")
@DateTimeFormat(pattern = "yyyy-MM-dd")
private Date birth;
```

#### 2. 目标入参对象前加上@Valid注解

​	目标入参对象前加上@Valid注解， @Valid 和 BindingResult 是一一对应的，如果有多个@Valid，那么每个@Valid后面跟着的BindingResult就是这个@Valid的验证结果，顺序不能乱，目标方法在验证错误时，应该直接返回出view-name,而不是再去重定向。

```
@RequestMapping(value = "/emp", method = RequestMethod.POST)
public String empPut(@Valid Employee employee, BindingResult bindingResult, ModelMap map) {
    if(bindingResult.getErrorCount() > 0){
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for(FieldError error:fieldErrors){
       		System.out.println(error.getField()+"   "+error.getDefaultMessage());
    	}
        Map<String,String> genders = new HashMap();
        genders.put("0","Female");
        genders.put("1","Male");
        map.addAttribute("genders",genders);
        map.addAttribute("departments",departmentDao.getAll());
    	return "curd/input";
    }
    employeeDao.save(employee);
    return "redirect:/curd/emps";
}
```

#### 3.  错误消息显示到页面上

​	bean对象属性上的注解的message属性，会被展示，但是优先级低于国际化资源的配置显示

```
 @Email(message = "邮件格式不正确")
 private String email;
 private Integer gender;
 private Department department;
 @Past(message = "需要是过去的时间")
```

​	path:对应的是加了校验注解的Bean属性的名称

```
<form:errors path="lastName"></form:errors>
```

#### 4. 国际化错误信息：

​	国际化错误信息 (优先级高于注解的message属性，会优先展示)

​	为spring-mvc配置**id为messageSource的ResourceBundleMessageSource对象**，并设置basename属性为 相对路径/xxx , 再在该相对路径下写国际化资源 xxx_en_US、xxx_zh_CN.properties文件，格式为：

注解名.bean对象名称.bean对象的属性=eeeee。

```
<bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
        <property name="basename" value="global/i18n"></property>
</bean>
```

/conf/global/i18n_en_US.properties文件，/conf是根路径之一。

```
Email.employee.email=error Email Format
Past.employee.birth=Birth need past
```

###  十六、json数据返回格式，上传下载演示

​	步骤：

#### 		0. JSON导入jar包

​			1. jackson-annotations-2.9.8.jar

​			2. jackson-core-2.9.8.jar

​			3. jackson-databind-2.9.8.jar

#### 		1.示例1.1上传：

​	在加入@ResponseBody注解后，方法不会对返回值做视图解析，而是直接输出到response，而入参的@RequestBody注解标注的是 form被提交上来的所有内容，如果没有form被提交，仅仅是获取对象，并以json形式返回则不必要@RequestBody入参对象。

```
@ResponseBody
@RequestMapping("/testResponseBody")
public String testHttpMessageConverter(@RequestBody String body){
    System.out.println("body:"+body);
    return "helloworld! " + new Date();
}
```

​	这个form是一个上传的表单。

```
<form action="${pageContext.request.contextPath}/curd/testResponseBody"
	method="post" enctype="multipart/form-data">
	File: <input type="file" name="file"/>
	Desc: <input type="text" name="desc"/>
	<input type="submit" value="Submit"/>
</form>
```

#### 	示例1.2上传：

##### 0. 先导入commons-fileupload、commons-io jar包

##### 1. 再springmvc中配置：CommonsMultipartResolver 

​	**id="multipartResolver"**  

​	defaultEncoding,定义编码属性为utf-8

```
<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <property name="defaultEncoding" value="UTF-8"></property>
 </bean>
```

##### 2. 表单内容

```
<form action="${pageContext.request.contextPath}/curd/testUpload"
      method="post" enctype="multipart/form-data">
		upload-File: <input type="file" name="upload-file"/>
		upload-Desc: <input type="text" name="upload-desc"/>
		<input type="submit" value="Submit"/>
</form>
```

##### 3. 目标方法

【入参MultipartFile，getOriginalFilename：上传的文件的名称
getName：表单对应的input的name属性】

```
@RequestMapping("/testUpload")
public String testUpload(@RequestParam("upload-file")MultipartFile multipartFile,
@RequestParam("upload-desc")String uploadDesc) {
    System.out.println("originalFilename: "+ multipartFile.getOriginalFilename());
    System.out.println("name: "+ multipartFile.getName());
    System.out.println("size: "+ multipartFile.getSize());
    return "success";
}
```

​	

#### 	2.  示例2:下载

```
<a href="curd/testResponseEntity">download Jquery-1.9.1.min.js</a>
```

```
 @RequestMapping("/testResponseEntity")
 public ResponseEntity<byte[]> testHttpMessageConverter(HttpSession sesssion) throws IOException {
 	InputStream in = sesssion.getServletContext().getResourceAsStream("scripts/jquery-1.9.1.min.js");
 	// 使用servletContext的getResourceAsStream将文件读取成流，再一次read写进byte[]
 	byte[] bytes = new byte[in.available()];
 	in.read(bytes);
	
	// HttpHeaders,HttpStatus的设置
	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-Disposition", "attachment;filename=jquery-1.9.1.min.js");
	HttpStatus status = HttpStatus.OK;
	
	// 使用ResponseEntity<T> 泛型为byte[],使用byte[],HttpHeaders,HttpStatus来构造，点击时就会去下载
	ResponseEntity<byte[]> responseEntity = new ResponseEntity(bytes,headers,status);
	System.out.println(responseEntity);
	return responseEntity;
}
```

#### 十七、自定义拦截器的使用

#### 1. 创建一个自定义的拦截器，需要继承，并重写3个方法

```
public class FirstInterceptor implements HandlerInterceptor {

    // 在目标方法调用前使用，return true才会继续运行下一步，乃至下一个Interceptor
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("FirstInterceptor-preHandle startTime: "+new Date());
        return true;
    }

    // 在目标方法无异常执行后、视图渲染前使用
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("FirstInterceptor-postHandle endTime: "+new Date());
    }

    // preHandle返回值为true，都要执行
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("FirstInterceptor-afterCompletion...");
    }
}
```



#### 2. 将这个拦截器配置到sprignmvc-ioc容器的拦截器中

可以直接配置一个bean拦截器在interceptors中，那么会拦截下所有请求，

也可以配置<mvc:interceptor >，

​	定义<mvc:mapping path="*"/>要拦截的路径(必填)，

​	定义<mvc:exclude-mapping path="/i18n2"/>不拦截的路径

​	定义<bean class="cn.cxd.interceptor.FirstInterceptor">拦截器

```
<mvc:interceptors>
    <bean class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor"/>
    <bean class="cn.cxd.interceptor.FirstInterceptor"></bean>
    <mvc:interceptor>
        <mvc:mapping path="/i18n2"/>
        <bean class="cn.cxd.interceptor.SecondInterceptor"></bean>
    </mvc:interceptor>
    <mvc:interceptor>
        <mvc:mapping path="/i18n*"/>
        <mvc:exclude-mapping path="/i18n2"/>
        <bean class="cn.cxd.interceptor.FirstInterceptor"></bean>
    </mvc:interceptor>
</mvc:interceptors>
```



### 十八、自定义异常以及异常处理并展示错误信息到页面，异常与页面的映射

#### 	1. 自定义异常

​		**由@ResponseStatus制定这个异常发生时的网页响应，**

​			**即使用SimpleMappingExceptionResolver对这个exception去映射跳转页面 会失效！**

```
@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "customException ..")
public class CustomException extends RuntimeException{

}
```

#### 	2. 异常处理

​		目标方法仅仅是抛出Runtime异常,然后交给@ExceptionHandler注解的方法去处理(显示到网页上)

​		**@ExceptionHandler的异常处理方法需要入参Exception并返回modelAndView，才能将错误信息以用key-value放入到request域中**

​		**存在优先级问题，匹配与目标方法同一个handler的@ExceptionHandler的最接近的异常处理方法，当与目标方法同一个handler没有可以匹配的@ExceptionHandler时，才会去寻找带有@ControllerAdvice注解的handler类，在这个handler类中再去找最优的异常处理方法**

```
@ExceptionHandler({RuntimeException.class})
public ModelAndView testExceptionHandle2(Exception ex) {
    ModelAndView modelAndView = new ModelAndView("error");
    System.out.println("RuntimeException");
    modelAndView.addObject("excepteion",ex);
    return modelAndView;
}
```

```
@ControllerAdvice
public class ExceptionHandlers{
    @ExceptionHandler({ArithmeticException.class})
    public ModelAndView testExceptionHandle(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        System.out.println("ArithmeticException");
        modelAndView.addObject("excepteion",ex);
        return modelAndView;
    }
}
```

#### 3. 使用SimpleMappingExceptionResolver完成异常到页面的映射

**（会自动存入错误信息到exceptionAttribute属性作为key的map，再存到request域并跳转映射页面）**

但是对于标注了@ResponseStatus的异常，不会跳转，由@ResponseStatus制定这个异常发生时的网页响应

exceptionMappings属性：properties类型，key为 exception的全类名，value为view-name

exceptionAttribute属性：String类型，定义将异常信息存入到request域的key

```
<bean class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
    <property name="exceptionMappings">
        <props>
            <prop key="cn.cxd.exception.CustomException">error</prop>
            <prop key="java.lang.ArithmeticException">error2</prop>
        </props>
    </property>
    <property name="exceptionAttribute" value="expeteion"></property>
</bean>
```



