<%@ page language="java" contentType="text/html; charset=gb2312"
	pageEncoding="gb2312"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="gb2312">
<title>��¼����</title>
</head>
<body>
	<form action="http://localhost:8080/JavaJSP/checkJSP.jsp" method="get" id="registerForm">
		<table align="center" border="1" cellspacing="0" width="230">
			<caption>�û���¼</caption>
			<tr>
				<td><label for="inputName">�ǳƣ� </label></td>
				<td><input type="text" required="required" placeholder="�����ǳ�"
					name="name" id="inputName"></td>
			</tr>
			<tr>
				<td colspan = "2" >
					<input type = "submit"  value="ȷ��" id = "submitForm">
				</td>
			</tr>
		</table>
	</form>
	<a href="sortedWinner.jsp" target="_blank">���а�</a>

</body>
</html>