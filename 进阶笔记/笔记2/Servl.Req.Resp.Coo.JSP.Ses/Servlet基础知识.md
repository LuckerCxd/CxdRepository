Servlet基础知识：

在eclipse使用dynamicWEBproject：

	0.在eclipse 中部署Tomcat	
	1.配置Servlet:（Eclipse环境）
	2.建包和创建类，用于实现Servlet接口，重写一系列方法
	3.配置
	4.访问网页
	5.简介HttpServlet,GenericServlet
	
	
0.在eclipse 中部署Tomcat	

		Window/Preference/Server/Runtime Environment -> Add -> Apache Tomcat xx 
			-> next -> brower -> finish
		检测：
			建立Dynamic Web Project工程，再创建jsp文件，body中写入文本
		使用浏览器访问：
			http://localhost:8080/工程名称/xxx.jsp
		例如：
			http://localhost:8080/TryTom/NewFile.jsp
		如果正常访问，则部署成功
			

1.配置Servlet:（Eclipse环境）

	0.建立Dynamic Web Project工程，
		建立的第三步选上：Generate web.xml deployment descriptor
		

	1.此时实际工程结构：
		-ProjectName..
			-src
				-xxxPackages..
					-xxxClassName..
			-build
			-WebContent
				-META-INF
				-WEB-INF
					-lib
				   (-web.xml）
				-xxx.html
		
		web.xml配置时class: 
		  <servlet-class>xxxPackages.xxxClassName</servlet-class>
		在WebContent下的web.xml中进行配置。		
			

2.建包和创建类，用于实现Servlet接口，重写一系列方法

	Servlet->GenericServlet->HttpServlet
	接口			抽象类			抽象类

	1.void destroy()   					//用于资源回收，仅执行一次
	2.ServletConfig getServletConfig()
	3.String getServletInfo()		
	4.void init(ServletConfig config)	//资源申请，仅执行一次
	5.void service(ServletRequest req, ServletResponse res) 
					//用于服务，每一次访问都会执行

3.配置：

	方式：
		1.web.xml中配置
		2.注解配置（在Java6以后可以提供,如果使用这种方式,则建工程时不要xml文件）
	
	具体：
	1.web.xml中配置
		在<web-app>标签体中</web-app>填入以下信息：

			<servlet>
			  <servlet-name>demo1</servlet-name>
			  <servlet-class>DemoServlet.Servdemo</servlet-class>
			 </servlet>
			  
			 <servlet-mapping>
			  	<servlet-name>demo1</servlet-name>
			  	<url-pattern>/demo1</url-pattern>
			 </servlet-mapping>


		解释：
		Tomcat服务器的实际行为：
			由于：在浏览器的地址栏输入：localhost:8080/ProjectName/资源路径
			其中的资源路径：
				正是 <servlet-mapping> 中的<url-pattern>中的路径
			1.通过url-pattern中的资源路径访问网页，并通过映射找到servlet-name中的名称
			2.通过servlet-name中的名称在<servlet>标签中找到这个名称对应的servlet-class(完整包名路径)
			3.通过该路径可以加载这个路径指向的类，并创建该类对象，
				之后再创建Request,Response对象，并传入该类对象的service方法，再执行。


	2.注解配置（在Java6以后可以提供）
		使用注释配置时，不必创建web.xml，甚至可以说是不要创建它
		在类上标注 @WebServlet("/demo1") 即可 //demo1为设置的资源路径
		
		

4.访问网页：

	1.首先使用Debug运行类中的代码..可以看见日志信息
	2.使用浏览器输入：
		http://localhost:8080/工程名/资源路径
		
		例如：
			http://localhost:8080/TryTom/demo1

		
GenericServlet:实现了Servlet接口，方法空实现，继承该类后仅需重写service方法

	1.To write a generic servlet, you need only override 
		the abstract service method.
	2.public abstract class GenericServlet extends java.lang.Object
		implements Servlet, ServletConfig, java.io.Serializable

	
GenericServlet_src:
-

	public abstract class GenericServlet implements Servlet, ServletConfig,
    java.io.Serializable {

	    private static final long serialVersionUID = 1L;
	
	    private transient ServletConfig config;
	
	    public GenericServlet() {
	        // NOOP
	    }
	
	    @Override
	    public void destroy() {
	        // NOOP by default
	    }
	
	    @Override
	    public String getInitParameter(String name) {
	        return getServletConfig().getInitParameter(name);
	    }
	
	    @Override
	    public Enumeration<String> getInitParameterNames() {
	        return getServletConfig().getInitParameterNames();
	    }
	
	    
	    @Override
	    public ServletConfig getServletConfig() {
	        return config;
	    }
	
	    
	    @Override
	    public ServletContext getServletContext() {
	        return getServletConfig().getServletContext();
	    }
	
	 
	    @Override
	    public String getServletInfo() {
	        return "";
	    }
	
	    
	    @Override
	    public void init(ServletConfig config) throws ServletException {
	        this.config = config;
	        this.init();
	    }
	
	    
	    public void init() throws ServletException {
	        // NOOP by default
	    }
	

	    public void log(String msg) {
	        getServletContext().log(getServletName() + ": " + msg);
	    }
	
	    public void log(String message, Throwable t) {
	        getServletContext().log(getServletName() + ": " + message, t);
	    }
	
	    @Override
	    public abstract void service(ServletRequest req, ServletResponse res)
	            throws ServletException, IOException;
	
	    @Override
	    public String getServletName() {
	        return config.getServletName();
	    }
	}


HttpServlet：实现了Servlet接口，继承该类后至少重写一种方法

	Provides an abstract class to be subclassed to create an HTTP servlet suitable for a Web site. A subclass of HttpServlet must override at least one method, usually one of these:
		1.doGet, if the servlet supports HTTP GET requests
		2.doPost, for HTTP POST requests
		3.doPut, for HTTP PUT requests
		4.doDelete, for HTTP DELETE requests
		5.init and destroy, to manage resources that are held for the life of the servlet
		6.getServletInfo, which the servlet uses to provide information about itself
	
	解释：
		service 会被判断网页的行为，并根据行为执行各自对应的方法，
			因此重写这些行为即可满足需求
	
HttpServlet_src:
-
	protected void service(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {

        String method = req.getMethod();

        if (method.equals(METHOD_GET)) {
            long lastModified = getLastModified(req);
            if (lastModified == -1) {
                // servlet doesn't support if-modified-since, no reason
                // to go through further expensive logic
                doGet(req, resp);
            } else {
                long ifModifiedSince;
                try {
                    ifModifiedSince = req.getDateHeader(HEADER_IFMODSINCE);
                } catch (IllegalArgumentException iae) {
                    // Invalid date header - proceed as if none was set
                    ifModifiedSince = -1;
                }
                if (ifModifiedSince < (lastModified / 1000 * 1000)) {
                    // If the servlet mod time is later, call doGet()
                    // Round down to the nearest second for a proper compare
                    // A ifModifiedSince of -1 will always be less
                    maybeSetLastModified(resp, lastModified);
                    doGet(req, resp);
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                }
            }

        } else if (method.equals(METHOD_HEAD)) {
            long lastModified = getLastModified(req);
            maybeSetLastModified(resp, lastModified);
            doHead(req, resp);

        } else if (method.equals(METHOD_POST)) {
            doPost(req, resp);

        } else if (method.equals(METHOD_PUT)) {
            doPut(req, resp);

        } else if (method.equals(METHOD_DELETE)) {
            doDelete(req, resp);

        } else if (method.equals(METHOD_OPTIONS)) {
            doOptions(req,resp);

        } else if (method.equals(METHOD_TRACE)) {
            doTrace(req,resp);

        } else {
            //
            // Note that this means NO servlet supports whatever
            // method was requested, anywhere on this server.
            //

            String errMsg = lStrings.getString("http.method_not_implemented");
            Object[] errArgs = new Object[1];
            errArgs[0] = method;
            errMsg = MessageFormat.format(errMsg, errArgs);

            resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, errMsg);
        }
    }
		
post:提交表单可以展示出来；get:访问时可以展示出来

xxx.html
-


	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	</head>
	<body>
		<form action="http://localhost:8080/WebServlet/demo3" method = "post">
		<label for = "user">登录:</label><input id = "user" name = "username" placeholder="输入用户名"></input>
		<br/>
		<label for = "pwd">密码:</label><input id = "pwd" type = "password" name = "password" placeholder="输入密码"></input> 
		<br/>
		<input type = "submit" value = "提交"></input>
		</form>
	</body>
	</html>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="http://localhost:8080/WebServlet/demo3" method = "post">
	<label for = "user">登录:</label><input id = "user" name = "username" placeholder="输入用户名"></input>
	<br/>
	<label for = "pwd">密码:</label><input id = "pwd" type = "password" name = "password" placeholder="输入密码"></input> 
	<br/>
	<input type = "submit" value = "提交"></input>
	</form>
</body>
</html>



xxx.java
-

	@WebServlet("/demo3")
	public class HttpServDemo extends HttpServlet {
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("get it");
		}
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("post it");
		}
	}

