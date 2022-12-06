package com.github.chinyangatl.redfox.controller;

import com.github.chinyangatl.redfox.model.beans.*;
import com.github.chinyangatl.redfox.model.dao.EmployeeDAO;
import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "EmployeeController", value = "/EmployeeController")
public class EmployeeController extends HttpServlet {
    private EmployeeDAO employeeDAO;

    @Resource(name="jdbc/redfox")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        try{
            employeeDAO = new EmployeeDAO(dataSource);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String command = request.getParameter("command");

            if(command == null) command = "LIST_MOVIES";

            switch (command) {
                case "LIST_MOVIES":
                    listMovies(request, response);
                case "ADD_ACTOR":
                    addActor(request, response);
                case "ADD_DIRECTOR":
                    addDirector(request, response);
                case "ADD_MOVIE":
                    addMovie(request, response);
                default:
                    listMovies(request, response);
            }
        }  catch (Exception e) {
        throw new ServletException(e);
    }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        employeeLogin(request, response);
    }


    private void addActor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String dob = request.getParameter("dob");
        String surname = request.getParameter("surname");

        Actor actor = new Actor(firstName, surname, dob);

       employeeDAO.addActor(actor);
       listMovies(request, response);
    }

    private void addDirector(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String dob = request.getParameter("dob");
        String surname = request.getParameter("surname");

        Director director = new Director(firstName, surname, dob);

        employeeDAO.addDirector(director);
        listMovies(request, response);
    }

    private void addMovie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String movieTitle = request.getParameter("movieTitle");
        String releaseDate = request.getParameter("releaseDate");
        String img = request.getParameter("img");
        String genre = request.getParameter("genre");
        String description = request.getParameter("description");

        ArrayList<Actor> actors = new ArrayList<>();
        String castMember1 = request.getParameter("castMember1");
        String castMember2 = request.getParameter("castMember2");
        String castMember3 = request.getParameter("castMember3");

        String actor1[] = castMember1.split(" ");
        Actor filmActor1 = new Actor(actor1[0], actor1[1]);
        filmActor1.setDob("June 1, 1987");
        actors.add(filmActor1);
        employeeDAO.addActor(filmActor1);


        if(castMember2 == null) {
            return;
        } else {
            String actor2[] = castMember1.split(" ");
            Actor filmActor2 = new Actor(actor2[0], actor2[1]);
            filmActor1.setDob("July 1, 1987");
            actors.add(filmActor2);
            employeeDAO.addActor(filmActor2);
        }

        if(castMember3 == null) {
            return;
        } else {
            String[] actor3 = castMember1.split(" ");
            Actor filmActor3 = new Actor(actor3[0], actor3[1]);
            filmActor1.setDob("May 1, 1984");
            actors.add(filmActor3);
            employeeDAO.addActor(filmActor3);
        }

        String director = request.getParameter("director");
        String directorName[] = director.split(" ");

        Director movieDirector = new Director(directorName[0], directorName[1]);
        movieDirector.setDob("March 12, 1964");
        employeeDAO.addDirector(movieDirector);


        Movie movie = new Movie(movieTitle, releaseDate, genre, img, description);
        movie.setActors(actors);
        movie.setDirector(movieDirector);
        movie.setRating(0);

        employeeDAO.addMovie(movie);
        listMovies(request, response);
    }

    private void listMovies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Movie> movies = employeeDAO.getMovies();
        request.setAttribute("movie_list", movies);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/employee_dashboard.jsp");
        requestDispatcher.forward(request, response);
    }


    private void employeeLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginResult = employeeDAO.login( request.getParameter("email"),  request.getParameter("password"));
        request.setAttribute("loginResult", loginResult);

        if(Objects.equals(loginResult, "Success")) {
            doGet(request, response);
        }
    }


    private void dummyInsert() {
        Movie movie = new Movie(2, "Raiders of the Lost Ark", "Adventure", "June 12, 1981", 5, "image", "lorem ispum");
        Actor actor = new Actor(3, "Harrison", "Ford", "July 13, 1942");
        List<Actor> actors = new ArrayList<>();
        actors.add(actor);
        Director director = new Director(2, "Stephen", "Spielberg", "December 18, 1946");
        employeeDAO.addMovie(movie);
        employeeDAO.addActor(actor);
        employeeDAO.addDirector(director);

        try {
            employeeDAO.linkMovieAndDirector(movie, director);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            employeeDAO.linkMovieWithCast(movie, actors);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
