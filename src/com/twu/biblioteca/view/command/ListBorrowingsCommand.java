package com.twu.biblioteca.view.command;

import com.twu.biblioteca.BibliotecaException;
import com.twu.biblioteca.auth.User;
import com.twu.biblioteca.controller.Controller;
import com.twu.biblioteca.model.Media;
import com.twu.biblioteca.view.ConsoleUI;
import com.twu.biblioteca.view.UserInputHandler;

import java.util.Map;

public class ListBorrowingsCommand implements MenuCommand {

    private final ConsoleUI consoleUI;
    private final Controller controller;

    public ListBorrowingsCommand(ConsoleUI consoleUI, Controller controller) {
        this.consoleUI = consoleUI;
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            Map<Media, User> checkedOutItemsWithUsers = controller.getCheckedOutItemsWithUsers();
            consoleUI.listCurrentBorrowings(checkedOutItemsWithUsers);
        }catch (BibliotecaException e){
            consoleUI.println(e.getMessage());
        }
    }
}
