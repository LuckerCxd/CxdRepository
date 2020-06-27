<%@ page language="java" contentType="text/html; charset=gb2312"
    pageEncoding="gb2312"%>
<jsp:useBean id="book" class="cn.cd.beans.BookBean" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="gb2312">
<title>查询结果</title>
</head>
<body>


书号：<jsp:getProperty name="book" property="bookid"/>
书名：<jsp:getProperty name="book" property="title"/>
作者：<jsp:getProperty name="book" property="author"/>
出版社：<jsp:getProperty name="book" property="publisher"/>
价格：<jsp:getProperty name="book" property="price"/>




</body>
</html>