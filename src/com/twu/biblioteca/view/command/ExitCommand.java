package com.twu.biblioteca.view.command;

import com.twu.biblioteca.view.ConsoleUI;

public class ExitCommand implements MenuCommand {

    private ConsoleUI consoleUI;

    public ExitCommand(ConsoleUI consoleUI) {
        this.consoleUI = consoleUI;
    }

    @Override
    public void execute() {
        consoleUI.println("Goodbye");
        System.exit(0);
    }

}
