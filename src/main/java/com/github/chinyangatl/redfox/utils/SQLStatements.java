package com.github.chinyangatl.redfox.utils;

public class SQLStatements {
    public static final String GET_EMPLOYEES = "SELECT * FROM employee";
    public static final String ADD_EMPLOYEE = "INSERT INTO employee (name, email, password) VALUES (?, ?, ?)";
    public static final String QUERY_ADMIN_ACCOUNT = "select * from admin where email = ? and password = ?";

    public static final String QUERY_EMPLOYEE_ACCOUNT = "select * from employee where email = ? and password = ?";

    public static final String QUERY_USER_ACCOUNT = "select * from user where email = ? and password = ?";

    public static final String REGISTER_USER = "INSERT INTO user VALUES(?, ?, ?, ?, ?)";

    public static final String GET_MOVIES = "SELECT * FROM movie";

    public static final String GET_MOVIE_DIRECTOR = "SELECT director.id, director.firstName, director.surname, director.dob from movie_director \n" +
            "INNER JOIN director on movie_director.directorId = director.id\n" +
            "INNER JOIN movie on movie_director.movieId = ?";

    public static final String GET_MOVIE_ACTORS = "SELECT actor.firstName, actor.surname, actor.dob from movie_actor\n" +
            "INNER JOIN actor on movie_actor.actorId = actor.id\n" +
            "INNER JOIN movie on movie_actor.movieId = ?";

    public static final String ADD_MOVIE = "INSERT INTO movie (movieTitle, genre, releaseDate, rating, img, description) VALUES(?, ?, ?, ?, ?, ?)";

    public static final String ADD_ACTOR = "INSERT INTO actor (firstname, surname, dob) VALUES(?, ?, ?)";

    public static final String ADD_DIRECTOR = "INSERT INTO director (firstname, surname, dob) VALUES(?, ?, ?)";

    public static final String QUERY_ACTOR = "SELECT id FROM actor WHERE firstName = ? AND surname = ?";

    public static final String QUERY_DIRECTOR = "SELECT id FROM director WHERE firstName = ? AND surname = ?";

    public static final String QUERY_MOVIE = "SELECT id FROM movie WHERE movieTitle = ? AND releaseDate = ?";

    public static final String LINK_MOVIE_DIRECTOR = "INSERT INTO movie_director VALUES(?, ?);";

    public static final String LINK_MOVIE_ACTOR = "INSERT INTO movie_actor VALUES(?, ?);";
}
