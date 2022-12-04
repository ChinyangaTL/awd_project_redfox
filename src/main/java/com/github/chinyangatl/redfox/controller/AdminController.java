package com.github.chinyangatl.redfox.controller;

import com.github.chinyangatl.redfox.model.beans.Admin;
import com.github.chinyangatl.redfox.model.beans.Employee;
import com.github.chinyangatl.redfox.model.dao.AdminDAO;
import com.github.chinyangatl.redfox.utils.AdminCommandEnums;
import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

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
        System.out.println("I am here in get");
        try {
            String command = request.getParameter("command");

            if(command == null) command = String.valueOf(AdminCommandEnums.LIST_EMPLOYEES);


            switch (command) {
                case "LIST_EMPLOYEES" :
                    listEmployees(request, response);
                    break;

                case "ADD_EMPLOYEE" :
                    addEmployee(request, response);
                    break;

                default:
                    listEmployees(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
//        listAdmins(request, response);
        listEmployees(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("I am here in post");
        adminLogin(request, response);
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

    private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // read student info from form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // create a new student object
        Employee employee = new Employee(name, email, password);

        // add the student to the database
        adminDAO.addEmployee(employee);

        // send back to main page (the student list)
        listEmployees(request, response);
    }

    private void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginResult = adminDAO.login( request.getParameter("email"),  request.getParameter("password"));
        System.out.println("Login result" + loginResult);
        request.setAttribute("loginResult", loginResult);

        if(Objects.equals(loginResult, "Success")) {
           doGet(request, response);
        }


    }
}
