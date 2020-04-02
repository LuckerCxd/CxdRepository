<%--
  Created by IntelliJ IDEA.
  User: 23516
  Date: 2020/3/25
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>用户anan列表</title>
</head>
<body>

    <c:forEach items="${userList}" var="user">
        ${user.id}----用户名：${user.username}-----密码：${user.password}--------爱好：${user.hobbies}</br>
    </c:forEach>
</body>
</html>
