package com.twu.biblioteca.view.command;

import com.twu.biblioteca.BibliotecaException;
import com.twu.biblioteca.controller.Controller;
import com.twu.biblioteca.view.ConsoleUI;
import com.twu.biblioteca.view.UserInputHandler;

public class CheckOutMovieCommand implements MenuCommand {

    private final ConsoleUI consoleUI;
    private final Controller controller;
    private final UserInputHandler userInputHandler;

    public CheckOutMovieCommand(ConsoleUI consoleUI, Controller controller, UserInputHandler userInputHandler) {
        this.consoleUI = consoleUI;
        this.controller = controller;
        this.userInputHandler = userInputHandler;
    }

    @Override
    public void execute() {
        consoleUI.printSelectMovieStatement();
        try {
            controller.checkOutMovie(userInputHandler.askForNextString());
            consoleUI.printCheckoutSuccessMessage();
        }catch (BibliotecaException e){
            consoleUI.println(e.getMessage());
        }
    }
}
