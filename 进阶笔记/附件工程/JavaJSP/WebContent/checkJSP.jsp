<%@ page language="java" contentType="text/html; charset=gb2312"
    pageEncoding="gb2312"%>
<%@page import="cn.cxd.beans.ComparedUser"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="gb2312">
<title>Insert title here</title>
</head>
<body>

<%! String[] checkedNames = {"aa","bb","cc","dd"}; %>
<%
	String name = request.getParameter("name");
	if(name != null && name != ""){
		for(int i=0;i<checkedNames.length;i++){
			if(checkedNames[i].equals(name)){
				out.print("<script>alert('该昵称已存在...'); window.location='login.jsp' </script>");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				break;
			}
		}
	}
	ArrayList<ComparedUser> ul = new ArrayList<ComparedUser>();
	session.setAttribute("userList", ul);
	session.setAttribute("name", name);
	request.getRequestDispatcher("welcomegame.jsp").forward(request, response);
%>
</body>
</html>