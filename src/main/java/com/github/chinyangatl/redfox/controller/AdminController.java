package com.github.chinyangatl.redfox.controller;

import com.github.chinyangatl.redfox.model.beans.Admin;
import com.github.chinyangatl.redfox.model.beans.Employee;
import com.github.chinyangatl.redfox.model.dao.AdminDAO;
import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminController", value = "/AdminController")
public class AdminController extends HttpServlet {
    private AdminDAO adminDAO;

    @Resource(name="jdbc/redfox")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        try{
            adminDAO = new AdminDAO(dataSource);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        listAdmins(request, response);
        listEmployees(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void listAdmins(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Admin> admins = adminDAO.getAdmins();
        request.setAttribute("admin_list", admins);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/list_admins.jsp");
        requestDispatcher.forward(request, response);
    }

    private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employees = adminDAO.getEmployees();
        request.setAttribute("employee_list", employees);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/list_employees.jsp");
        requestDispatcher.forward(request, response);
    }
}
