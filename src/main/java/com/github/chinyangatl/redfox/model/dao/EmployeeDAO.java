package com.github.chinyangatl.redfox.model.dao;

import com.github.chinyangatl.redfox.model.beans.Actor;
import com.github.chinyangatl.redfox.model.beans.Director;
import com.github.chinyangatl.redfox.model.beans.Employee;
import com.github.chinyangatl.redfox.model.beans.Movie;
import com.github.chinyangatl.redfox.utils.SQLStatements;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private DataSource dataSource;

    public EmployeeDAO(DataSource dataSource) {
        this.dataSource = dataSource;
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

    public int addActor(Actor actor) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();

            statement = connection.prepareStatement(SQLStatements.ADD_ACTOR, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, actor.getFirstName());
            statement.setString(2, actor.getLastName());
            statement.setString(3, actor.getDob());

            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                statement.setString(1, actor.getFirstName());
                statement.setString(2, actor.getLastName());
                statement.setString(3, actor.getDob());
                int affectedRows = statement.executeUpdate();

                if(affectedRows != 1) {
                    throw new SQLException("Couldn't insert actor");
                }

                ResultSet generatedKeys = statement.getGeneratedKeys();
                if(generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Couldn't get id for actor");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection, statement, null);
        }
    }

    public int addDirector(Director director) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();

            statement = connection.prepareStatement(SQLStatements.ADD_DIRECTOR, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, director.getFirstName());
            statement.setString(2, director.getLastName());
            statement.setString(3, director.getDob());

            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                statement.setString(1, director.getFirstName());
                statement.setString(2, director.getLastName());
                statement.setString(3, director.getDob());
                int affectedRows = statement.executeUpdate();

                if(affectedRows != 1) {
                    throw new SQLException("Couldn't insert director");
                }

                ResultSet generatedKeys = statement.getGeneratedKeys();
                if(generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Couldn't get id for director");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection, statement, null);
        }
    }

    public int addMovie(Movie movie) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();

            statement = connection.prepareStatement(SQLStatements.ADD_MOVIE, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, movie.getMovieTitle());
            statement.setString(2, movie.getGenre());
            statement.setString(3, movie.getReleaseDate());
            statement.setFloat(4, movie.getRating());
            statement.setString(5, movie.getImgUrl());
            statement.setString(6, movie.getDescription());

            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                statement.setString(1, movie.getMovieTitle());
                statement.setString(2, movie.getGenre());
                statement.setString(3, movie.getReleaseDate());
                statement.setFloat(4, movie.getRating());
                statement.setString(5, movie.getImgUrl());
                statement.setString(6, movie.getDescription());

                int affectedRows = statement.executeUpdate();

                if(affectedRows != 1) {
                    throw new SQLException("Couldn't insert movie");
                }

                ResultSet generatedKeys = statement.getGeneratedKeys();
                if(generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Couldn't get id for movie");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection, statement, null);
        }
    }

    public void linkMovieAndDirector(Movie movie, Director director) {

    }

    public void linkMovieWithCase(Movie movie, List<Actor> actors) {}


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

