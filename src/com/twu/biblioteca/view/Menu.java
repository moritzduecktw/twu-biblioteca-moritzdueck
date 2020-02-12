package com.twu.biblioteca.view;

import com.twu.biblioteca.controller.Controller;
import com.twu.biblioteca.view.command.*;

public class Menu {

    private static final String QUIT = "0";
    private static final String LIST_BOOKS = "1";
    private static final String LIST_MOVIES = "2";
    private static final String CHECKOUT_BOOK = "3";
    private static final String RETURN_BOOK = "4";
    private static final String CHECKOUT_MOVIE = "5";
    private static final String RETURN_MOVIE = "6";
    private static final String LOGIN = "7";
    private static final String LIST_BORROWINGS = "8";
    private static final String USER_INFO = "9";
    private final ConsoleUI consoleUI;

    private UserInputHandler userInputHandler;
    private Controller controller;

    public Menu(ConsoleUI consoleUI, UserInputHandler userInputHandler, Controller controller) {
        this.consoleUI = consoleUI;
        this.userInputHandler = userInputHandler;
        this.controller = controller;
    }

    public void start() {
        consoleUI.printWelcomeMessage();
        do {
            consoleUI.printMenu();
        } while (handleUserInput(userInputHandler.askForNextString()));
    }

    public boolean handleUserInput(String input) {
        if (input.equals(QUIT)) {
            return false;
        } else if (input.equals(LIST_BOOKS)) {
            (new ListBooksCommand(consoleUI,controller)).execute();
        } else if(input.equals(LIST_MOVIES)){
            (new ListMoviesCommand(consoleUI,controller)).execute();
        } else if (input.equals(CHECKOUT_BOOK)) {
            (new CheckOutBookCommand(consoleUI,controller,userInputHandler)).execute();
        } else if( input.equals(RETURN_BOOK)){
            (new ReturnBookCommand(consoleUI,controller,userInputHandler)).execute();
        } else if( input.equals(CHECKOUT_MOVIE)){
            (new CheckOutMovieCommand(consoleUI,controller,userInputHandler)).execute();
        } else if(input.equals(RETURN_MOVIE)){
            (new ReturnMovieCommand(consoleUI,controller,userInputHandler)).execute();
        } else if(input.equals(LOGIN)){
            (new LoginCommand(consoleUI,controller,userInputHandler)).execute();
        } else if(input.equals(LIST_BORROWINGS)){
            (new ListBorrowingsCommand(consoleUI,controller)).execute();
        } else if(input.equals(USER_INFO)){
            (new PrintUserInfoCommand(consoleUI,controller)).execute();
        } else {
            consoleUI.printInvalidOptionMessage();
        }
        return true;
    }

}
