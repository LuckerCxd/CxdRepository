<%@ page language="java" contentType="text/html; charset=gb2312"
    pageEncoding="gb2312"%>
<jsp:useBean id="book" class="cn.cd.beans.BookBean" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="gb2312">
<title>��ѯ���</title>
</head>
<body>


��ţ�<jsp:getProperty name="book" property="bookid"/>
������<jsp:getProperty name="book" property="title"/>
���ߣ�<jsp:getProperty name="book" property="author"/>
�����磺<jsp:getProperty name="book" property="publisher"/>
�۸�<jsp:getProperty name="book" property="price"/>




</body>
</html>