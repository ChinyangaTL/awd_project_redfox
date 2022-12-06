<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Redfox | Register</title>
  <link rel="stylesheet" href="./styles/globalStyles.css">
  <link rel="stylesheet" href="./styles/components.css">
  <link rel="stylesheet" href="./styles/clientHome.css">

</head>
<body>
 <main>
   <form action="ClientController" method="POST">
     <input type="hidden" name="command" value="REGISTER" />
     <table>
       <tbody>
       <tr>
         <td><label>First Name:</label></td>
         <td><input type="text" name="firstName" /></td>
       </tr>

       <tr>
         <td><label>Last Name:</label></td>
         <td><input type="text" name="surname" /></td>
       </tr>

       <tr>
         <td><label>Email:</label></td>
         <td><input type="email" name="email" /></td>
       </tr>

       <tr>
         <td><label>Password:</label></td>
         <td><input type="password" name="password" /></td>
       </tr>
       <tr>
         <td><label>Confirm Password:</label></td>
         <td><input type="password" name="confirmPassword" /></td>
       </tr>

       <tr>
         <td><label></label></td>
         <td><input type="submit" value="Register" /></td>
       </tr>

       </tbody>
     </table>
     <a href="login.jsp">Have an account? Login</a> <br/>
     <a href="employee_login.jsp">Employee Login</a>
   </form>

 </main>

</body>
</html>
