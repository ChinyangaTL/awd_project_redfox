package com.github.chinyangatl.redfox.utils;

public class SQLStatements {
    public static final String GET_EMPLOYEES = "SELECT * FROM employee";
    public static final String ADD_EMPLOYEE = "INSERT INTO employee (name, email, password) VALUES (?, ?, ?)";
    public static final String QUERY_ADMIN_ACCOUNT = "select * from admin where email = ? and password = ?";
}
