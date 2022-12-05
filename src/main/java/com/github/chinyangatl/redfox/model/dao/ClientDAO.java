package com.github.chinyangatl.redfox.model.dao;

import com.github.chinyangatl.redfox.model.beans.*;
import com.github.chinyangatl.redfox.utils.SQLStatements;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    private DataSource dataSource;

    public ClientDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String login(String email, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQLStatements.QUERY_USER_ACCOUNT);

            statement.setString(1, email);
            statement.setString(2, password);


            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                System.out.println("Success");
                System.out.println(resultSet.getString("role"));
                return "Success";
            }

            return "No account found";

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection, statement, null);
        }
    }

    public String register(String firstName, String surname, String email, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQLStatements.REGISTER_USER);

            statement.setString(1, firstName);
            statement.setString(2, surname);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setString(5, "user");


            int affectedRows = statement.executeUpdate();

            if(affectedRows ==1) {

                return "Success";
            }

            return "No account found";

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection, statement, resultSet);
        }
    }

    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLStatements.GET_MOVIES);

            while (resultSet.next()){
                int movieId = resultSet.getInt("id");
                String movieTitle = resultSet.getString("movieTitle");
                String genre = resultSet.getString("genre");
                String releaseDate = resultSet.getString("releaseDate");
                float rating = resultSet.getFloat("rating");
                String imgUrl = resultSet.getString("img");
                String description = resultSet.getString("description");

                Movie movie = new Movie(movieId, movieTitle, genre, releaseDate, rating, imgUrl, description);


                Director director = getMovieDirector(movieId);
                movie.setDirector(director);

                List<Actor> actors = getMovieCast(movieId);
                movie.setActors((ArrayList<Actor>) actors);

                movies.add(movie);
            }
            return movies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection, statement, resultSet);
        }

    }

    private Director getMovieDirector(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Director> directors = new ArrayList<>();

        try {
            connection = dataSource.getConnection();

            statement = connection.prepareStatement(SQLStatements.GET_MOVIE_DIRECTOR);

            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if(resultSet.next()) {
                int directorId = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String surname = resultSet.getString("surname");
                String dob = resultSet.getString("dob");

                return new Director(directorId, firstName, surname, dob);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection, statement, null);
        }
    }

    private List<Actor> getMovieCast(int movieId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Actor> actors = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQLStatements.GET_MOVIE_ACTORS);
            statement.setInt(1, movieId);
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
//                int actorId = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String surname = resultSet.getString("surname");
                String dob = resultSet.getString("dob");

                Actor actor = new Actor(25, firstName, surname, dob);
                actors.add(actor);
            }
            return actors;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection, statement, null);
        }
    }

    private void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if(connection != null) {
                connection.close();
            }
            if(statement != null) {
                connection.close();
            }
            if(resultSet != null) {
                resultSet.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}







