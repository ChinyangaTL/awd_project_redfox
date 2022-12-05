package com.github.chinyangatl.redfox.controller;

import com.github.chinyangatl.redfox.model.beans.Movie;
import com.github.chinyangatl.redfox.model.dao.ClientDAO;
import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

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
        listMovies(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private void listMovies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Movie> movies = clientDAO.getMovies();
        request.setAttribute("movie_list", movies);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/home.jsp");
        requestDispatcher.forward(request, response);
    }

}
