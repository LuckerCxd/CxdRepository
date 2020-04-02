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

    用户名： ${username} <br/>
    密码： ${password} <br/>

    <c:if test="${not empty hobbies}" >
        爱好：
        <c:if test="${hobbies[0] eq '1'}">
            喝可乐
            <c:if test="${hobbies[1] eq '2'}">
                ,喝茶
            </c:if>
            <c:if test="${hobbies[2] eq '3'}">
                ,喝白开水
            </c:if>
        </c:if>

        <c:if test="${hobbies[0] eq '2'}">
            喝茶
            <c:if test="${hobbies[1] eq '3'}">
                ,喝白开水
            </c:if>
        </c:if>

        <c:if test="${hobbies[0] eq '3'}">
            喝白开水
        </c:if>
    </c:if>
    <br/>

    ${user}
    <br/>
    ${userVoPOJO}
    <br/>
    ${userVoArray}
    <br/>
    ${userVoList}
</body>
</html>
