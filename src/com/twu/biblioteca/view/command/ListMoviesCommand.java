package com.twu.biblioteca.view.command;

import com.twu.biblioteca.controller.Controller;
import com.twu.biblioteca.view.ConsoleUI;

public class ListMoviesCommand implements MenuCommand {

    private final ConsoleUI consoleUI;
    private final Controller controller;

    public ListMoviesCommand(ConsoleUI consoleUI, Controller controller) {
        this.consoleUI = consoleUI;
        this.controller = controller;
    }

    @Override
    public void execute() {
        consoleUI.listMovies(controller.getAvailableMovies());
    }

}
