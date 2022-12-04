<!DOCTYPE html>
<html>

<head>
    <title>Add Student</title>

    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>

<body>

<div id="container">
    <h3>Add Employee</h3>

    <form action="AdminController" method="GET">

        <input type="hidden" name="command" value="ADD_EMPLOYEE" />

        <table>
            <tbody>
            <tr>
                <td><label>Name:</label></td>
                <td><input type="text" name="name" /></td>
            </tr>

            <tr>
                <td><label>Email:</label></td>
                <td><input type="text" name="email" /></td>
            </tr>

            <tr>
                <td><label>Password:</label></td>
                <td><input type="text" name="password" /></td>
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
        <a href="AdminController">Back to List</a>
    </p>
</div>
</body>

</html>











