package com.github.chinyangatl.redfox.model.beans;

import java.util.ArrayList;

public class Movie {
    private int id;
    private String movieTitle;
    private Director director;
    private ArrayList<Actor> actors;
    private String genre;
    private String releaseDate;
    private float rating;
    private String imgUrl;
    private String description;

    public Movie(int id, String movieTitle, String genre, String releaseDate, float rating, String imgUrl, String description) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.imgUrl = imgUrl;
        this.description = description;
    }

    public Movie(int id, String movieTitle, Director director, ArrayList<Actor> actors, String genre, String releaseDate, float rating, String imgUrl, String description) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.director = director;
        this.actors = actors;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.imgUrl = imgUrl;
        this.description = description;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public void setActors(ArrayList<Actor> actors) {
        this.actors = actors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
