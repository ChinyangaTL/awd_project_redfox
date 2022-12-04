package com.github.chinyangatl.redfox;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.annotation.Resource;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    @Resource(name="jdbc/redfox")
    private DataSource dataSource;
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        response.setContentType("text/plain");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = dataSource.getConnection();
            String sql = "select * from admin";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String email = resultSet.getString("email");
                printWriter.println(email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }
}
