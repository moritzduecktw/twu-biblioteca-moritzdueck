package com.twu.biblioteca.view;

import com.twu.biblioteca.BibliotecaException;
import com.twu.biblioteca.auth.User;
import com.twu.biblioteca.controller.Controller;
import com.twu.biblioteca.model.Media;

import java.util.Map;

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
            consoleUI.listBooks(controller.getAvailableBooks());
        } else if(input.equals(LIST_MOVIES)){
            consoleUI.listMovies(controller.getAvailableMovies());
        } else if (input.equals(CHECKOUT_BOOK)) {
            checkoutBook();
        } else if( input.equals(RETURN_BOOK)){
            returnBook();
        } else if( input.equals(CHECKOUT_MOVIE)){
            checkoutMovie();
        } else if(input.equals(RETURN_MOVIE)){
            returnMovie();
        } else if(input.equals(LOGIN)){
            login();
        } else if(input.equals(LIST_BORROWINGS)){
            listBorrowings();
        } else if(input.equals(USER_INFO)){
            printUserInfo();
        } else {
            consoleUI.printInvalidOptionMessage();
        }
        return true;
    }

    private void listBorrowings() {
        try {
            Map<Media, User> checkedOutItemsWithUsers = controller.getCheckedOutItemsWithUsers();
            consoleUI.listCurrentBorrowings(checkedOutItemsWithUsers);
        }catch (BibliotecaException e){
            consoleUI.println(e.getMessage());
        }
    }


    public void checkoutBook() {
        consoleUI.printSelectBookStatement();
        try {
            controller.checkOutBook(userInputHandler.askForNextString());
            consoleUI.printCheckoutSuccessMessage();
        }catch (BibliotecaException e) {
            consoleUI.println(e.getMessage());
        }
    }


    public void returnBook() {
        consoleUI.printSelectBookStatement();
        try {
            controller.returnBook(userInputHandler.askForNextString());
            consoleUI.printReturnSuccessMessage();
        }catch (BibliotecaException e) {
            consoleUI.println(e.getMessage());
        }
    }

    private void checkoutMovie() {
        consoleUI.printSelectMovieStatement();
        try {
            controller.checkOutMovie(userInputHandler.askForNextString());
            consoleUI.printCheckoutSuccessMessage();
        }catch (BibliotecaException e){
            consoleUI.println(e.getMessage());
        }
    }


    private void returnMovie() {
        consoleUI.printSelectMovieStatement();
        try {
            controller.returnMovie(userInputHandler.askForNextString());
            consoleUI.printReturnSuccessMessage();
        }catch (BibliotecaException e){
            consoleUI.println(e.getMessage());
        }
    }

    private void login() {
        consoleUI.println("Please enter your library number:");
        String libraryNumber = userInputHandler.askForNextString();
        consoleUI.println("Password:");
        String password = userInputHandler.askForNextString();
        controller.login(libraryNumber,password);
    }

    private void printUserInfo() {
        try {
            consoleUI.printCurrentUserInfo(controller.getCurrentUser());
        }catch (BibliotecaException e){
            consoleUI.println(e.getMessage());
        }
    }

}
