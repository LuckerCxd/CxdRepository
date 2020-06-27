<%--
  Created by IntelliJ IDEA.
  User: 23516
  Date: 2020/6/2
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Hello world</title>
</head>
<body>

<a href="makeCustomException">makeCustomException</a>
<br><br>
<a href="testExceptionHandle">testExceptionHandle</a>
<br><br>
<form action="${pageContext.request.contextPath}/testUpload"
      method="post" enctype="multipart/form-data">
    upload-File: <input type="file" name="upload-file"/>
    upload-Desc: <input type="text" name="upload-desc"/>
    <input type="submit" value="Submit"/>
</form>
<br><br>
<a href="i18n1">i18n1</a>
<br><br>
<a href="helloWorld">helloWorld</a>
<br><br>
<a href="curd/testResponseEntity">download Jquery-1.9.1.min.js</a>
<br><br>
<br><br>
<form action="${pageContext.request.contextPath}/curd/testResponseBody"
      method="post" enctype="multipart/form-data">
    File: <input type="file" name="file"/>
    Desc: <input type="text" name="desc"/>
    <input type="submit" value="Submit"/>
</form>
<br><br>
<a href="curd/emps">curd/emps</a>
<br><br>
<a href="testRedirect">testRedirect</a>
<br><br>
<a href="testForward">testForward</a>
<br><br>
<a href="myCustomView">myCustomView</a>
<br><br>
<a href="straightMapping">straightMapping</a>
<br><br>
<form action="test/testModelAttribute" method="post">
    testModelAttribute--<br/>
    <input type="hidden" name="id" value="1">
    username :<input type="text" name="name" value="Mia">
    email :<input type="text" name="email" value="mia@qq.com">
    <input type="submit" value="submit">
</form>
<br>
<br>
<form action="test/ParamModel">
    paramModel--<br>
    username :<input type="text" name="name">
    password :<input type="text" name="password">
    email :<input type="text" name="email">
    <input type="submit" value="submit">
</form>
<br><br>
<form action="test/testMap" method="post">
    testMap--<br>
    username :<input type="text" name="name">
    password :<input type="text" name="password">
    email :<input type="text" name="email">
    <input type="submit" value="submit">
</form>

</body>
</html>
