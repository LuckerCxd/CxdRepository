<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags"  prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<s:head/>
</head>
<s:fielderror></s:fielderror>
<body>
	
	<s:form action="/p9/hello1" method="GET">
	<s:textfield name="username" label="用户名"  requiredLabel="true" requiredPosition="left"></s:textfield>
	<s:password name="password" label="密码"  showPassword="true" requiredLabel="true" requiredPosition="left" > </s:password>
	<s:textfield name="birthday" label="出生日期"></s:textfield>
	<s:radio list="#{'true':'是' , 'false':'否'} "  name="adult" label="成年"></s:radio>
	<s:submit value="提交"></s:submit>
	</s:form>
	
	<hr/>
	<form action="${pageContext.request.contextPath}/p9/hello1"  method = "GET">
		<label for = "user" >用户名:</label> 
		<input id = "user" type = "text" name = "username" placeholder="输入用户名"></input>
		<br/>
		<label for = "pwd" >密码:</label> 
		<input id = "pwd" type = "password" name = "password" placeholder="输入密码"></input>
		<br/>
		
		<label for = "birthday" >出生:</label> 
		<input id = "birthday" type = "text" name = "birthday" placeholder="输入年月日"></input>
		<br/>
		<label for = "adult" >成年:</label> 
		是<input id = "adult" type = "radio" name = "adult" value = "true"></input>
		否<input id = "adult" type = "radio" name = "adult" value = "false"></input>
		<br/>
		<input type="submit" value ="登录">
	</form>
</body>
</html>