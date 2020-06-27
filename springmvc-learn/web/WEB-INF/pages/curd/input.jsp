<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: 23516
  Date: 2020/6/15
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>添加新员工</title>
</head>
<body>
    <form:errors path="*"></form:errors>
    <%--
        这里的modelAttribute对应的就是在目标方法中存入到map的对象的key，
        表单中直接使用的path属性对应的就是这个key对应的value对象的成员变量的名称
    --%>
    <form:form action="${pageContext.request.contextPath}/curd/emp" method="POST"
               modelAttribute="employee">
        <!-- 根据存入map的employee对象的id属性是否为null来判断，如果是null的话就是新增-->
        <c:if test="${employee.id == null}">
            LastName: <form:input path="lastName"/>
            <form:errors path="lastName"></form:errors>
        </c:if>
        <c:if test="${employee.id != null}">
            <h3>UPDATE EE</h3>
            <input type="hidden" name="id" value="${employee.id}">
            <input type="hidden" name="_method" value="PUT">
        </c:if>
        <br>

        <%--
            radiobuttons：单选框
            这个字段对应目标方法存入到map的genders,这个genders是map，items就是genders
        --%>

        Gender: <form:radiobuttons path="gender" items="${genders}"/>
        <br>
        Email: <form:input path="email"/>
        <form:errors path="email"></form:errors>
        <br>
        <%--
            select多选框
            department.deptId对应的是form表的modelAttribute属性的对象的department成员变量的deptId成员变量
            items就是departments
            itemLabel就是需要显示的选择内容：departments里的department对象的deptName成员变量
            itemValue就是需要传入的选择内容：departments里的department对象的deptId成员变量
        --%>
        Department:
        <form:select path="department.deptId" items="${departments}" itemLabel="deptName" itemValue="deptId"/>
        <br>
        Birth: <form:input path="birth"/>
        <form:errors path="birth"></form:errors>
        <br>
        Salary: <form:input path="salary"/>
        <form:errors path="salary"></form:errors>
        <br>
        <input type="submit" value="Submit">
    </form:form>
</body>
</html>
