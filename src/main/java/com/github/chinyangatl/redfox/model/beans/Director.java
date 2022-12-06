package com.github.chinyangatl.redfox.model.beans;

public class Director extends FilmActor{
    public Director(String firstName, String lastName, String dob) {
        super(firstName, lastName, dob);
    }

    public Director(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Director(int id, String firstName, String lastName, String dob) {
        super(id, firstName, lastName, dob);
    }
}
