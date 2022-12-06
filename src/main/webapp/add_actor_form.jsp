<!DOCTYPE html>
<html>

<head>
    <title>Add Actor</title>

    <link rel="stylesheet" href="./styles/globalStyles.css">
    <link rel="stylesheet" href="./styles/components.css">
</head>

<body>
<main>


<div id="container">
    <h3>Add Actor</h3>

    <form action="EmployeeController" method="GET">

        <input type="hidden" name="command" value="ADD_ACTOR" />

        <table>
            <tbody>
            <tr>
                <td><label>First Name:</label></td>
                <td><input type="text" name="firstName" /></td>
            </tr>

            <tr>
                <td><label>Surname:</label></td>
                <td><input type="text" name="surname" /></td>
            </tr>

            <tr>
                <td><label>Date Of Birth:</label></td>
                <td><input type="text" name="dob" /></td>
            </tr>

            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save" /></td>
            </tr>

            </tbody>
        </table>
    </form>

    <div style="clear: both;"></div>

    <p>
        <a href="employee_dashboard.jsp">Back to List</a>
    </p>
</div>

</main>
</body>

</html>
