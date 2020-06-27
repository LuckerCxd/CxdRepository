package DemoCookie;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieDemo3")
public class CookieDemo3 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag = false;
		SimpleDateFormat sDateFormat = null;
		
		Cookie[] cookies = request.getCookies();
		response.setHeader("content-type", "text/html;charset=utf-8");
		if(cookies != null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("LastTimeLoggin")) {
					//�Ѿ���¼���Ĳ���
					String key,value;
					response.getWriter().write("<h1>�Ѿ��ɹ���¼:"+"</br>");
					for (Cookie c : cookies) {
						key = c.getName();
						if(key.equals("username") || key.equals("LastTimeLoggin")) {
							value = URLDecoder.decode(c.getValue(),"utf-8");
							response.getWriter().write(key +" = "+value+"</br>");
						}
					}
					response.getWriter().write("</h1>");
					
					//response��ɹ���,����cookie����
					cookie = new Cookie("username", URLEncoder.encode("���","utf-8"));
					sDateFormat = new SimpleDateFormat("yyyy��MM��dd��       HH:mm:ss");
					String logginTime = sDateFormat.format(new Date());
					String encodeLog = URLEncoder.encode(logginTime,"utf-8");
					Cookie cookie1 = new Cookie("LastTimeLoggin", encodeLog);
					
					cookie.setMaxAge(10);
					cookie1.setMaxAge(10);
					response.addCookie(cookie);
					response.addCookie(cookie1);
					flag = true;
					break;
				}
			}
		}
		
		if(!flag) {
			//δ��¼��������cookie����
			Cookie cookie = new Cookie("username", URLEncoder.encode("���","utf-8"));
			sDateFormat = new SimpleDateFormat("yyyy��MM��dd��       HH:mm:ss");
			String logginTime = sDateFormat.format(new Date());
			String encodeLog = URLEncoder.encode(logginTime,"utf-8");
			Cookie cookie1 = new Cookie("LastTimeLoggin", encodeLog);
			
			cookie.setMaxAge(10);
			cookie1.setMaxAge(10);
			response.addCookie(cookie);
			response.addCookie(cookie1);
			
			//�����겢�ǿ�������request��ã����Ǿ���������ת�ͲŻ�ȡ��
			cookies = request.getCookies();
			String key,value;
			response.getWriter().write("<h1>��һ�ε�¼!"+"</br>");
		}
	}
}
