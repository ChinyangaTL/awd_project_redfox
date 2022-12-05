package com.github.chinyangatl.redfox.controller;

import com.github.chinyangatl.redfox.model.beans.Movie;
import com.github.chinyangatl.redfox.model.dao.ClientDAO;
import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "ClientController", value = "/ClientController")
public class ClientController extends HttpServlet {
    private ClientDAO clientDAO;

    @Resource(name = "jdbc/redfox")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            clientDAO = new ClientDAO(dataSource);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String command = request.getParameter("command");

            if(command == null) command = "VIEW_MOVIES";

            switch (command) {
                case "VIEW_MOVIES":
                    listMovies(request, response);
                case "VIEW_SINGLE_MOVIE":
                    viewSingleMovie(request, response);
                case "RATE_MOVIE":
                    rateMovie(request, response);
                case "ADD_TO_FAVS":
                    addMovieToUserFavs(request, response);
                default:
                    listMovies(request, response);
            }
        }  catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void addMovieToUserFavs(HttpServletRequest request, HttpServletResponse response) {
        // TODO: CHANGE EMAIL TO DYNAMIC DEPENDING ON SESSION
        clientDAO.addMovieToFavorites("lip@shameless.com", Integer.parseInt(request.getParameter("movieId")));
        //clientDAO.addMovieToFavorites("lip@shameless.com", Integer.parseInt(request.getParameter("movieId")))
    }

    private void rateMovie(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int movieId = Integer.parseInt(request.getParameter("movieId"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        // TODO: CHANGE EMAIL TO DYNAMIC DEPENDING ON SESSION
        String userEmail = "lip@shameless.com";
        clientDAO.populateMovieRatingsTable(movieId, userEmail, rating);
        clientDAO.updateMovieRating(movieId);

    }

    private void viewSingleMovie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int movieId = Integer.parseInt(request.getParameter("singleMovieId"));
        Movie movie = clientDAO.getSingleMovie(movieId);

        request.setAttribute("currentMovie", movie);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/single_movie.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String command = request.getParameter("command");

            if(command == null) command = "LOGIN";

            switch (command) {
                case "LOGIN":
                    login(request, response);
                case "REGISTER":
                    register(request, response);
                default:
                    login(request, response);
            }
        }  catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if(password.equals(confirmPassword)) {
            String registerResult = clientDAO.register(firstName, surname, email, password);
            request.setAttribute("registerResult", registerResult);

            if(Objects.equals(registerResult, "Success")) {
                doGet(request, response);
            }
        }




    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginResult = clientDAO.login( request.getParameter("email"),  request.getParameter("password"));
        request.setAttribute("loginResult", loginResult);

        if(Objects.equals(loginResult, "Success")) {
            doGet(request, response);
        }
    }

    private void listMovies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Movie> movies = clientDAO.getMovies();
        request.setAttribute("movie_list", movies);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/home.jsp");
        requestDispatcher.forward(request, response);
    }

}
