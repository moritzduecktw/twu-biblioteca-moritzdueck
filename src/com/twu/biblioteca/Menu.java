package com.twu.biblioteca;

public class Menu {

    private static final String QUIT = "0";
    private static final String LIST_BOOKS = "1";
    private static final String LIST_MOVIES = "2";
    private static final String CHECKOUT_BOOK = "3";
    private static final String RETURN_BOOK = "4";
    private static final String CHECKOUT_MOVIE = "5";
    private static final String RETURN_MOVIE = "6";
    private final ConsoleUI consoleUI;

    private UserInputHandler userInputHandler;
    private MediaRepository mediaRepository;

    public Menu(ConsoleUI consoleUI, UserInputHandler userInputHandler, MediaRepository mediaRepository) {
        this.consoleUI = consoleUI;
        this.userInputHandler = userInputHandler;
        this.mediaRepository = mediaRepository;
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
            consoleUI.listBooks();
        } else if(input.equals(LIST_MOVIES)){
            consoleUI.listMovies();
        } else if (input.equals(CHECKOUT_BOOK)) {
            checkoutBook();
        } else if( input.equals(RETURN_BOOK)){
            returnBook();
        } else if( input.equals(CHECKOUT_MOVIE)){
            checkoutMovie();
        } else if(input.equals(RETURN_MOVIE)){
            returnMovie();
        } else {
            consoleUI.printInvalidOptionMessage();
        }
        return true;
    }


    public void checkoutBook() {
        consoleUI.printSelectBookStatement();
        if(mediaRepository.checkOutBook(userInputHandler.askForNextString())){
            consoleUI.printCheckoutSuccessMessage();
        }else{
            consoleUI.printCheckoutFailureMessage();
        }
    }

    private void checkoutMovie() {
        consoleUI.printSelectMovieStatement();
        if(mediaRepository.checkOutMovie(userInputHandler.askForNextString())){
            consoleUI.printCheckoutSuccessMessage();
        }else{
            consoleUI.printCheckoutFailureMessage();
        }
    }


    public void returnBook() {
        consoleUI.printSelectBookStatement();
        if(mediaRepository.returnBook(userInputHandler.askForNextString())){
            consoleUI.printReturnSuccessMessage();
        }else{
            consoleUI.printReturnFailureMessage();
        }
    }


    private void returnMovie() {
        consoleUI.printSelectMovieStatement();
        if(mediaRepository.returnMovie(userInputHandler.askForNextString())){
            consoleUI.printReturnSuccessMessage();
        }else {
            consoleUI.printReturnFailureMessage();
        }
    }


}