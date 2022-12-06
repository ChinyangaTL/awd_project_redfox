package com.github.chinyangatl.redfox.model.beans;

public class Client extends GeneralActor{
    private String firstName, lastName;

    public Client(String firstName, String lastName, String email, String password) {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Client(String email, String password) {
        super(email, password);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
