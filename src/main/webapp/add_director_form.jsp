<!DOCTYPE html>
<html>

<head>
    <title>Add Director</title>

    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>

<body>

<div id="container">
    <h3>Add Director</h3>

    <form action="EmployeeController" method="GET">

        <input type="hidden" name="command" value="ADD_DIRECTOR" />

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
                <td><label>Date of Birth:</label></td>
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
        <a href="AdminController">Back to List</a>
    </p>
</div>
</body>

</html>
