Action类获取Request,Response等对象

    1.获取方式：(两种),以及这些获取方式的的实现
    2.获取的对象分析
    3.这两种方法的分析

[1.获取方式：(两种),以及这些获取方式的的实现]()
-
    获取方式：(两种)
        1.ServletActionContext类的静态方法获取
            getPageContext()
            getRequest()
            getResponse()
            getServletContext()
            ...
        2.Action类实现ServletRequestAware,ServletResponseAware等接口,依赖于拦截器
    实现：HelloAction4

HelloAction4:
-
    package cn.cxd.action;
    
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    
    import org.apache.struts2.StrutsStatics;
    import org.apache.struts2.interceptor.ServletResponseAware;
    
    import com.opensymphony.xwork2.ActionContext;
    import com.opensymphony.xwork2.ActionSupport;
    
    //1.Action获取Request,Response等对象
    public class HelloAction4 extends ActionSupport 
    		implements ServletResponseAware,
    				StrutsStatics{
    	
    	private HttpServletRequest request = null;
    	private HttpServletResponse response = null;
    	
    	public String showApi() { 
    		/*
    		
    		// 第一种方式:ServletActionContext类的静态方法获取
    		HttpServletRequest request = ServletActionContext.getRequest();
    		HttpServletResponse response = ServletActionContext.getResponse();
    		PageContext pageContext = ServletActionContext.getPageContext();
    		HttpSession session = request.getSession();
    		System.out.println("request: "+request);
    		System.out.println("response: "+response);
    		System.out.println("pageContext: "+pageContext);
    		System.out.println("session: "+session);
    		return NONE;
    		
    		*/
    		
    		// 第一种方式的内部实现
    		HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(HTTP_REQUEST);
    		System.out.println("request: "+request);
    		
    	    // 第二种方式在于Action类实现了ServletResponseAware接口,
            // 并重写setServletResponse方法，拦截器会自动处理，调用该方法
            // 我们仅仅是获取即可
    		System.out.println("response: "+response);
    		return NONE;
    	}
    
    	@Override
    	public void setServletResponse(HttpServletResponse response) {
    		this.response = response;
    		
    	}
    }

[2.获取的对象分析]()
-

1.对于request对象: org.apache.struts2.dispatcher.StrutsRequestWrapper@1c1c0e4

    我们可以发现这是包含在Struts的Jar包，对其进一步分析：
    这个StrutsRequestWrapper类继承了HttpServletRequestWrappe父类
    而该父类也是包含于Tomcat的：
        import javax.servlet.http.HttpServletRequestWrapper;
    进一步可以发现：HttpServletRequestWrapper 继承了ServletRequestWrapper类，
        并实现了HttpServletRequest 接口 
    而 ServletRequestWrapper类，同样是包含于Tomcat的
        public interface HttpServletRequest extends ServletRequest
        public class ServletRequestWrapper implements ServletRequest
    总的来说：
        struts    -->    tomcat   
        
2.对于response对象: org.apache.catalina.connector.ResponseFacade@3990582b

    我们可以发现这是包含在Tomcat中的Jar包,对其进一步分析：
    这个ResponseFacade类 实现 HttpServletResponse接口
    而该父类也是包含于Tomcat的：
        import javax.servlet.http.HttpServletResponse;
    进一步可以发现：HttpServletResponse 继承了ServletResponse接口
    总的来说：
        tomcat   
    

