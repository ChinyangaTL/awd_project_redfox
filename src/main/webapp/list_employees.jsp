
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employees</title>
    <link rel="stylesheet" href="./styles/globalStyles.css">
    <link rel="stylesheet" href="./styles/components.css">
    <link rel="stylesheet" href="./styles/employees.css">
</head>
<body>
<main>
    <section class="section-center">
        <c:forEach var="employee" items="${employee_list}">
            <article class='employee-item'>
                <p class='title'>${employee.name}</p>
                <p class='title'>${employee.email}</p>
                <div class='btn-container'>
                    <button
                            type='button'
                            class='edit-btn'
                    >
                        Edit
                    </button>
                    <button
                            type='button'
                            class='delete-btn'

                    >
                        Delete
                    </button>
                </div>
            </article>
        </c:forEach>
    </section>
</main>

</body>
</html>
