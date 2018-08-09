
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>车辆信息</title>
</head>
<body>
<%--    <c:if test="${!empty carList}">
        <c:forEach var="car" items="${carList}">
            姓名：${car.code} &nbsp;&nbsp;手机号：${car.code} &nbsp;&nbsp;邮箱：${car.code} &nbsp;&nbsp;<br>

        </c:forEach>
    </c:if>--%>


    <c:out value="${carList}" escapeXml="true" default="默认值"></c:out><br/>

</body>
</html>
