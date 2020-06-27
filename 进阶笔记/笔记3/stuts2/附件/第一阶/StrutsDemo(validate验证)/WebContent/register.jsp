<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<s:head/>
</head>
<body>
<s:debug/>

<s:form action="/p1/actiondemo" method="GET">
	<s:textfield name="username" label="用户名"/>
	<s:password  name="password" label="密码"/>
	<s:password name="rePassword" label="确认密码"/>
	<s:textfield name="email" label="邮箱"/>
	<s:textfield name="age" label="年龄"/>
	<s:textfield name="score" label="成绩"/>
	<s:textfield name="url" label="个人主页介绍"/>
	<s:submit value="提交注册"></s:submit>
</s:form>

<s:actionerror/>

</body>
</html>