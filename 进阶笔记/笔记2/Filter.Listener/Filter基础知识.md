Filter基础知识：
	
	1.过滤器用途
	2.执行过程
	3.使用步骤




过滤器用途：一般用于完成通用的操作，统一编码，检测登录状态等

执行过程:	
	
	1.通过配置信息，确定了拦截路径url-Pattern,并为之绑定了name,
		在根据name查找出Class,反射创建Filter对象。
	2.Filter对象执行init()，完成资源的分配，只执行一次
	3.Filter对象在每次碰见拦截路径的资源请求时，执行doFilter()，
		doFilter()可以获取其request共享的信息，再选择是放行：
		chain.doFilter(request, response);
		或是转往别的页面。
		在选择放行后，该资源会再一次经过该过滤器，一般是使用Response
		对该资源进行加工
	3.Filter对象执行destroy()，完成资源的释放，只执行一次
	
	如果有多个Filter过滤器，组合成一条过滤链：
	如果是注解配置：就按照类名的字符串大小由小到大进行过滤
	如果是xml配置：就按照<Filter-mapping>先后顺序来过滤



使用步骤：

	1.建包和创建类，用于实现Servlet接口，重写一系列方法
	2.配置


1.建包和创建类，用于实现Filter接口，重写一系列方法
	

FilterDemo1:
-	
	//@WebFilter(value = "/*")
	public class FilterDemo1 implements Filter {
		
		public void destroy() {
			System.out.println("recycle resource");
		}
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			System.out.println("Filter1 start");
			chain.doFilter(request, response);
			System.out.println("Filter1 back");
		}
	
		public void init(FilterConfig fConfig) throws ServletException {
			System.out.println("dispatch resource");
		}
	
	}

2.配置：

	1.方式：
		1.web.xml中配置
		2.注解配置
	
		具体：
		1.web.xml中配置
			在<web-app>标签体中</web-app>填入以下信息：
			相比起：servlet配置上，url-pattern 代表的是 拦截路径
			*.jsp : 后缀为jsp的都要经过 过滤器Filter
	
				<filter>
			  	<filter-name>FilterDemo1</filter-name>
			  	<filter-class>DemoFilter.FilterDemo1</filter-class>
			  	</filter>
			  	
			  	<filter-mapping>
			  	<filter-name>FilterDemo1</filter-name>
			  	<url-pattern>*.jsp</url-pattern>
			  	</filter-mapping>
	
		2.注解配置
			使用注释配置时，不必创建web.xml，甚至可以说是不要创建它
			在类上标注 @WebFilter(value = "/*") 即可
				 // value 是默认值-默认url-pattern:
					/* 代表所有的资源路径都要经过 过滤器Filter 拦截路径
			
			
	2.配置的基本选项：
	
		1.String[] urlPatterns  拦截路径
		2.DispatcherType[] dispatcherTypes() 
				default {DispatcherType.REQUEST};拦截方式
				
	
拦截路径：（仅为当前工程下的拦截，不能联用，也不能"../"去往别的工程）

	所有拦截			/*:			访问所有资源都会被拦截
	后缀名拦截：		*.jsp:      访问所有后缀为jsp的资源都会被拦截
	目录拦截：		/user/*		访问user目录下所有资源都会被拦截
	具体资源拦截：	/index.jsp	访问index.jsp的资源会被拦截

举例：

	@WebFilter(value = {"/user/*"})
	public class FilterDemo1 implements Filter {
		
		public void destroy() {
			System.out.println("recycle resource");
		}
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			System.out.println("Filter1 start");
			chain.doFilter(request, response);
			System.out.println("Filter1 back");
		}
	
		public void init(FilterConfig fConfig) throws ServletException {
			System.out.println("dispatch resource");
		}
	}

拦截方式：
	
	public enum DispatcherType {
	    FORWARD,	//转发
	    INCLUDE,	//包含
	    REQUEST,	//浏览器直接请求
	    ASYNC,		//异步
	    ERROR		//EL的错误跳转
	}
	
举例：
	
	@WebFilter(value = {"/*"},dispatcherTypes= {DispatcherType.FORWARD})
	public class FilterDemo1 implements Filter {
		
		public void destroy() {
			System.out.println("recycle resource");
		}
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			System.out.println("Filter1 start");
			chain.doFilter(request, response);
			System.out.println("Filter1 back");
		}
	
		public void init(FilterConfig fConfig) throws ServletException {
			System.out.println("dispatch resource");
		}
	}




import javax.servlet.annotation.WebFilter;
-
	package javax.servlet.annotation;
	
	import java.lang.annotation.Documented;
	import java.lang.annotation.ElementType;
	import java.lang.annotation.Retention;
	import java.lang.annotation.RetentionPolicy;
	import java.lang.annotation.Target;
	
	import javax.servlet.DispatcherType;
	
	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public @interface WebFilter {
	
	    /**
	     * @return description of the Filter, if present
	     */
	    String description() default "";
	
	    /**
	     * @return display name of the Filter, if present
	     */
	    String displayName() default "";
	
	    /**
	     * @return array of initialization params for this Filter
	     */
	    WebInitParam[] initParams() default {};
	
	    /**
	     * @return name of the Filter, if present
	     */
	    String filterName() default "";
	
	    /**
	     * @return small icon for this Filter, if present
	     */
	    String smallIcon() default "";
	
	    /**
	     * @return the large icon for this Filter, if present
	     */
	    String largeIcon() default "";
	
	    /**
	     * @return array of Servlet names to which this Filter applies
	     */
	    String[] servletNames() default {};
	
	    /**
	     * A convenience method, to allow extremely simple annotation of a class.
	     *
	     * @return array of URL patterns
	     * @see #urlPatterns()
	     */
	    String[] value() default {};
	
	    /**
	     * @return array of URL patterns to which this Filter applies
	     */
	    String[] urlPatterns() default {};
	
	    /**
	     * @return array of DispatcherTypes to which this filter applies
	     */
	    DispatcherType[] dispatcherTypes() default {DispatcherType.REQUEST};
	
	    
	    boolean asyncSupported() default false;
	}


		


	
web.xml基本框架：
-
	<?xml version="1.0" encoding="UTF-8"?>
	<web-app version="4.0" id="WebApp_ID" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	
	<display-name>WebFilter</display-name>
	
	
	<welcome-file-list>
	
	<welcome-file>index.html</welcome-file>
	
	<welcome-file>index.htm</welcome-file>
	
	<welcome-file>index.jsp</welcome-file>
	
	<welcome-file>default.html</welcome-file>
	
	<welcome-file>default.htm</welcome-file>
	
	<welcome-file>default.jsp</welcome-file>
	
	</welcome-file-list>
	
	</web-app>