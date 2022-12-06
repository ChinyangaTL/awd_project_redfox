<!DOCTYPE html>
<html>

<head>
    <title>Add Employee</title>

    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="./styles/globalStyles.css">
    <link rel="stylesheet" href="./styles/components.css">
    <link rel="stylesheet" href="./styles/clientHome.css">
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>

<body>
<main>
    <div id="container">
        <h3>Add Employee</h3>

        <form action="AdminController" method="GET">

            <input type="hidden" name="command" value="ADD_EMPLOYEE" />

            <table>
                <tbody>
                <tr>
                    <td><label>Name:</label></td>
                    <td><input required type="text" name="name" /></td>
                </tr>

                <tr>
                    <td><label>Email:</label></td>
                    <td><input required type="text" name="email" /></td>
                </tr>

                <tr>
                    <td><label>Password:</label></td>
                    <td><input required type="text" name="password" /></td>
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
</main>

</body>

</html>











