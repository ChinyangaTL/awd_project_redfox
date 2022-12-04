package com.github.chinyangatl.redfox.controller;

import com.github.chinyangatl.redfox.model.beans.Employee;
import com.github.chinyangatl.redfox.model.beans.Movie;
import com.github.chinyangatl.redfox.model.dao.EmployeeDAO;
import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

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
        listMovies(request, response);
    }

    private void listMovies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Movie> movies = employeeDAO.getMovies();
        request.setAttribute("movie_list", movies);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/employee_dashboard.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
