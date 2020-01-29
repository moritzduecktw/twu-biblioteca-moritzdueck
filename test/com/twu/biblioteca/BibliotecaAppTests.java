package com.twu.biblioteca;

import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

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
        verify(out).println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    @Test
    public void shouldListAllBooksOnStart() {

        //given
        PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        //when
        bibliotecaApp.start();
        //then
        verify(out).println("Clean Code: A Handbook of Agile Software Craftsmanship");
        verify(out).println("The Pragmatic Programmer: From Journeyman to Master");
        verify(out).println("Code Complete: A Practical Handbook of Software Construction");

    }
}
