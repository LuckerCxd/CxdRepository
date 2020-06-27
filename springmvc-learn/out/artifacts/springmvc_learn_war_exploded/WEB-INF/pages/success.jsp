<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Success</title>
    <fmt:message key="username"></fmt:message>
    <br>
    <fmt:message key="password"></fmt:message>
    <br>
    <spring:message code="username"></spring:message>
    <br>
    <spring:message code="password"></spring:message>
    <br>
    name : ${requestScope.name}
    <br>
    email : ${requestScope.email}
    <br>
    password : ${requestScope.password}
</head>
<body>
<h3>success</h3>
</body>
</html>
