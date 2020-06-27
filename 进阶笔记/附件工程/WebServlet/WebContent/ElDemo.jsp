<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="JavaBeanClass.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



<%--  

<% request.setAttribute("name", "RR"); session.setAttribute("name", "SS");%>
<% User user = new User();
	user.setName("李白");
	user.setPassword("123456");
	user.setLoginTime(new Date());
	session.setAttribute("us", user);
%>
${name}
${requestScope.name}
${sessionScope.name}<br/>what time is?<br/>
${sessionScope.us.logTimeStr} 

--%>
 


 <%-- 
<%

	Map map = new HashMap();
	map.put("name", "李太白");
	map.put("age", 19);
	session.setAttribute("mip",map);

%>
<br/>
${sessionScope.ls[0]}
<br/>
${sessionScope.mip["name"]}
<br/>
${sessionScope.mip.name}
${pageContext.request.contextPath}




<% session.setAttribute("number", 9); %>
<c:choose>
<c:when test="${sessionScope.number == 4}"> it is 4</c:when>
<c:when test="${sessionScope.number == 3}"> it is 3</c:when>
<c:when test="${sessionScope.number == 6}"> it is 6</c:when>
<c:when test="${sessionScope.number == 7}"> it is 7</c:when>
<c:otherwise> it is sb</c:otherwise>
</c:choose>

<c:forEach var="i" step="1" begin="0" end="10" varStatus="s">

${i}
${s.index}
${s.count}
<br/>
</c:forEach>

--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%  List list = new  ArrayList();
	list.add("abc");
	list.add("aec");
	list.add("avc");
	list.add("aec");
	session.setAttribute("list", list);
%>


<table align = "center" border="1px solid black">
<tr>
<th>编号</th>
<th>名称</th>
</tr>

<c:forEach items="${sessionScope.list}" varStatus="ls">
<c:if test="${ls.count % 2 == 1}">
<tr bgcolor = "pink">
<td>${ls.count}</td>
<td>${list[ls.index]}</td>
</tr>
</c:if>

<c:if test="${ls.count % 2 != 1}">
<tr bgcolor="blue">
<td>${ls.count}</td>
<td>${list[ls.index]}</td>
</tr>
</c:if>

</c:forEach>

</table>


</body>
</html>