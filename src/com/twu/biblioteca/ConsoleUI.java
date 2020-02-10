package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.List;

public class ConsoleUI {

    private PrintStream out;
    private MediaRepository mediaRepository;

    public ConsoleUI(PrintStream out, MediaRepository mediaRepository) {
        this.out = out;
        this.mediaRepository = mediaRepository;
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
    }

    public void printCheckoutSuccessMessage() {
        out.println("Thank you! Enjoy the book");
    }

    public void printCheckoutFailureMessage() {
        out.println("Sorry, that book is not available");
    }

    public void printReturnSuccessMessage() {
        out.println("Thank you for returning the book");
    }

    public void printReturnFailureMessage() {
        out.println("That is not a valid book to return.");
    }

    public void printSelectBookStatement() {
        out.println("Select one of the books by giving the title:");
    }

    public void printInvalidOptionMessage() {
        out.println("Please select a valid option!");
    }

    public void listBooks() {

        List<Book> books = mediaRepository.getBooks();

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

    public void listMovies() {
        List<Movie> movies = mediaRepository.getMovies();
        StringBuffer result = new StringBuffer();

        int maxNameLength = movies.stream()
                .map(Movie::getName)
                .map(String::length)
                .reduce(0, Integer::max);

        int maxDirectorLength = movies.stream()
                .map(Movie::getDirector)
                .map(String::length)
                .reduce(0, Integer::max);

        for (Movie movie : movies) {
            result.append(paddString(movie.getName(), maxNameLength));
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


    private String paddString(String string, int targetLength) {
        return String.format("%-" + targetLength + "s", string);
    }
}
