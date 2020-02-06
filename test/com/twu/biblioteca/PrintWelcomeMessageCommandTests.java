package com.twu.biblioteca;

import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PrintWelcomeMessageCommandTests {

    @Test
    public void printWelcomeMessage() {

        PrintStream mockedPrintStream = mock(PrintStream.class);
        PrintWelcomeMessageCommand printWelcomeMessageCommand = new PrintWelcomeMessageCommand(mockedPrintStream);

        printWelcomeMessageCommand.execute();

        verify(mockedPrintStream).println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");

    }
}
