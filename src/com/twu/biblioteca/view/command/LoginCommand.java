package com.twu.biblioteca.view.command;

import com.twu.biblioteca.controller.Controller;
import com.twu.biblioteca.view.ConsoleUI;
import com.twu.biblioteca.view.UserInputHandler;

public class LoginCommand implements MenuCommand {
    private final ConsoleUI consoleUI;
    private final Controller controller;
    private final UserInputHandler userInputHandler;

    public LoginCommand(ConsoleUI consoleUI, Controller controller, UserInputHandler userInputHandler) {
        this.consoleUI = consoleUI;
        this.controller = controller;
        this.userInputHandler = userInputHandler;
    }

    @Override
    public void execute() {
        consoleUI.println("Enter your library number please:");
        String libraryNumber = userInputHandler.askForNextString();
        consoleUI.println("Password:");
        String password = userInputHandler.askForNextString();
        controller.login(libraryNumber,password);
    }
}
