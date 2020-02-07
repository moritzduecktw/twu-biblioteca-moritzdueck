package com.twu.biblioteca;

import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ConsoleUITests {

    @Test
    public void printsWelcomeMessage() {

        PrintStream out = mock(PrintStream.class);
        ConsoleUI consoleUI = new ConsoleUI(out, null);

        consoleUI.printWelcomeMessage();

        verify(out).println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    @Test
    public void printsMenu() {
        PrintStream out = mock(PrintStream.class);
        ConsoleUI consoleUI = new ConsoleUI(out, null);

        consoleUI.printMenu();

        verify(out).println("Menu:\n");
        verify(out).println("(0) Quit");
        verify(out).println("(1) List of books");
        verify(out).println("(2) List of movies");
        verify(out).println("(3) Check-out a book");
        verify(out).println("(4) Return a book");
    }
}
