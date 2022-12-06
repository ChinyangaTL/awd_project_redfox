<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>


<%--INSERT INTO movie (id, movieTitle, genre, releaseDate, img, description) VALUES (--%>
<%--1, "Se7en", "Thriller", "September 22, 1995", "https://m.media-amazon.com/images/M/MV5BZDgyZmY5MmItY2I3Ny00NjA4LWFhNmItMGQ4ZGJhZDk5YjU3XkEyXkFqcGdeQXVyMTAzMDM4MjM0._V1_.jpg", "lorem ipsum"--%>
<%--)--%>
<body>
    <form action="EmployeeController" method="GET">

    <input type="hidden" name="command" value="ADD_MOVIE" />

    <table>
        <tbody>
        <tr>
            <td><label>Movie Title:</label></td>
            <td><input type="text" name="movieTitle" /></td>
        </tr>

<%--        <tr>--%>
<%--            <select name="genre" id="genre">--%>
<%--                <option value="Thriller">Thriller</option>--%>
<%--                <option value="Comedy">Comedy</option>--%>
<%--                <option value="Adventure">Adventure</option>--%>
<%--                <option value="Horror">Horror</option>--%>
<%--            </select>--%>
<%--        </tr>--%>

        <tr>
            <td><label>Genre:</label></td>
            <td><input type="text" name="genre" /></td>
        </tr>

        <tr>
            <td><label>Director:</label></td>
            <td><input type="text" name="director" /></td>
        </tr>

        <tr>
            <td><label>Cast Member:</label></td>
            <td><input type="text" name="castMember1" /></td>
        </tr>

        <tr>
            <td><label>Cast Member:</label></td>
            <td><input type="text" name="castMember2" /></td>
        </tr>

        <tr>
            <td><label>Cast Member:</label></td>
            <td><input type="text" name="castMember3" /></td>
        </tr>



        <tr>
            <td><label>ReleaseDate:</label></td>
            <td><input type="text" name="releaseDate" /></td>
        </tr>

        <tr>
            <td><label>Image Link URL:</label></td>
            <td><input type="text" name="img" /></td>
        </tr>

        <tr>
            <td><label>Description:</label></td>
            <td><textarea id="description" name="description"  rows="4" >

                </textarea></td>
        </tr>

        <tr>
            <td><label></label></td>
            <td><input type="submit" value="Save" class="save" /></td>
        </tr>

        </tbody>
    </table>
</form>
</body>
</html>
