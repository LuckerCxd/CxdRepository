<%@ page language="java" contentType="text/html; charset=gb2312"
    pageEncoding="gb2312"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="gb2312">
<title>Book Query</title>
</head>
<body>

请输入一个书号:<br>
<form action="http://localhost:8080/BookStoreMVC/bookquery.do" method = "post">
<input type="text" name="bookid"><br>
<input type="submit" value="提交">
</form>


</body>
</html>