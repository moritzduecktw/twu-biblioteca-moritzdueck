package com.twu.biblioteca;

public class Controller {

    private static final String LIST_BOOKS = "1";
    private static final String QUIT = "0";
    private static final String CHECKOUT_BOOK = "2";
    private static final String RETURN_BOOK = "3";
    private final ConsoleUI consoleUI;

    private UserInputHandler userInputHandler;
    private BookShelf bookShelf;

    public Controller(ConsoleUI consoleUI, UserInputHandler userInputHandler, BookShelf bookShelf) {
        this.consoleUI = consoleUI;
        this.userInputHandler = userInputHandler;
        this.bookShelf = bookShelf;
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
            consoleUI.printAllBooks();
        } else if (input.equals(CHECKOUT_BOOK)) {
            bookCheckoutMenu();
        } else if( input.equals(RETURN_BOOK)){
            bookReturnMenu();
        } else {
            consoleUI.printInvalidOptionMessage();
        }
        return true;
    }

    public void bookCheckoutMenu() {
        consoleUI.printSelectBookStatement();
        if(bookShelf.checkOut(userInputHandler.askForNextString())){
            consoleUI.printCheckoutSuccessMessage();
        }else{
            consoleUI.printCheckoutFailureMessage();
        }
    }


    private void bookReturnMenu() {
        consoleUI.printSelectBookStatement();
        if(bookShelf.returnBook(userInputHandler.askForNextString())){
            consoleUI.printReturnSuccessMessage();
        }else{
            consoleUI.printReturnFailureMessage();
        }
    }

}
