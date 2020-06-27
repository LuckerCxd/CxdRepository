package DemoFilter;

import java.io.IOException;

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
				
				req.getSession().setAttribute("tip", "Î´µÇÂ¼ÇëÏÈµÇÂ¼");
				request.getRequestDispatcher("/login/login.jsp").forward(request, response);
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("dispatch resource");
	}
}
