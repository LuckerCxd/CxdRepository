<%@page import="java.util.Locale"%>
<%@page import="java.util.ResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>

<s:i18n name="cn.cxd.web.actions.I18NActionDemo">

<form action="${pageContext.request.contextPath}/p1/i18n" method="get">
<s:text name="login.username"/> <input type="text" name="username" >
<br/>
<s:text name="login.password"/> <input type="text" name="password" >
<br/>
<input type="submit" value= <s:text name="login.submit"/>>
</form>

</s:i18n>





</body>
</html>