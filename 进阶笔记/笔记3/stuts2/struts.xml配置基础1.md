struts.xml配置基础1：

    1.<package>
    2.<action>
    3.创建action类的3种方式
    4.默认的action类
    5.使用action通配符*
    6.动态方法method调用
    7.[转发，重定向，转发到Action,重定向到Action](同包和不同包的)
    8.自定义result-type类

举例：

    <package name="p1" extends="struts-default" namespace="/p1">
    	<action name="hello" class="cn.cxd.action.HelloAction" method="doHello">
    		<result name = "success" type="dispatcher" >/success.html</result>
            <result name = "fail" type="dispatcher" >/fail.html</result>
    	</action>
    </package>

1.<package>
-
    abstract:抽象,用于被其他包继承
    namespace:命名空间,默认是空字符串,设置后,访问时也要添加才能访问到
        /p1/xxx/hello 可访问
        xxx/p1/hello  不可访问
       原因： 一层层想上查找 若访问路径使用 \p1\a\b时
            1.发现并没有\p1\a\b，退而求其次 找\p1\a
            2.发现没有\p1\a，退而求其次 找\p1


2.<action> :（相当于servlet）
-
    name : 相当于是@WebServlet映射路径,用name来访问
    class: 用于反射出实例，调用method
    method: 访问时执行的方法
            要求：public，
            返回值：String
            参数：空参
    result: 执行完method后的return字符串结果与result_name属性对比,
            如果内容对的上,就是返回 result标签体内容(路径URL)
            默认是： dispatcher:转发方式

3.创建action类的3种方式
-
1.直接写aciton类，不需要继承任何类，并在struts.xml中配置

    package cn.cxd.action;
    public class HelloAction {
    	public String doHello() {
    		System.out.println("doHello被执行了");
    		//int i = 5/0;
    		return "fail";
    	}
    }

    <action name="hello1" class="cn.cxd.action.HelloAction" method="doHello">
		<result name = "success" type="dispatcher" >/success.html</result>
		<result name = "fail" type="dispatcher" >/fail.html</result>
	</action>

2.写action类继承Action(xwork)接口，重写execute方法即可，在struts.xml中配置
    配置时，可以不写method,而当没有method时，默认会执行execute方法

    返回值也有常量：
        public static final String SUCCESS = "success";
        public static final String NONE = "none";
        public static final String ERROR = "error";
        public static final String INPUT = "input";
        public static final String LOGIN = "login";

    package cn.cxd.action;
    import com.opensymphony.xwork2.Action;
    public class HelloAction2 implements Action{
    	@Override
    	public String execute() throws Exception {
    		return SUCCESS;
    	}
    }
    
    <action name="hello2" class="cn.cxd.action.HelloAction2" >
		<result name = "success" type="dispatcher" >/success.html</result>
		<result name = "fail" type="dispatcher" >/fail.html</result>
	</action>

3.写action类继承ActionSupport(xwork)类
    public class ActionSupport implements Action..
        内部也有execute方法,返回为success

    package cn.cxd.action;
    import com.opensymphony.xwork2.Action;
    import com.opensymphony.xwork2.ActionSupport;
    public class HelloAction3 extends ActionSupport{
    	public String doHello() {
    		return SUCCESS;
    	}
    
    }
    
    <action name="hello3" class="cn.cxd.action.HelloAction3" method="doHello">
		<result name = "success" type="dispatcher" >/success.html</result>
		<result name = "fail" type="dispatcher" >/fail.html</result>
	</action>

4.默认的action类：
-
    <default-class-ref class="com.opensymphony.xwork2.ActionSupport" />
    可见：struts-default.xml下的默认action类,继承ActionSupport,有execute()

    <action name="hello4" >
		<result name = "success" type="dispatcher" >/success.html</result>
	</action>

5.使用action通配符*：
-
    在访问时“aaaa_bbbb”  {1} 代表:aaaa {2} 代表:bbbb
    http://localhost:8080/StrutsDmeo/p2/add_User

    <package name="p2" extends="struts-default" namespace="/p2">
    	<action name="*_*" class="cn.cxd.action.{2}" method="{1}{2}">
    		<result name = "success" type="dispatcher">/{2}/success.html</result>
    	</action>
	</package>
    package cn.cxd.action;


    import com.opensymphony.xwork2.ActionSupport;
    public class User extends ActionSupport{
    	public String addUser() {
    		return SUCCESS;
    	}
    	public String deleteUser() {
    		return SUCCESS;
    	}
    }

6.动态方法method调用：
-
    <!-- 动态方法method调用   -->
    <!-- 访问时： http://localhost:8080/StrutsDmeo/p3/User!deleteUser -->

	<constant name="struts.enable.DynamicMethodInvocation" value="true"></constant>
	<package name="p3" extends="struts-default" namespace="/p3">
		<action name="User" class="cn.cxd.action.User">
			<result name = "success" type="dispatcher">/User/success.html</result>
		</action>
	</package>

