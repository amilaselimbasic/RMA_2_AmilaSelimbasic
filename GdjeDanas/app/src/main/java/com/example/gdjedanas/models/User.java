package com.example.gdjedanas.models;

public class User {

    private String name;
    private String email;

    public User() {

        // prazan konstruktor potreban za firebase
    }

    public User(String name, String email) {

        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}