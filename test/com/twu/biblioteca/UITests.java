package com.twu.biblioteca;

import org.junit.Test;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class UITests {

    @Test
    public void printsWelcomeMessage() {

        PrintStream out = mock(PrintStream.class);
        InputStream in = mock(InputStream.class);

        UI ui = new UI(out, new UserInputHandler(in), new BookShelf(new ArrayList<Book>()));

        ui.printWelcomeMessage();

        verify(out).println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    @Test
    public void printsMenu() {
        PrintStream out = mock(PrintStream.class);
        InputStream in = mock(InputStream.class);
        UI ui = new UI(out, new UserInputHandler(in), new BookShelf(new ArrayList<Book>()));

        ui.printMenu();

        verify(out).println("Menu:\n");
        verify(out).println("(0) Quit");
        verify(out).println("(1) List of books");
        verify(out).println("(2) Check-out a book");
    }

    @Test
    public void exitsOnUserInputZero() {

        PrintStream out = mock(PrintStream.class);
        InputStream in = mock(InputStream.class);
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));
        UI ui = new UI(out, new UserInputHandler(in), new BookShelf(books));

        assertThat(ui.handleUserInput("0"), is(false));
    }

    @Test
    public void printsAllBooksOnUserInputOne() {

        PrintStream out = mock(PrintStream.class);
        InputStream in = mock(InputStream.class);
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));
        UI ui = new UI(out, new UserInputHandler(in), new BookShelf(books));

        assertThat(ui.handleUserInput("1"), is(true));
        verify(out).print("Clean Code: A Handbook of Agile Software Craftsmanship       | Robert C. Martin            | 2008\n" +
                "The Pragmatic Programmer: From Journeyman to Master          | Andrew Hunt and Dave Thomas | 1999\n" +
                "Code Complete: A Practical Handbook of Software Construction | Steve McConnell             | 2004\n");

    }

    @Test
    public void checksOutBooks() {
        PrintStream out = mock(PrintStream.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);
        when(userInputHandler.askForNextString()).thenReturn("Clean Code: A Handbook of Agile Software Craftsmanship");
        BookShelf bookShelf = mock(BookShelf.class);
        when(bookShelf.checkOut(anyString())).thenReturn(true);

        UI ui = new UI(out, userInputHandler, bookShelf);
        ui.handleUserInput("2");

        verify(bookShelf).checkOut("Clean Code: A Handbook of Agile Software Craftsmanship");
    }

    @Test
    public void printCheckoutMessage() {
        PrintStream out = mock(PrintStream.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));
        BookShelf bookShelf = new BookShelf(books);

        UI ui = new UI(out, userInputHandler, bookShelf);
        ui.printBookCheckoutMessage();

        verify(out).println("Select one of the following books by giving the title:");
        verify(out).print("Clean Code: A Handbook of Agile Software Craftsmanship       | Robert C. Martin            | 2008\n" +
                "The Pragmatic Programmer: From Journeyman to Master          | Andrew Hunt and Dave Thomas | 1999\n" +
                "Code Complete: A Practical Handbook of Software Construction | Steve McConnell             | 2004\n");
    }

    @Test
    public void printCheckoutSuccess() {
        PrintStream out = mock(PrintStream.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);

        BookShelf bookShelf = mock(BookShelf.class);
        when(bookShelf.checkOut(anyString())).thenReturn(true);

        UI ui = new UI(out,userInputHandler,bookShelf);
        ui.bookCheckoutMenu();

        //only print on success
        when(bookShelf.checkOut(anyString())).thenReturn(false);
        ui.bookCheckoutMenu();
        ui.bookCheckoutMenu();

        verify(out,times(1)).println("Thank you! Enjoy the book");
    }

    @Test
    public void printCheckoutFailure() {
        PrintStream out = mock(PrintStream.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);

        BookShelf bookShelf = mock(BookShelf.class);
        when(bookShelf.checkOut(anyString())).thenReturn(true);

        UI ui = new UI(out,userInputHandler,bookShelf);
        ui.bookCheckoutMenu();

        //only print on failure
        when(bookShelf.checkOut(anyString())).thenReturn(false);
        ui.bookCheckoutMenu();
        ui.bookCheckoutMenu();

        verify(out,times(2)).println("Sorry, that book is not available");
    }

    @Test
    public void printsErrorOnWrongInput() {

        PrintStream out = mock(PrintStream.class);
        InputStream in = mock(InputStream.class);
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));
        UI ui = new UI(out, new UserInputHandler(in), new BookShelf(books));

        assertThat(ui.handleUserInput("34"), is(true));
        assertThat(ui.handleUserInput("e3"), is(true));
        assertThat(ui.handleUserInput("-1"), is(true));

        verify(out, times(3)).println("Please select a valid option!");
    }

}
