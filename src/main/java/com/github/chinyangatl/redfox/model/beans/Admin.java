package com.github.chinyangatl.redfox.model.beans;

public class Admin extends Actor{
    public Admin(String email, String password) {
        super(email, password);
    }

    public Admin(int id, String email, String password) {
        super(id, email, password);
    }
}
