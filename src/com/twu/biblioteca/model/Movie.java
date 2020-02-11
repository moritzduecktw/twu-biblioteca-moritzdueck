package com.twu.biblioteca.model;

import java.util.Objects;

public class Movie extends Media{

    private final int year;
    private final String director;
    private final MovieRating rating;

    public Movie(String title, int year, String director, MovieRating rating) {
        super(title);
        this.year = year;
        this.director = director;
        this.rating = rating;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return year == movie.year &&
                Objects.equals(title, movie.title) &&
                Objects.equals(director, movie.director) &&
                rating == movie.rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, year, director, rating);
    }
}
