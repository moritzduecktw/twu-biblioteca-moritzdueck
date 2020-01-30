package com.twu.biblioteca;

import org.junit.Test;
import org.mockito.InOrder;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

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


        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));

        bibliotecaApp.setBooks(books);
        //when
        bibliotecaApp.start();
        //then
        InOrder inOrder = inOrder(out);

        inOrder.verify(out).println("Clean Code: A Handbook of Agile Software Craftsmanship       | Robert C. Martin            | 2008");
        inOrder.verify(out).println("The Pragmatic Programmer: From Journeyman to Master          | Andrew Hunt and Dave Thomas | 1999");
        inOrder.verify(out).println("Code Complete: A Practical Handbook of Software Construction | Steve McConnell             | 2004");

    }
}
