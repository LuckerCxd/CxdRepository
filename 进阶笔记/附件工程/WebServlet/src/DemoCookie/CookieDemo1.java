package DemoCookie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieDemo1")
public class CookieDemo1 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookie1 = new Cookie("username", "Àî°×");
		Cookie cookie2 = new Cookie("password", "123456");
		cookie1.setMaxAge(10);
		cookie2.setMaxAge(10);
		response.addCookie(cookie1);
		response.addCookie(cookie2);
//		cookie1.setPath("/");
//		cookie2.setDomain(".baidu.com");
		
		
		
		
		
	}

}
