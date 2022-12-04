package com.github.chinyangatl.redfox.utils;

public class SQLStatements {
    public static final String GET_EMPLOYEES = "SELECT * FROM employee";
    public static final String ADD_EMPLOYEE = "INSERT INTO employee (name, email, password) VALUES (?, ?, ?)";
}
