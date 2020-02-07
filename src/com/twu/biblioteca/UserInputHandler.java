package com.twu.biblioteca;

import java.io.InputStream;
import java.util.Scanner;

public class UserInputHandler {

    private final Scanner scanner;

    public UserInputHandler(InputStream in) {
        scanner = new Scanner(in);
    }

    public String askForNextString() {
        return scanner.nextLine();
    }
}
