<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.Date"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 

	boolean flag = false;
	SimpleDateFormat sDateFormat = null;
	Cookie[] cookies = request.getCookies();
	response.setHeader("content-type", "text/html;charset=utf-8");
	if(cookies != null) {
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals("LastTimeLoggin")) {
				//已经登录过的操作
				String key,value;
				response.getWriter().write("<h1>已经成功登录:"+"</br>");
				for (Cookie c : cookies) {
					key = c.getName();
					if(key.equals("username") || key.equals("LastTimeLoggin")) {
						value = URLDecoder.decode(c.getValue(),"utf-8");
						response.getWriter().write(key +" = "+value+"</br>");
					}
				}
				response.getWriter().write("</h1>");
				
				//response完成过后,进行cookie设置
				cookie = new Cookie("username", URLEncoder.encode("李白","utf-8"));
				sDateFormat = new SimpleDateFormat("yyyy年MM月dd日       HH:mm:ss");
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
		//未登录过，进行cookie设置
		Cookie cookie = new Cookie("username", URLEncoder.encode("李白","utf-8"));
		sDateFormat = new SimpleDateFormat("yyyy年MM月dd日       HH:mm:ss");
		String logginTime = sDateFormat.format(new Date());
		String encodeLog = URLEncoder.encode(logginTime,"utf-8");
		Cookie cookie1 = new Cookie("LastTimeLoggin", encodeLog);
		
		cookie.setMaxAge(10);
		cookie1.setMaxAge(10);
		response.addCookie(cookie);
		response.addCookie(cookie1);
		
		//设置完并非可以立即request获得，而是经过服务器转送才获取到
		cookies = request.getCookies();
		String key,value;
		response.getWriter().write("<h1>第一次登录!"+"</br>");
	}
	
%>



<%! String name = "谢逊"; %>
<%= request.getServletContext().getRealPath(".") %>




</body>
</html>