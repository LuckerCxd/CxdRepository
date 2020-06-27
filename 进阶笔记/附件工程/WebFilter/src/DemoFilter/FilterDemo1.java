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


@WebFilter(value = {"/*"})
public class FilterDemo1 implements Filter {
	
	public void destroy() {
		System.out.println("recycle resource");
	}
	
	/*
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		HttpServletRequest req = (HttpServletRequest)request;
		String requestURI = req.getRequestURI();
		System.out.println(requestURI);
		if(requestURI.contains("/login/") || requestURI.contains("/LoginServlet")|| requestURI.contains("/css/") || requestURI.contains("/js/") || requestURI.contains("/fonts/")) {
			chain.doFilter(request, response);
		}else {
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
	*/
	
	
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
		
		
		chain.doFilter(proxyReq, response);
		
	}
	
	
	
	List<String> list = new ArrayList<String>();
	public void init(FilterConfig fConfig) throws ServletException {
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
