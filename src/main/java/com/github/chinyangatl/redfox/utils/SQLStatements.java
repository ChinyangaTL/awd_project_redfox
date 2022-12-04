package com.github.chinyangatl.redfox.utils;

public class SQLStatements {
    public static final String GET_EMPLOYEES = "SELECT * FROM employee";
    public static final String ADD_EMPLOYEE = "INSERT INTO employee (name, email, password) VALUES (?, ?, ?)";
    public static final String QUERY_ADMIN_ACCOUNT = "select * from admin where email = ? and password = ?";

    public static final String GET_MOVIES = "SELECT * FROM movie";

    public static final String GET_MOVIE_DIRECTOR = "SELECT director.id, director.firstName, director.surname, director.dob from movie_director \n" +
            "INNER JOIN director on movie_director.directorId = director.id\n" +
            "INNER JOIN movie on movie_director.movieId = ?";

    public static final String GET_MOVIE_ACTORS = "SELECT actor.firstName, actor.surname, actor.dob from movie_actor\n" +
            "INNER JOIN actor on movie_actor.actorId = actor.id\n" +
            "INNER JOIN movie on movie_actor.movieId = ?";
}
