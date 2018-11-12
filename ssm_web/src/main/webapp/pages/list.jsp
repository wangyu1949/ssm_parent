<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/7
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table border="1px">
        <c:forEach items="${items}" var="item">
            <tr>
                <td width="200px">${item.name}</td>
                <td width="200px">${item.price}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
