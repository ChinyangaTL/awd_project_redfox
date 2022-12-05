<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: les_miserables
  Date: 05/12/22
  Time: 02:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../styles/globalStyles.css">
    <link rel="stylesheet" href="../styles/components.css">
    <link rel="stylesheet" href="../styles/clientHome.css">
</head>
<body>
<%
    String[] categories = {"All", "Comedy", "Thriller", "Adventure"};
    request.setAttribute("categories", categories);
%>
<div className="btn-container">

    <c:forEach var="category" items="${categories}">
        <button
                type="button"
                className="filter-btn"
<%--                onClick={() => filterItems(category)}--%>
        >
        ${category}
        </button>
    </c:forEach>


</div>
</body>
</html>
