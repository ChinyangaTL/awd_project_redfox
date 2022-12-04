<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admins</title>
</head>
<body>
<c:forEach var="admin" items="${admin_list}">
  ${admin.email} <br/>
</c:forEach>
</body>
</html>
