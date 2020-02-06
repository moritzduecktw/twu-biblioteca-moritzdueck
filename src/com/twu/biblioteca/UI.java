package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;

public class UI {

    private static final int LIST_BOOKS = 1;

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
        while (true) {
            printMenu();
            handleUserInput(userInputHandler.askForNumber());
        }
    }

    public void printWelcomeMessage() {
        out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public void printAllBooks() {
        out.print(bookShelf.outputBookList());
    }

    public void printMenu() {
        out.println("Menu:\n");
        out.println("(1) List of books");
    }

    public void handleUserInput(int i) {
        if (i == LIST_BOOKS) {
            printAllBooks();
        }
    }
}
