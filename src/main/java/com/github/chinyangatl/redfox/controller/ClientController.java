package com.github.chinyangatl.redfox.controller;

import com.github.chinyangatl.redfox.model.beans.Client;
import com.github.chinyangatl.redfox.model.beans.Movie;
import com.github.chinyangatl.redfox.model.dao.ClientDAO;
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

@WebServlet(name = "ClientController", value = "/ClientController")
public class ClientController extends HttpServlet {
    private ClientDAO clientDAO;

    @Resource(name = "jdbc/redfox")
    private DataSource dataSource;
    List<Integer> favoriteMovieList;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            clientDAO = new ClientDAO(dataSource);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }

        favoriteMovieList = new ArrayList<>();

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
                case "GET_FAVS":
                    getFavoriteMovies(request, response);
                default:
                    listMovies(request, response);
            }
        }  catch (Exception e) {
            throw new ServletException(e);
        }
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
                Client client = new Client(firstName, surname, email, password);
                HttpSession session = request.getSession();
                session.setAttribute("clientFromSession", client);
                System.out.println(client.toString());
                doGet(request, response);
            }
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Client client = clientDAO.login( request.getParameter("email"),  request.getParameter("password"));
//        request.setAttribute("loginResult", loginResult);


        if(client != null) {
            HttpSession session = request.getSession();
            session.setAttribute("clientFromSession", client);
//            request.setAttribute("client", client);
            doGet(request, response);
        } else {
            String error = "check your credentials";
            request.setAttribute("error", error);
        }
    }

    private void listMovies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Movie> movies = clientDAO.getMovies();
        request.setAttribute("movie_list", movies);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/home.jsp");
        requestDispatcher.forward(request, response);
    }


    private void addMovieToUserFavs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Client client = (Client) session.getAttribute("clientFromSession");
        int movieId = clientDAO.addMovieToFavorites(client.getEmail(), Integer.parseInt(request.getParameter("movieId")));
        favoriteMovieList.add(movieId);
        session.setAttribute("favMovieListIds", favoriteMovieList);

//        Cookie theCookie = new Cookie("favMovieListCookie", favoriteMovieList.toString());
//        theCookie.setMaxAge(60*60*24*365);
//
//        response.addCookie(theCookie);
        listMovies(request, response);
        //clientDAO.addMovieToFavorites("lip@shameless.com", Integer.parseInt(request.getParameter("movieId")))
    }

    private void getFavoriteMovies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Movie> favoriteMovies = new ArrayList<>();
//        for(Integer movieID : favoriteMovieList) {
//            favoriteMovies.add(clientDAO.getSingleMovie(movieID));
//        }


        HttpSession session = request.getSession();
        Client client = (Client) session.getAttribute("clientFromSession");
        favoriteMovies = clientDAO.getFavoriteMovies(client.getEmail());
        session.setAttribute("favoriteMovies", favoriteMovies);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/favorite_five.jsp");
        requestDispatcher.forward(request, response);


    }

    private void rateMovie(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int movieId = Integer.parseInt(request.getParameter("movieId"));
        int rating = Integer.parseInt(request.getParameter("rating"));

        HttpSession session = request.getSession();
        Client client = (Client) session.getAttribute("clientFromSession");
        String userEmail = client.getEmail();
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


}
