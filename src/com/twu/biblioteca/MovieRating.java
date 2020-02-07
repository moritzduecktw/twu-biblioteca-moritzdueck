package com.twu.biblioteca;

public enum MovieRating {
    NONE("NA"),
    ONE("01"),
    TWO("02"),
    THREE("03"),
    FOUR("04"),
    FIVE("05"),
    SIX("06"),
    SEVEN("07"),
    EIGTH("08"),
    NINE("09"),
    TEN("10");

    private final String rating;

    MovieRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return rating;
    }
}
