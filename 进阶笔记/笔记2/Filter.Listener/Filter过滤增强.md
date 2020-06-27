Filter过滤词汇(增强返回)

    1.首先Filter使用注解设置拦截路径，将工程下的所有资源请求都拦下来
    2.登录Servlet，在URL上传送数据，并在Servlet上的Post或是Get上
        对传送来的参数获取,可使用request.getParameterMap(),
        getParameter()...方法获取到URL上传递过来的参数，
        之后就是对request使用获取参数的特定方法，
        在Filter过滤器中作 返回值 增强屏蔽
    3.在Filter init(),使用filterConfig获取servletContext,
        再获取敏感文件的真实路径，并读取出来。
    4.在Filter中使用代理模式，doFilter中 动态代理 参数为request,
        因为在Servlet中使用request.getParamter...来
        获取URL上的参数传递，
    5.注意：ServletRequest也有getParamter..
        不用强转为HttpServletRequest,在动态代理的invoke中，
        使用method.getName()判断被执行的函数名称
    6.如果判断为正是Servlet上Request所执行的获取参数的方法，
        那么就先method.invoke(真实对象，args),获取其返回值，
        再对该返回值判断是否包含contains步骤3中的敏感词汇，并替换掉，
        也就是获取返回值->增强返回值。
    7.如果判断为不是Servlet上Request所执行的获取参数的方法，
        那么就先method.invoke(真实对象，args),获取其返回值，
        直接返回出method.invoke(真实对象，args)
    8.在最后，不管判断有没成功，都要放行，
        注意:chain.doFilter(代理Request对象,response);
        这样才能起到增强返回值的功能，一定不能是真实对象。











FilterDemo1:
-
    package DemoFilter;
    
    import java.io.BufferedReader;
    import java.io.FileNotFoundException;
    import java.io.FileReader;
    import java.io.IOException;
    import java.lang.reflect.InvocationHandler;
    import java.lang.reflect.Method;
    import java.lang.reflect.Proxy;
    import java.util.ArrayList;
    import java.util.List;
    
    import javax.servlet.DispatcherType;
    import javax.servlet.Filter;
    import javax.servlet.FilterChain;
    import javax.servlet.FilterConfig;
    import javax.servlet.ServletException;
    import javax.servlet.ServletRequest;
    import javax.servlet.ServletResponse;
    import javax.servlet.annotation.WebFilter;
    import javax.servlet.http.HttpServletRequest;
    
    import JavaBeanClass.User;
    
    //1.首先Filter使用注解设置拦截路径，将工程下的所有资源请求都拦下来
    @WebFilter(value = {"/*"})
    public class FilterDemo1 implements Filter {
    	
    	public void destroy() {
    		System.out.println("recycle resource");
    	}

    	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    		response.setContentType("text/html;charset=utf-8");
    		request.setCharacterEncoding("utf-8");
    		System.out.println("filter..");
    //		request.getParameterMap();
    //		request.getParameterValues(name);
    //		request.getParameter(name);
    		
    		//创建代理对象，作增强返回值，而不是增强参数
    		ServletRequest proxyReq = (ServletRequest) Proxy.newProxyInstance(request.getClass().getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler() {
    
    			@Override
    			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    				
    				if(method.getName().equals("getParameter")){
    					String value = (String) method.invoke(request, args);
    					
    					for (String string : list) {
    //						System.out.println("ls: "+string);
    						if(value.contains(string)) {
    							value = value.replaceAll(string, "xxx");
    						}
    					}
    					return value;
    				}else {
    					return method.invoke(request, args);
    				}
    				
    			}
    		});
    		
    		//注意:chain.doFilter(代理Request对象,response);
    		chain.doFilter(proxyReq, response);
    		
    	}
    	
    	
    	
    	List<String> list = new ArrayList<String>();
    	public void init(FilterConfig fConfig) throws ServletException {
            //获取敏感文件的真实路径，并读取出来。

    		String realPath = fConfig.getServletContext().getRealPath("/敏感词汇.txt");
    		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(realPath));) {
    			String line = null;
    			while((line = bufferedReader.readLine())!= null) {
    				list.add(line);
    			}
    			for (String string : list) {
    				System.out.println(string);
    			}
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		
    	}
    }

ServletDemo1:
-
    package DemoFilter;
    
    import java.io.IOException;
    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    
    import JavaBeanClass.User;
    
    /**
     * Servlet implementation class ServletDemo1
     */
    @WebServlet("/ServletDemo1")
    public class ServletDemo1 extends HttpServlet {
    	private static final long serialVersionUID = 1L;
           
      
    
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		doPost(request, response);
    		
    	}
    
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		
    		String parameter = request.getParameter("msg");
    		response.getWriter().write("msg: "+parameter );
    		System.out.println("msg: "+parameter);
    	}
    
    }

URL:

    http://localhost:8080/WebFilter/ServletDemo1?msg=猪头，你好吗