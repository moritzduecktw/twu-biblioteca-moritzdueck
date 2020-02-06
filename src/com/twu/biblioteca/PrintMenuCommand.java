package com.twu.biblioteca;

import java.io.PrintStream;

public class PrintMenuCommand implements Command {

    private PrintStream out;

    public PrintMenuCommand(PrintStream out) {
        this.out = out;
    }

    @Override
    public void execute() {
        out.println("Menu:");
        out.println("(1) List of Books");
    }
}
