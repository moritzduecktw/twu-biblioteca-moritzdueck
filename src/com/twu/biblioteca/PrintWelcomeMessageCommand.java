package com.twu.biblioteca;

import java.io.PrintStream;

public class PrintWelcomeMessageCommand implements Command {

    private PrintStream printStream;

    public PrintWelcomeMessageCommand(PrintStream printStream) {

        this.printStream = printStream;
    }

    @Override
    public void execute() {
        printStream.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");

    }
}
