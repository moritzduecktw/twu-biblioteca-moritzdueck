package com.twu.biblioteca.view.command;

import com.twu.biblioteca.BibliotecaException;
import com.twu.biblioteca.controller.Controller;
import com.twu.biblioteca.view.ConsoleUI;

public class PrintUserInfoCommand implements MenuCommand {

    private final ConsoleUI consoleUI;
    private final Controller controller;

    public PrintUserInfoCommand(ConsoleUI consoleUI, Controller controller) {
        this.consoleUI = consoleUI;
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            consoleUI.printCurrentUserInfo(controller.getCurrentUser());
        }catch (BibliotecaException e){
            consoleUI.println(e.getMessage());
        }
    }
}
