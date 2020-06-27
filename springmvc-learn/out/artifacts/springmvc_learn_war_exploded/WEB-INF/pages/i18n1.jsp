<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%@ page import="java.util.Locale" %><%--
  Created by IntelliJ IDEA.
  User: 23516
  Date: 2020/6/25
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
        request.setAttribute("locale",locale);
    %>
    <fmt:message key="username"></fmt:message>
    <br>
    <a href="i18n2">i18n2</a>
    <br>
    <c:if test="${locale.toString() eq 'zh_CN'}">
        <a href="i18n1?locale=en_US">en_US</a>
    </c:if>
    <br>
    <c:if test="${locale.toString() eq 'en_US'}">
        <a href="i18n1?locale=zh_CN">zh_CN</a>
    </c:if>


</body>
</html>
