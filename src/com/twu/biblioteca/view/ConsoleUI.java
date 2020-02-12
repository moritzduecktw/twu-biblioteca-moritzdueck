package com.twu.biblioteca.view;

import com.twu.biblioteca.auth.User;
import com.twu.biblioteca.controller.Controller;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Media;
import com.twu.biblioteca.model.Movie;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;

public class ConsoleUI {

    private PrintStream out;

    public ConsoleUI(PrintStream out) {
        this.out = out;
    }

    public void printWelcomeMessage() {
        out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public void printMenu() {
        out.println("Menu:\n");
        out.println("(0) Quit");
        out.println("(1) List of books");
        out.println("(2) List of movies");
        out.println("(3) Check-out a book");
        out.println("(4) Return a book");
        out.println("(5) Check-out a movie");
        out.println("(6) Return a movie");
        out.println("(7) Login");
        out.println("(8) Currently checked-out items");
        out.println("(9) Current User Info");
    }

    public void printCheckoutSuccessMessage() {
        out.println("Thank you! Enjoy the item");
    }

    public void printReturnSuccessMessage() {
        out.println("Thank you for returning the item");
    }

    public void printSelectBookStatement() {
        out.println("Select one of the books by giving the title:");
    }

    public void printInvalidOptionMessage() {
        out.println("Please select a valid option!");
    }

    public void printSelectMovieStatement() {
        out.println("Select one of the movies by giving the name:");
    }

    public void listBooks(List<Book> books) {

        StringBuffer result = new StringBuffer();

        int maxLengthTitle = books.stream()
                .map(Book::getTitle)
                .map(String::length)
                .reduce(0, Integer::max);
        int maxLengthAuthor = books.stream()
                .map(Book::getAuthor)
                .map(String::length)
                .reduce(0, Integer::max);

        for (Book book : books) {
            result.append(paddString(book.getTitle(), maxLengthTitle));
            result.append( " | ");
            result.append( paddString(book.getAuthor(), maxLengthAuthor));
            result.append( " | ");
            result.append( book.getYear());
            result.append("\n");
        }

        out.print(result.toString());
    }

    public void listMovies(List<Movie> movies) {
        StringBuffer result = new StringBuffer();

        int maxTitleLength = movies.stream()
                .map(Movie::getTitle)
                .map(String::length)
                .reduce(0, Integer::max);

        int maxDirectorLength = movies.stream()
                .map(Movie::getDirector)
                .map(String::length)
                .reduce(0, Integer::max);

        for (Movie movie : movies) {
            result.append(paddString(movie.getTitle(), maxTitleLength));
            result.append(" | ");
            result.append(movie.getYear());
            result.append(" | ");
            result.append(paddString(movie.getDirector(), maxDirectorLength));
            result.append(" | ");
            result.append(movie.getRating().toString());
            result.append("\n");
        }

        out.print(result.toString());

    }

    public void listCurrentBorrowings(Map<Media, User> checkedOutItemsWithUsers) {

        StringBuffer result = new StringBuffer();

        int maxTitleLength = checkedOutItemsWithUsers.keySet().stream()
                .map(Media::getTitle)
                .map(String::length)
                .reduce(0, Integer::max);

        for (Media media: checkedOutItemsWithUsers.keySet()) {
            User user = checkedOutItemsWithUsers.get(media);

            result.append(paddString(media.getTitle(), maxTitleLength));
            result.append(" | ");
            result.append(user.getLibraryNumber());
            result.append("\n");
        }

        out.print(result.toString());
    }

    private String paddString(String string, int targetLength) {
        return String.format("%-" + targetLength + "s", string);
    }

    public void println(String message) {
        out.println(message);
    }

    public void printCurrentUserInfo(User user) {
        out.println(user);
    }
}
