package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;

public class UI {

    private static final String LIST_BOOKS = "1";
    private static final String QUIT = "0";

    private PrintStream out;
    private InputStream in;
    private BookShelf bookShelf;

    public UI(PrintStream out, InputStream in, BookShelf bookShelf) {
        this.out = out;
        this.in = in;
        this.bookShelf = bookShelf;
    }


    public void start() {
        printWelcomeMessage();
        UserInputHandler userInputHandler = new UserInputHandler(in);
        do {
            printMenu();
        } while(handleUserInput(userInputHandler.askForNextString()));

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
    }

    public boolean handleUserInput(String input) {
        if (input.equals(QUIT)) {
            return false;
        } else if (input.equals(LIST_BOOKS)) {
            printAllBooks();
        } else {
            printInvalidOptionMessage();
        }

        return true;
    }

    private void printInvalidOptionMessage() {
        out.println("Please select a valid option!");
    }
}
