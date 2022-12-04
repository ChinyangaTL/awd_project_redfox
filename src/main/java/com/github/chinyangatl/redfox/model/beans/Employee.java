package com.github.chinyangatl.redfox.model.beans;

public class Employee extends Actor{
    private String name;

    public Employee(String email, String password, String name) {
        super(email, password);
        this.name = name;
    }

    public Employee(int id, String email, String password, String name) {
        super(id, email, password);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