[3.这两种方法的分析]()
-
<h3>1.ServletActionContext类的静态方法获取：</h3>

    内部实现：ServletActionContext->ActionContext + StrutsStatics
    观察源码：
        public class ServletActionContext extends ActionContext 
                  implements StrutsStatics 
    可以发现：
        ServletActionContext 继承了 ActionContext 类 并实现 StrutsStatics接口
    仅对两个方法观察：
    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) ActionContext.getContext().get(HTTP_REQUEST);
    }

    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) ActionContext.getContext().get(HTTP_RESPONSE);
    }
    
    发现这个方法，使用了ActionContext.getContext()静态方法和get(String)方法
    public class ActionContext implements Serializable
        static ThreadLocal<ActionContext> actionContext = new ThreadLocal<ActionContext>();
        
        //使用线程来获取ActionContext实例
        public static ActionContext getContext() {
            return actionContext.get();
        }
        
        //而在创建AcitonContext实例时，就需要传入Map
        public ActionContext(Map<String, Object> context) {
            this.context = context;
        }
        public Object get(String key) {
            return context.get(key);
        }
    
    而在StrutsStatics接口中定义了一系列常量字符串，用于反射获取对象实例
        public static final String HTTP_REQUEST = "com.opensymphony.xwork2.dispatcher.HttpServletRequest";
        
        public static final String HTTP_RESPONSE = "com.opensymphony.xwork2.dispatcher.HttpServletResponse";
        
        public static final String SERVLET_DISPATCHER = "com.opensymphony.xwork2.dispatcher.ServletDispatcher";
        
        public static final String SERVLET_CONTEXT = "com.opensymphony.xwork2.dispatcher.ServletContext";
        
        public static final String PAGE_CONTEXT = "com.opensymphony.xwork2.dispatcher.PageContext";
        
        public static final String STRUTS_PORTLET_CONTEXT = "struts.portlet.context";
        
        public static final String STRUTS_ACTION_TAG_INVOCATION= "struts.actiontag.invocation";

<h3>2.实现 ServletRequestAware,ServletResponseAware接口,依赖拦截器</h3>

    @Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}

1.查看struts-default.xml中的拦截器，发现：

	<interceptor-ref name="servletConfig"/>	
    <interceptor name="servletConfig"       class="org.apache.struts2.interceptor.ServletConfigInterceptor"/>

2.观察org.apache.struts2.interceptor.ServletConfigInterceptor类：
   可以发现：对action类，查看类的所属关系，并会自动调用我们重写的set..()方法

    public String intercept(ActionInvocation invocation) throws Exception {
        Object action = invocation.getAction();
        
        //这里ActionContext实例的获取方式并不同于第一种
        ActionContext context = invocation.getInvocationContext();
        
        //对action类，查看类的所属关系，并会自动调用我们重写的set..()方法
        if (action instanceof ServletRequestAware) {
          HttpServletRequest request = (HttpServletRequest)context.get("com.opensymphony.xwork2.dispatcher.HttpServletRequest");
          ((ServletRequestAware)action).setServletRequest(request);
        } 
        
        if (action instanceof ServletResponseAware) {
          HttpServletResponse response = (HttpServletResponse)context.get("com.opensymphony.xwork2.dispatcher.HttpServletResponse");
          ((ServletResponseAware)action).setServletResponse(response);
        } 
        
        if (action instanceof ParameterAware) {
          ((ParameterAware)action).setParameters(context.getParameters());
        }
        
        if (action instanceof ApplicationAware) {
          ((ApplicationAware)action).setApplication(context.getApplication());
        }
        
        if (action instanceof SessionAware) {
          ((SessionAware)action).setSession(context.getSession());
        }
        
        if (action instanceof RequestAware) {
          ((RequestAware)action).setRequest((Map)context.get("request"));
        }
        
        if (action instanceof PrincipalAware) {
          HttpServletRequest request = (HttpServletRequest)context.get("com.opensymphony.xwork2.dispatcher.HttpServletRequest");
          if (request != null)
          {
            ((PrincipalAware)action).setPrincipalProxy(new ServletPrincipalProxy(request));
          }
        } 
        if (action instanceof ServletContextAware) {
          ServletContext servletContext = (ServletContext)context.get("com.opensymphony.xwork2.dispatcher.ServletContext");
          ((ServletContextAware)action).setServletContext(servletContext);
        } 
        return invocation.invoke();
      }