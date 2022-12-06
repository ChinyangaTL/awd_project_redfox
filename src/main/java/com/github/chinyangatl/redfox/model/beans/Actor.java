package com.github.chinyangatl.redfox.model.beans;

public class Actor extends FilmActor{
    public Actor(String firstName, String lastName, String dob) {
        super(firstName, lastName, dob);
    }

    public Actor(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Actor(int id, String firstName, String lastName, String dob) {
        super(id, firstName, lastName, dob);
    }
}
