<%@page import="java.util.Locale"%>
<%@page import="java.util.ResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
	ResourceBundle bundle = ResourceBundle.getBundle("resources.message",Locale.US);
%>

<form action="${pageContext.request.contextPath}/p1/i18n" method="get">
<%=bundle.getString("login.username") %> <input type="text" name="username" >
<br/>
<%=bundle.getString("login.password") %> <input type="text" name="password" >
<br/>
<input type="submit" value=<%=bundle.getString("login.submit") %> >
</form>


</body>
</html>