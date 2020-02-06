package com.twu.biblioteca;

import org.junit.Test;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class UITests {

    @Test
    public void printsWelcomeMessage() {

        PrintStream out = mock(PrintStream.class);
        InputStream in = mock(InputStream.class);

        UI ui = new UI(out, in, new BookShelf(new ArrayList<Book>()));

        ui.printWelcomeMessage();

        verify(out).println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    @Test
    public void printsMenu() {
        PrintStream out = mock(PrintStream.class);
        InputStream in = mock(InputStream.class);
        UI ui = new UI(out, in, new BookShelf(new ArrayList<Book>()));

        ui.printMenu();

        verify(out).println("Menu:\n");
        verify(out).println("(1) List of books");
    }

    @Test
    public void printsAllBooksOnUserInputOne() {

        PrintStream out = mock(PrintStream.class);
        InputStream in = mock(InputStream.class);
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));
        UI ui = new UI(out, in, new BookShelf(books));

        ui.handleUserInput("1");

        verify(out).print("Clean Code: A Handbook of Agile Software Craftsmanship       | Robert C. Martin            | 2008\n" +
                "The Pragmatic Programmer: From Journeyman to Master          | Andrew Hunt and Dave Thomas | 1999\n" +
                "Code Complete: A Practical Handbook of Software Construction | Steve McConnell             | 2004\n");

    }

    @Test
    public void printsErrorOnWrongInput() {

        PrintStream out = mock(PrintStream.class);
        InputStream in = mock(InputStream.class);
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));
        UI ui = new UI(out, in, new BookShelf(books));

        ui.handleUserInput("0");
        ui.handleUserInput("2");
        ui.handleUserInput("-1das3");

        verify(out, times(3)).println("Please select a valid option!");
    }

}
