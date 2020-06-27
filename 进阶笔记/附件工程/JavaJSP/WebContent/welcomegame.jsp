<%@ page language="java" contentType="text/html; charset=gb2312"
    pageEncoding="gb2312"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="gb2312">
<title>登录成功</title>
</head>
<body>
<%
	String name = (String)session.getAttribute("name");
	out.println("<h3>欢迎，玩家"+name+"</h3>");
%>
<a href="game.jsp" target="_blank">小游戏</a>
<hr/>
<a href="sortedWinner.jsp" target="_blank">排行榜</a>
</body>
</html>