7.[转发，重定向，转发到Action,重定向到Action](同包和不同包的)：
-
                struts.xml:
    <package name="p4" extends="struts-default" namespace="/p4">
		<!-- 重定向到网页资源redirect,默认是dispatcher转发请求 -->
		<action name="hello0" class="cn.cxd.action.HelloAction3" method="doHello">
			<result name = "success" type="dispatcher">/success.html</result>
		</action>
		
		<!-- 重定向到网页资源redirect,默认是dispatcher转发请求 -->
		<action name="hello1" class="cn.cxd.action.HelloAction3" method="doHello">
			<result name = "success" type="redirect">/success.html</result>
		</action>
		
		<!-- chain转发到同包下的其他action 
			标签体内容仅是action_name,无包 -->
		<action name="hello2" class="cn.cxd.action.HelloAction3" method="doHello">
			<result name = "success" type="chain">hello1</result>
		</action>
		
		<!--redirectAction重定向到同包下的其他action, 
			标签体内容仅是action_name即可,无包 -->
		<action name="hello3" class="cn.cxd.action.HelloAction3" method="doHello">
			<result name = "success" type="redirectAction">hello1</result>
		</action>
		
		<!--redirectAction重定向到不同包下的其他action, 需要参数
			在struts-default.xml中查找出：
			<result-type name="redirectAction" class="org.apache.struts2.dispatcher.ServletActionRedirectResult"/>
			去其源码找出内容,发现：可以被设置
				protected String actionName;
	   			protected String namespace;
   			-->
		<action name="hello4" class="cn.cxd.action.HelloAction3" method="doHello">
			<result name = "success" type="redirectAction">
				<param name="namespace">/p1</param>
				<param name="actionName">hello1</param>
			</result>
		</action>
		
		
		<!--chain转发到不同包下的其他action, 需要参数
			在struts-default.xml中查找出：
			<result-type name="chain" class="com.opensymphony.xwork2.ActionChainResult"/>
			去其源码找出内容,发现：可以被设置
				 private String actionName;
   				 private String namespace;
   			-->
		<action name="hello5" class="cn.cxd.action.HelloAction3" method="doHello">
			<result name = "success" type="chain">
				<param name="namespace">/p1</param>
				<param name="actionName">hello1</param>
			</result>
		</action>
		
	</package>

8.自定义result-type:
-
    步骤：1.在struts.xml中<package>中配置<result-types>标签,并在<action>前配置：
                内部有<result-type name="" class="">
           之后在<action>中配置<return name="" type="">
           举例：
                <package name="p5" extends="struts-default" namespace="/p5">
            		<result-types>
            			<result-type name="code" class="cn.cxd.action.ValidCode"/>
            		</result-types>
        		  <action name="hello0" class="cn.cxd.action.HelloAction3" method="doHello">
        			<result name = "success" type="code">
        				<param name="width">300</param>
        				<param name="height">50</param>
        			</result>
        		</action>
        	</package>

         2.写result-type类,继承 StrutsResultSupport 父类,重写doExecute()

            1.其中result-type类可以使用:
                ServletActionContext.getResponse().getOutputStream();
                直接获取response输出流对象，直接作用到页面上
            在访问action时，return到这个result-type类，而该类获取reponse对象
            进而作用到页面上

            2.其中result-type类可以使用：
                私有变量，并添加set方法，用于设置return-type对象
                private int width;
	            private int height;
                public void setWidth(int width) {
            		this.width = width;
            	}
            	public void setHeight(int height) {
            		this.height = height;
            	}
                而对应此,struts.xml上的<return>配置应该添加:
                    <param name="">xxx</param>
                    <param name="">xxx</param>
        
    1.同包下自定义result-type
    <!-- 自定义result-type,类继承StrutsResultSuppor,重写doExecute -->
    	<package name="p5" extends="struts-default" namespace="/p5">
    		<result-types>
    			<result-type name="code" class="cn.cxd.action.ValidCode"/>
    		</result-types>
    		<action name="hello0" class="cn.cxd.action.HelloAction3" method="doHello">
    			<result name = "success" type="code">
    				<param name="width">300</param>
    				<param name="height">50</param>
    			</result>
    			
    		</action>
    	</package>

    2.不同包下自定义result-type,需继承抽象包
    <package name="p6" extends="struts-default" abstract="true">
		<result-types>
			<result-type name="code" class="cn.cxd.action.ValidCode"/>
		</result-types>
	</package>
	
	<package name="p7" extends="p6" namespace="/p7">
		<action name="hello0" class="cn.cxd.action.HelloAction3" method="doHello">
			<result name = "success" type="code">
				<param name="width">300</param>
				<param name="height">50</param>
			</result>
		</action>
	</package>
