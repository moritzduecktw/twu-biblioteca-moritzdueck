package com.twu.biblioteca;

import java.io.PrintStream;

public class UI {

    private static final String LIST_BOOKS = "1";
    private static final String QUIT = "0";
    private static final String CHECKOUT_BOOK = "2";

    private PrintStream out;
    private UserInputHandler userInputHandler;
    private BookShelf bookShelf;

    public UI(PrintStream out, UserInputHandler userInputHandler, BookShelf bookShelf) {
        this.out = out;
        this.userInputHandler = userInputHandler;
        this.bookShelf = bookShelf;
    }

    public void start() {
        printWelcomeMessage();
        do {
            printMenu();
        } while (handleUserInput(userInputHandler.askForNextString()));
    }

    public void printWelcomeMessage() {
        out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public void printAllBooks() {
        out.print(bookShelf.outputBookList());
    }

    public void printMenu() {
        out.println("Menu:\n");
        out.println("(0) Quit");
        out.println("(1) List of books");
        out.println("(2) Check-out a book");
    }

    public boolean handleUserInput(String input) {
        if (input.equals(QUIT)) {
            return false;
        } else if (input.equals(LIST_BOOKS)) {
            printAllBooks();
        } else if (input.equals(CHECKOUT_BOOK)) {
            bookCheckoutMenu();
        } else {
            printInvalidOptionMessage();
        }
        return true;
    }

    private void bookCheckoutMenu() {
        do {
            printBookCheckoutMessage();
        } while (!bookShelf.checkOut(userInputHandler.askForNextString()));
    }

    public void printBookCheckoutMessage() {
        out.println("Select one of the following books by giving the title:");
        out.print(bookShelf.outputBookList());
    }

    private void printInvalidOptionMessage() {
        out.println("Please select a valid option!");
    }
}
