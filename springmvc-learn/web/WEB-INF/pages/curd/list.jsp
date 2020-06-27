<%--
  Created by IntelliJ IDEA.
  User: 23516
  Date: 2020/6/15
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>显示员工信息</title>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/scripts/jquery-1.9.1.min.js">
    </script>
    <script type="text/javascript">
        $(function(){
            $(".delete").click(function () {
                var href =  $(this).attr("href");
                $("#deleteForm").attr("action", href).submit();
                return false;
            });
        })
    </script>
</head>
<body>
    <c:if test="${empty requestScope.employees}">
        没有任何员工信息
    </c:if>
    <c:if test="${! empty requestScope.employees}">
        <table border="1" cellpadding="10" cellspacing="0">
            <tr>
                <th>ID</th>
                <th>LastName</th>
                <th>Email</th>
                <th>Gender</th>
                <th>Department</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${requestScope.employees}" var="emp">
                <tr>
                    <td>${emp.id}</td>
                    <td>${emp.lastName}</td>
                    <td>${emp.email}</td>
                    <td>${emp.gender == 0 ? '女':'男'}</td>
                    <td>${emp.department.deptName}</td>
                    <td>
                        <a class="edit" href="${pageContext.request.contextPath}/curd/emp/${emp.id}">
                            Edit
                        </a>
                    </td>
                    <td>
                        <a class="delete" href="${pageContext.request.contextPath}/curd/emp/${emp.id}">
                            Delete
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <form id="deleteForm" method="post">
        <input type="hidden" name="_method" value="DELETE">
    </form>

    <br><br>
    <h4><a href="${pageContext.request.contextPath}/curd/emp">add Employee</a></h4>
    <br><br>
    <h4> add Employee(Convertor) LastName-Email-Gender-DepartmentId
        <br>
        例如：Mia-mia@qq.com-女-1
    </h4>
    <form:form action="${pageContext.request.contextPath}/curd/empConvertor">
        <input type="text" name="source"/>
        <input type="submit" value="Submit">
    </form:form>

</body>
</html>
