<%@ page language="java" contentType="text/html; charset=gb2312"
    pageEncoding="gb2312"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="gb2312">
<title>��¼�ɹ�</title>
</head>
<body>
<%
	String name = (String)session.getAttribute("name");
	out.println("<h3>��ӭ�����"+name+"</h3>");
%>
<a href="game.jsp" target="_blank">С��Ϸ</a>
<hr/>
<a href="sortedWinner.jsp" target="_blank">���а�</a>
</body>
</html>