<%@ page language="java" contentType="text/html; charset=gb2312"
    pageEncoding="gb2312"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="gb2312">
<title>Book Insert</title>
</head>
<body>


<h3>������ͼ����Ϣ��</h3>
<% if(request.getAttribute("result")!=null)
out.print(request.getAttribute("result"));
%>
<form action = "http://localhost:8080/BookStoreMVC/bookinsert.do" method = "post">
<table>
<tr><td>���</td> <td><input type="text" name="bookid" ></td></tr>
<tr><td>����</td><td><input type="text" name="title"></td></tr>
<tr><td>����</td><td><input type="text" name="author" ></td></tr>
<tr><td>������</td><td><input type="text" name="publisher" ></td></tr>
<tr><td>����</td><td><input type="text" name="price" ></td></tr>
<tr><td><input type="submit" value="ȷ��" ></td>
<td><input type="reset" value="����" ></td>
</tr>
</table>
</form>




</body>
</html>