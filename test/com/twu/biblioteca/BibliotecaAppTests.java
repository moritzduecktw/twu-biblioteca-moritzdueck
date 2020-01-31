package com.twu.biblioteca;

import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BibliotecaAppTests {

    @Test
    public void printWelcomeMessage() {

        PrintStream out = mock(PrintStream.class);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(out,new BookShelf(new ArrayList<Book>()));

        bibliotecaApp.start();

        verify(out).println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    @Test
    public void printAllBooks() {
        
        PrintStream out = mock(PrintStream.class);
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));
        BibliotecaApp bibliotecaApp = new BibliotecaApp(out, new BookShelf(books));

        bibliotecaApp.printAllBooks();

        verify(out).print("Clean Code: A Handbook of Agile Software Craftsmanship       | Robert C. Martin            | 2008\n"+
                "The Pragmatic Programmer: From Journeyman to Master          | Andrew Hunt and Dave Thomas | 1999\n"+
                "Code Complete: A Practical Handbook of Software Construction | Steve McConnell             | 2004\n");

    }


}
