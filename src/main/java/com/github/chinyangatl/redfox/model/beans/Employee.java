package com.github.chinyangatl.redfox.model.beans;

public class Employee extends GeneralActor {
    private String name;

    public Employee(String name, String email, String password) {
        super(email, password);
        this.name = name;
    }

    public Employee(int id, String name, String email, String password) {
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
