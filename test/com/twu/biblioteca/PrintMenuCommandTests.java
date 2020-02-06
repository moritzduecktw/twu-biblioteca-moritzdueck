package com.twu.biblioteca;

import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PrintMenuCommandTests {

    @Test
    public void printsMenu() {
        PrintStream mockedPrintStream = mock(PrintStream.class);
        Command printMenu = new PrintMenuCommand(mockedPrintStream);

        printMenu.execute();

        verify(mockedPrintStream).println("Menu:");
        verify(mockedPrintStream).println("(1) List of Books");
    }

}
