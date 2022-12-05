package com.github.chinyangatl.redfox.controller;

import com.github.chinyangatl.redfox.model.beans.Movie;
import com.github.chinyangatl.redfox.model.dao.ClientDAO;
import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name = "SingleMovie", value = "/SingleMovie")
public class SingleMovie extends HttpServlet {
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
        int movieId = Integer.parseInt(request.getParameter("singleMovieId"));
        Movie movie = clientDAO.getSingleMovie(movieId);

        System.out.println(movie);
        System.out.println("is anyhone home");

        request.setAttribute("currentMovie", movie);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/single_movie.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
