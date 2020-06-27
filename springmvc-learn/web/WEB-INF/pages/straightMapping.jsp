
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    show i18n
    <fmt:message key="username"></fmt:message>
    <br>
    <fmt:message key="password"></fmt:message>
    <br>
    <spring:message code="username"></spring:message>
    <br>
    <spring:message code="password"></spring:message>
    <br>
</body>
</html>
