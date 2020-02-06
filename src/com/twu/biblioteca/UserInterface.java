package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.Scanner;

public class UserInterface {

    private Controller controller;
    private PrintStream out;

    public UserInterface(Controller controller, Scanner scanner, PrintStream out) {
        this.controller = controller;
        this.out = out;
    }

    public void welcomeUser() {
        controller.execute(new PrintWelcomeMessageCommand(out));
        controller.execute(new PrintMenuCommand(out));
    }

    public int getUserSelection() {
        throw new UnsupportedOperationException();
    }
}
