<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" errorPage="cookieLog.jsp" isELIgnored="false"%>
<%@ include file="top.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>主页home </h1>
<%--<% int i = 3/0; out.write(i); --%>

<% pageContext.setAttribute("name","bitter"); %>
<%
	out.write(pageContext.getAttribute("name").toString());
%>
</body>
</html>