Filter伪权限登录验证：

    需要的部件：
    
        1.filter
        2.jsp
        3.servlet
        附带：JavaBean类 User类

注意：
    
    1.当Filter将request,response请求转发到.jsp时，.jsp任能使用EL获取数据，
    或是使用JSP内置对象设置或获取属性，但是表单的提交是不会带上request的，
    即便在.jsp中将属性设置好，在提交表单后的servlet仍然不可见request，而是新的request
    2.但是如果是当Filter将request,response请求转发到其他servlet时，则request是相同的，是同一个request.
    

1.filter

    1.Filter过滤器对所有资源都进行拦截
    2.在doFilter方法中，
        1.先对Request,Response进行统一编码，以及其他的servlet,jsp等资源的统一配置
        2.再将ServletRequest强转为HttpServletRequest,并获取其URI
        3.通过URI字符串是否包含(contains)指定目录名或是文件名，直接chain.doFilter放行
            文件夹的contains:("/xxx/")
            文件的contains("/xxx");
            在访问与登录有关的资源时，包括jsp,css,fonts,js都要直接放行
            其他的要检测session部分的共享信息
        4.session上获取的对象为空，则未登录上，那么就要将请求转发到登录页面
            否则放行
        注意：
            在每一次chain.doFilter(request, response);放行后，不接其他代码
            表示请求回来时也不做其他加工。


1.FilterDemo1
-    
    package DemoFilter;
    
    import java.io.IOException;
    import java.lang.reflect.InvocationTargetException;
    import java.util.Map;
    
    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import javax.websocket.Session;
    
    import org.apache.commons.beanutils.BeanUtils;
    
    import JavaBeanClass.User;
    //1.Filter过滤器对所有资源都进行拦截
    @WebFilter(value = {"/*"})
    public class FilterDemo1 implements Filter {
    	
    	public void destroy() {
    		System.out.println("recycle resource");
    	}
    	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    		//2.1先对Request,Response进行统一编码，以及其他的servlet,jsp等资源的统一配置
            response.setContentType("text/html;charset=utf-8");
    		request.setCharacterEncoding("utf-8");
    		
            //2.2再将ServletRequest强转为HttpServletRequest,并获取其URI
    		HttpServletRequest req = (HttpServletRequest)request;
    		String requestURI = req.getRequestURI();
    		System.out.println(requestURI);

            //2.3通过URI字符串是否包含(contains)指定目录名或是文件名，直接chain.doFilter放行
    		if(requestURI.contains("/login/") || requestURI.contains("/LoginServlet")|| requestURI.contains("/css/") || requestURI.contains("/js/") || requestURI.contains("/fonts/")) {
    			chain.doFilter(request, response);
    		}else {
                
                //2.4session上获取的对象为空，则未登录上，那么就要将请求转发到登录页面,否则放行
    			User user = (User)req.getSession().getAttribute("user");
    			if(user != null) {
    				chain.doFilter(request, response);
    				System.out.println(user.getUsername());
    				System.out.println(user.getPassword());
    			}else {
    				
    				req.getSession().setAttribute("tip", "未登录请先登录");
    				request.getRequestDispatcher("/login/login.jsp").forward(request, response);
    			}
    		}
    	}
    
    	public void init(FilterConfig fConfig) throws ServletException {
    		System.out.println("dispatch resource");
    	}
    }

2.jsp:  表单登录，设定方法为Post,提交到伪数据库查询登录Servlet

2.login.jsp
-
    <%@ page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"%>
    <!DOCTYPE html>
    	<html lang="zh-CN">
    	  <head>
    	    <meta charset="ISO-8859-1">
    	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    	    <meta name="viewport" content="width=device-width, initial-scale=1">
    	    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    	    <title>Bootstrap 101 Template</title>
    	    <link href="../css/bootstrap.min.css" rel="stylesheet">
    	    <style type="text/css">
    	    .place{
    	    	font-size: 30px;
    	    	border: 2px solid black;
    	    	padding-top: 100px;
    	    	padding-bottom: 100px;
    	    }
    	    .form-group{
    	    	align-content: center;
    	    	margin-top: 40px;
    	    }
    	    .login{font-size: 25px;}
    	    </style>
    	    
    		<!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    	    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    	    <!--[if lt IE 9]>
    	      <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    	      <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    	    <![endif]-->
    	  </head>
    	  <body>
    	    <form action="http://localhost:8080/WebFilter/LoginServlet" class = "form-horizontal place"  method = "post">
    			<div class="form-group">
    				<label for = "user"  class = "col-sm-5 control-label" >登录:</label>
    				<div class = "col-sm-5">
    				<input id = "user" name = "username" placeholder="输入用户名"></input>
    				</div>
    		 	</div>	
    		  <div class="form-group">
    				<label for = "pwd" class = "col-sm-5 control-label">密码:</label> 
    				<div class = "col-sm-5">
    					<input id = "pwd" type = "password" name = "password" placeholder="输入密码"></input>
    				</div>
    		  </div>
    		  
    	   		<div class = "col-sm-5 form-group"> </div>
    	   		<button type="submit" class="btn btn-danger col-sm-3 login" >登录</button>
    		</form>
    		
    		
    		<div class = "col-sm-5 form-group">
    		
    		${sessionScope.tip}
    		
    		
    		</div>
    		
    		
    		
    	    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    	    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    	    <script src="../js/bootstrap.min.js"></script>
    	  </body>
    	</html>


3.servlet
    
    1.表单提交时，会将jsp上的信息附带在request上的参数上，使用map获取：
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
    2.使用jar包并导入：
        commons-beanutils-1.8.0.jar
        commons-logging-1.2.jar
    3.自动setter或是手动setter都可以：
        自动：（需要：JavaBean类的成员对象和login.jsp的name能对应得上）
            BeanUtils.populate(user, map);
        
        手动：
            user.setName(map.get("username")[0]);
            user.setPassword(map.get("password")[0]);
3.LoginServlet
-
    package DemoFilter;
    import java.io.IOException;
    import java.lang.reflect.InvocationTargetException;
    import java.util.Map;
    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import javax.websocket.Session;
    import org.apache.commons.beanutils.BeanUtils;
    import JavaBeanClass.User;

    @WebServlet("/LoginServlet")
    public class LoginServlet extends HttpServlet {
    	private static final long serialVersionUID = 1L;
     
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		doPost(request, response);
    		
    	}
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		Map<String, String[]> map = request.getParameterMap();
    		System.out.println("username:"+map.get("username")[0]);
    		System.out.println("password:"+map.get("password")[0]);
    		
    		User user = new User();
    		try {
    			BeanUtils.populate(user, map);
    			System.out.println("BUusername:"+user.getUsername());
    			System.out.println("BUpassword:"+user.getPassword());
    //			手动设置
    //			user.setName(map.get("username")[0]);
    //			user.setPassword(map.get("password")[0]);;
    		} catch (IllegalAccessException | InvocationTargetException e) {
    			e.printStackTrace();
    		}
    		//相当于直接当做数据库中有此数据
    		if(true) {
    			request.getSession().setAttribute("user",user);
    			request.getRequestDispatcher("/ServletDemo1").forward(request,response);
    		}
    	}
    
    }


User:
-
package JavaBeanClass;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
	private String username;
	private String password;
	private int age;
	private Date loginTime;
	public User() {
	}
	
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogTimeStr() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		return simpleDateFormat.format(loginTime);
	}
	
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", age=" + age + ", loginTime=" + loginTime + "]";
	}
	
}

