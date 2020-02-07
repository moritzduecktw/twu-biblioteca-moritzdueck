package com.twu.biblioteca;

import java.io.PrintStream;

public class ConsoleUI {

    private PrintStream out;
    private BookShelf bookShelf;

    public ConsoleUI(PrintStream out, BookShelf bookShelf) {
        this.out = out;
        this.bookShelf = bookShelf;
    }

    public void printWelcomeMessage() {
        out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public void printMenu() {
        out.println("Menu:\n");
        out.println("(0) Quit");
        out.println("(1) List of books");
        out.println("(2) Check-out a book");
        out.println("(3) Return a book");
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

    public void printAllBooks() {
        out.print(bookShelf.outputBookList());
    }
}
