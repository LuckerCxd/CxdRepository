Action类获取提交网页提交的参数：

    方式(4种)及其实现：
        1.ServletActionContext类静态方法获取Request对象再获取参数
        2.依赖于拦截器的set注入
        3.依赖于拦截器的setModel注入-模型驱动注入
        4.依赖于拦截器的set对象注入
注意：
-
     1.使用BeanUtils：
        commons-beanutils-1.8.0.jar，commons-logging-1.2.jar
        BeanUtils.populate(bean, map)： 
      根据表单上的name属性和bean中的私有属性对比，符合的就自动调用属性的set()方法
     2.依赖于拦截器的set注入,需要为Action类设置私有属性，并提供set()方法,随后获取即可
        <interceptor name="params" class="com.opensymphony.xwork2.interceptor.ParametersInterceptor"/>
     3.Aciton类实现ModelDriven<Model_ClassName>接口，也是依赖于拦截器
        设置Model类私有变量对象并进行实例化，随后重写其getModel()方法即可获取：
        <interceptor-ref name="modelDriven"/>
        <interceptor name="modelDriven" class="com.opensymphony.xwork2.interceptor.ModelDrivenInterceptor"/>

        public String intercept(ActionInvocation invocation) throws Exception {
            Object action = invocation.getAction();
        
            if (action instanceof ModelDriven) {
              ModelDriven modelDriven = (ModelDriven)action;
              ValueStack stack = invocation.getStack();
              Object model = modelDriven.getModel();
              if (model != null) {
                stack.push(model);
              }
              if (this.refreshModelBeforeResult) {
                invocation.addPreResultListener(new RefreshModelBeforeResult(modelDriven, model));
              }
            } 
            return invocation.invoke();
        }

      4.Action类设置bean私有变量，设置其get,set()方法，而在html网页中，
        表单的name属性设置为 bean.bean中的私有变量,也是依赖于拦截器去做
        总体上，获取表单的数据,
          第1个数据：获取其name,发现是 bean.bean中的私有变量，
            进行Bean中的比对,在Action中getBean实例，发现为null，进而setBean实例。
          第2个数据：获取其name,发现是 bean.bean中的私有变量，
            进行Bean中的比对,在Action中getBean实例，发现不为null，直接设置即可。

HelloAction5:  -> 第1,2种
-
    package cn.cxd.action;
    
    import java.lang.reflect.InvocationTargetException;
    import java.util.Map;
    import cn.cxd.bean.Student;
    import javax.servlet.http.HttpServletRequest;
    
    import org.apache.commons.beanutils.BeanUtils;
    import org.apache.struts2.ServletActionContext;
    
    import com.opensymphony.xwork2.ActionSupport;
    
    public class HelloAction5 extends ActionSupport{
    	//2.1 依赖于拦截器的set注入
    	private String username;
    	private String password;
    
    	public String showPara() throws Exception{
    		//1.1 ServletActionContext类静态方法获取Request对象再获取参数
    		Student student = new Student();
    		HttpServletRequest request = ServletActionContext.getRequest();
    
            //1.2 注意使用BeanUtils，必须有：
            //  commons-beanutils-1.8.0.jar，commons-logging-1.2.jar
            //  BeanUtils.populate(bean, map);  
            // 根据表单上的name属性和bean中的私有属性对比，符合的就自动调用属性的set()方法
    		BeanUtils.populate(student, request.getParameterMap());
    		System.out.println("firstMethod:"+student);
    	     
    		//2.2 依赖于拦截器的set注入
    		student = new Student();
    		student.setUsername(username);
    		student.setPassword(password);
    		System.out.println("SecondMethod:"+student);
    		return NONE;
    	}
    
    	//2.3 依赖于拦截器的set注入
    	public void setUsername(String username) {
    		this.username = username;
    	}
    	public void setPassword(String password) {
    		this.password = password;
    	}

}

HelloAction6: ->第3种
-
    package cn.cxd.action;
    
    import com.opensymphony.xwork2.ActionSupport;
    import com.opensymphony.xwork2.ModelDriven;
    
    import cn.cxd.bean.Student;
    
    public class HelloAction6 extends ActionSupport implements ModelDriven<Student>{
    	private Student student = new Student();
    	public String showPara() {
    		System.out.println(student);
    		return NONE;
    	}
    	@Override
    	public Student getModel() {
    		return student;
    	}
    	
    }

HelloAction7: ->第4种
-

    package cn.cxd.action;
    
    import com.opensymphony.xwork2.ActionSupport;
    
    import cn.cxd.bean.Student;
    
    public class HelloAction7 extends ActionSupport{
    	private Student student;
    	public String showPara() {
    		System.out.println(student);
    		return NONE;
    	}
    	public Student getStudent() {
    		System.out.println("get it");
    		return student;
    	}
    	public void setStudent(Student student) {
    		System.out.println("set it");
    		this.student = student;
    	}
    }



Student-Bean:
-

    package cn.cxd.bean;
    
    public class Student {
    	private String username;
    	private String password;
    	
    	public void setUsername(String username) {
    		this.username = username;
    	}
    
    	public void setPassword(String password) {
    		this.password = password;
    	}
    
    	public String getUsername() {
    		return username;
    	}
    
    	public String getPassword() {
    		return password;
    	}
    
    	@Override
    	public String toString() {
    		return "User [username=" + username + ", password=" + password + "]";
    	}
    	
    	
    }

form1.html:  -> 第1,2,3种
-
    <form action="http://localhost:8080/StrutsDmeo/p9/hello0" method = "GET">
		<label for = "user" >用户名:</label> 
		<input id = "user" type = "text" name = "username" placeholder="输入用户名"></input>
		<label for = "pwd" >密码:</label> 
		<input id = "pwd" type = "password" name = "password" placeholder="输入密码"></input>
		<br/>
		<input type="submit" value ="登录">
	</form>

form2.html:  -> 第4种
-
    <form action="http://localhost:8080/StrutsDmeo/p9/hello2" method = "GET">
		<label for = "user" >用户名:</label> 
		<input id = "user" type = "text" name = "student.username" placeholder="输入用户名"></input>
		<label for = "pwd" >密码:</label> 
		<input id = "pwd" type = "password" name = "student.password" placeholder="输入密码"></input>
		<br/>
		<input type="submit" value ="登录">
	</form>

struts.xml:
-
    <package name="p9" extends="struts-default" namespace="/p9">
		<action name="hello0" class="cn.cxd.action.HelloAction5" method="showPara"></action>
		<action name="hello1" class="cn.cxd.action.HelloAction6" method="showPara"></action>
		<action name="hello2" class="cn.cxd.action.HelloAction7" method="showPara"></action>
	</package>