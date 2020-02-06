package com.twu.biblioteca;

import java.io.PrintStream;

public class Controller {


    private static final int LIST_BOOKS = 1;

    private UserInterface userInterface;
    private PrintStream out;
    private BookShelf bookShelf;


    public Controller(PrintStream out, BookShelf bookShelf) {
        this.out = out;
        this.bookShelf = bookShelf;
    }

    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }


    public void start() {
        userInterface.welcomeUser();

        int userSelection = userInterface.getUserSelection();

        if (userSelection == LIST_BOOKS) {
            ListBooksCommand listBooksCommand = new ListBooksCommand(bookShelf.getBooks(), out);
            listBooksCommand.execute();
        }
    }

    public void execute(Command command) {
        command.execute();
    }


}
