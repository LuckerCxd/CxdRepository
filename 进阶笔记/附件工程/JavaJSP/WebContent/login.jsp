<%@ page language="java" contentType="text/html; charset=gb2312"
	pageEncoding="gb2312"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="gb2312">
<title>登录界面</title>
</head>
<body>
	<form action="http://localhost:8080/JavaJSP/checkJSP.jsp" method="get" id="registerForm">
		<table align="center" border="1" cellspacing="0" width="230">
			<caption>用户登录</caption>
			<tr>
				<td><label for="inputName">昵称： </label></td>
				<td><input type="text" required="required" placeholder="输入昵称"
					name="name" id="inputName"></td>
			</tr>
			<tr>
				<td colspan = "2" >
					<input type = "submit"  value="确认" id = "submitForm">
				</td>
			</tr>
		</table>
	</form>
	<a href="sortedWinner.jsp" target="_blank">排行榜</a>

</body>
</html>