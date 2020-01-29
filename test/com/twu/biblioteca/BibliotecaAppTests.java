package com.twu.biblioteca;

import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BibliotecaAppTests {

    @Test
    public void shouldPrintWelcomeMessage() {

       //given
        PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        //when
        bibliotecaApp.start();
        //then
        verify(out).println("Welcome to Biblioteca!");
    }

}
