package com.twu.biblioteca;

public class Movie {

    private final String name;
    private final int year;
    private final String director;
    private final MovieRating rating;

    public Movie(String name, int year, String director, MovieRating rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public MovieRating getRating() {
        return rating;
    }



}
