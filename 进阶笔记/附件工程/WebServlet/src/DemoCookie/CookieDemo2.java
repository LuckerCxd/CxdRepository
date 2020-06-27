package DemoCookie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieDemo2")
public class CookieDemo2 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("content-type", "text/html;charset=utf-8");
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			String key,value;
			for (Cookie cookie : cookies) {
				key = cookie.getName();
				value = cookie.getValue();
				response.getWriter().write(key +" = "+value+"</br>");
			}
		}
	}

}
