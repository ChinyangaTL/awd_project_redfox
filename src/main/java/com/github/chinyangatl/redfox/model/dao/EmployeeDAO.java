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
    public String login(String email, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQLStatements.QUERY_EMPLOYEE_ACCOUNT);

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
        PreparedStatement insertActorStatement = null;
        PreparedStatement queryActorStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();

            queryActorStatement = connection.prepareStatement(SQLStatements.QUERY_ACTOR);
            queryActorStatement.setString(1, actor.getFirstName());
            queryActorStatement.setString(2, actor.getLastName());

            resultSet = queryActorStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                insertActorStatement = connection.prepareStatement(SQLStatements.ADD_ACTOR, Statement.RETURN_GENERATED_KEYS);
                insertActorStatement.setString(1, actor.getFirstName());
                insertActorStatement.setString(2, actor.getLastName());
                insertActorStatement.setString(3, actor.getDob());
                int affectedRows = insertActorStatement.executeUpdate();

                if(affectedRows != 1) {
                    throw new SQLException("Couldn't insert actor");
                }

                ResultSet generatedKeys = insertActorStatement.getGeneratedKeys();
                if(generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Couldn't get id for actor");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection, insertActorStatement, resultSet);
            close(connection, queryActorStatement, resultSet);
        }
    }

    public int addDirector(Director director) {
        Connection connection = null;
        PreparedStatement queryDirectorStatement = null;
        PreparedStatement insertDirectorStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();

            queryDirectorStatement = connection.prepareStatement(SQLStatements.QUERY_DIRECTOR);
            queryDirectorStatement.setString(1, director.getFirstName());
            queryDirectorStatement.setString(2, director.getLastName());

            resultSet = queryDirectorStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                insertDirectorStatement = connection.prepareStatement(SQLStatements.ADD_DIRECTOR, Statement.RETURN_GENERATED_KEYS);
                insertDirectorStatement.setString(1, director.getFirstName());
                insertDirectorStatement.setString(2, director.getLastName());
                insertDirectorStatement.setString(3, director.getDob());
                int affectedRows = insertDirectorStatement.executeUpdate();

                if(affectedRows != 1) {
                    throw new SQLException("Couldn't insert director");
                }

                ResultSet generatedKeys = insertDirectorStatement.getGeneratedKeys();
                if(generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Couldn't get id for director");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection, insertDirectorStatement, resultSet);
            close(connection, queryDirectorStatement, resultSet);
        }
    }

    public int addMovie(Movie movie) {
        Connection connection = null;
        PreparedStatement queryMovieStatement = null;
        PreparedStatement insertMovieStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();

            queryMovieStatement = connection.prepareStatement(SQLStatements.QUERY_MOVIE);

            queryMovieStatement.setString(1, movie.getMovieTitle());
            queryMovieStatement.setString(2, movie.getReleaseDate());

            resultSet = queryMovieStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                insertMovieStatement = connection.prepareStatement(SQLStatements.ADD_MOVIE, Statement.RETURN_GENERATED_KEYS);
                insertMovieStatement.setString(1, movie.getMovieTitle());
                insertMovieStatement.setString(2, movie.getGenre());
                insertMovieStatement.setString(3, movie.getReleaseDate());
                insertMovieStatement.setFloat(4, movie.getRating());
                insertMovieStatement.setString(5, movie.getImgUrl());
                insertMovieStatement.setString(6, movie.getDescription());

                int affectedRows = insertMovieStatement.executeUpdate();

                if(affectedRows != 1) {
                    throw new SQLException("Couldn't insert movie");
                }

                ResultSet generatedKeys = insertMovieStatement.getGeneratedKeys();
                if(generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Couldn't get id for movie");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection, insertMovieStatement, resultSet);
            close(connection, queryMovieStatement, resultSet);
        }
    }

    public void linkMovieAndDirector(Movie movie, Director director) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = null;

        try {
            connection.setAutoCommit(false);
            int movieId = addMovie(movie);
            int directorId = addDirector(director);

            statement = connection.prepareStatement(SQLStatements.LINK_MOVIE_DIRECTOR);
            statement.setInt(1, movieId);
            statement.setInt(2, directorId);

            int affectedRows = statement.executeUpdate();
            if(affectedRows == 1) {
                connection.commit();
            } else {
                throw new SQLException("Song insertion failed");
            }

        }catch (SQLException e) {
            System.out.println("Linking failed" + e.getMessage());

            try {
                System.out.println("Performing rollback");
                connection.rollback();
            } catch (SQLException e2) {
                System.out.println("Yikes" + e.getMessage());
            }
        } finally {
            connection.setAutoCommit(true);
            close(connection, statement, null);
        }

    }

    public void linkMovieWithCast(Movie movie, List<Actor> actors) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = null;

        try {
            connection.setAutoCommit(false);

            int[] actorIds = new int[actors.size()];
            int movieId = addMovie(movie);
            for(int i = 0; i < actors.size(); i++) {
               actorIds[i] = addActor(actors.get(i));
            }

            statement = connection.prepareStatement(SQLStatements.LINK_MOVIE_ACTOR);
            for(Actor actor : actors) {
                statement.setInt(1, movieId);
                statement.setInt(2, actor.getId());

                int affectedRows = statement.executeUpdate();
                if(affectedRows == 1) {
                    connection.commit();
                } else {
                    throw new SQLException("Linking movie and actors failed");
                }
            }

        }catch (SQLException e) {
            System.out.println("Linking failed" + e.getMessage());

            try {
                System.out.println("Performing rollback");
                connection.rollback();
            } catch (SQLException e2) {
                System.out.println("Yikes" + e.getMessage());
            }
        } finally {
            connection.setAutoCommit(true);
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

