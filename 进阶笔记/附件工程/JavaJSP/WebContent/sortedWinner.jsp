<%@page import="java.util.Collections"%>
<%@page import="java.util.Collection"%>
<%@page import="cn.cxd.beans.ComparedUser"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=gb2312"
    pageEncoding="gb2312"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="gb2312">
<title>排行榜</title>
</head>
<body>

<%
    List<ComparedUser> uList = (List<ComparedUser>)session.getAttribute("userList");
	String name = (String)session.getAttribute("name");
	int count = Integer.parseInt(request.getParameter("count"));
	uList.add(new ComparedUser(name,count));
	Collections.sort(uList);
	int length = uList.size();
	for(int i = 0;i<length;i++){
		out.write(uList.get(i).toString()+"</br>");
	}
	
	
%>
</body>
</html>