<%--
  Created by IntelliJ IDEA.
  User: 23516
  Date: 2020/3/23
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>

<%--<form action="${pageContext.servletContext.contextPath}/user/registerAnno1.do">
    <input type="text" placeholder="请输入用户名" required="required" name="username" value="luckerCxd">
    <input type="text" placeholder="清输入密码" required="required" name="password" value="123">
    <input type="checkbox" name="hobbies" value="1">喝可乐
    <input type="checkbox" name="hobbies" value="2">喝茶
    <input type="checkbox" name="hobbies" value="3">喝白开水
    <input type="submit" value="提交">
</form>--%>

<%--<form action="${pageContext.servletContext.contextPath}/user/registerAnno2.do">
    <input type="text" placeholder="请输入用户名" required="required" name="username" value="luckerCxd">
    <input type="text" placeholder="清输入密码" required="required" name="password" value="123">
    <input type="checkbox" name="hobbies" value="1" checked="checked">喝可乐
    <input type="checkbox" name="hobbies" value="2" checked="checked">喝茶
    <input type="checkbox" name="hobbies" value="3" checked="checked">喝白开水
    <input type="submit" value="提交">
</form>--%>


<form action="${pageContext.servletContext.contextPath}/user/registerAnno3.do">
    <input type="text" placeholder="请输入用户名" required="required" name="user.username" value="luckerCxd">
    <input type="text" placeholder="清输入密码" required="required" name="user.password" value="123">
    <input type="checkbox" name="user.hobbies" value="1" checked="checked">喝可乐
    <input type="checkbox" name="user.hobbies" value="2" checked="checked">喝茶
    <input type="checkbox" name="user.hobbies" value="3" checked="checked">喝白开水
    <input type="submit" value="提交">
</form>

<%--<form action="${pageContext.servletContext.contextPath}/user/registerAnno3.do">
    <input type="text" placeholder="请输入用户名" required="required" name="o.username" value="luckerCxd">
    <input type="text" placeholder="清输入密码" required="required" name="o.password" value="123">
    <input type="checkbox" name="o.hobbies" value="1" checked="checked">喝可乐
    <input type="checkbox" name="o.hobbies" value="2" checked="checked">喝茶
    <input type="checkbox" name="o.hobbies" value="3" checked="checked">喝白开水
    <input type="submit" value="提交">
</form>--%>

<%--<form action="${pageContext.servletContext.contextPath}/user/registerAnno5.do">
    <input type="text" placeholder="请输入用户名" required="required" name="user.username" value="luckerCxd">
    <input type="text" placeholder="清输入密码" required="required" name="user.password" value="123">
    <input type="checkbox" name="hobbies[0]" value="1" checked="checked">喝可乐
    <input type="checkbox" name="hobbies[1]" value="2" checked="checked">喝茶
    <input type="checkbox" name="hobbies[2]" value="3" checked="checked">喝白开水
    <input type="submit" value="提交">
</form>--%>

</body>
</html>
