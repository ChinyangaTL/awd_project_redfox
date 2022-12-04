<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<main>
    <section class="section-center">
        <input type="button" value="Add Employee"
               onclick="window.location.href='add-employee-form.jsp'; return false;"
        />

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
