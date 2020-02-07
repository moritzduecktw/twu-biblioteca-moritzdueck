package com.twu.biblioteca;

import org.junit.Test;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ControllerTests {

    @Test
    public void exitsByMenu() {

        PrintStream out = mock(PrintStream.class);
        InputStream in = mock(InputStream.class);
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));
        Controller controller = new Controller(mock(ConsoleUI.class), new UserInputHandler(in), new BookShelf(books));

        assertThat(controller.handleUserInput("0"), is(false));
    }

    @Test
    public void printsAllBooksByMenu() {

        ConsoleUI consoleUI = mock(ConsoleUI.class);
        InputStream in = mock(InputStream.class);
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));
        Controller controller = new Controller(consoleUI, new UserInputHandler(in), new BookShelf(books));

        assertThat(controller.handleUserInput("1"), is(true));
        verify(consoleUI).printAllBooks();
    }

    @Test
    public void checksOutBooksByMenu() {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);
        when(userInputHandler.askForNextString()).thenReturn("Clean Code: A Handbook of Agile Software Craftsmanship");
        BookShelf bookShelf = mock(BookShelf.class);
        when(bookShelf.checkOut(anyString())).thenReturn(true);

        Controller controller = new Controller(consoleUI, userInputHandler, bookShelf);
        controller.handleUserInput("2");

        verify(bookShelf).checkOut("Clean Code: A Handbook of Agile Software Craftsmanship");
    }

    @Test
    public void returnsBooksByMenu() {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);

        when(userInputHandler.askForNextString()).thenReturn("book1");
        BookShelf bookShelf = mock(BookShelf.class);
        when(bookShelf.returnBook("book1")).thenReturn(true);

        Controller controller = new Controller(consoleUI, userInputHandler, bookShelf);
        controller.handleUserInput("3");

        verify(bookShelf).returnBook("book1");
        verify(consoleUI).printReturnSuccessMessage();
    }

    @Test
    public void printCheckoutSuccess() {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);

        BookShelf bookShelf = mock(BookShelf.class);
        when(bookShelf.checkOut(anyString())).thenReturn(true);

        Controller controller = new Controller(consoleUI,userInputHandler,bookShelf);
        controller.bookCheckoutMenu();

        //only print on success
        when(bookShelf.checkOut(anyString())).thenReturn(false);
        controller.bookCheckoutMenu();
        controller.bookCheckoutMenu();

        verify(consoleUI,times(1)).printCheckoutSuccessMessage();
    }

    @Test
    public void printCheckoutFailure() {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);

        BookShelf bookShelf = mock(BookShelf.class);
        when(bookShelf.checkOut(anyString())).thenReturn(true);

        Controller controller = new Controller(consoleUI,userInputHandler,bookShelf);
        controller.bookCheckoutMenu();

        //only print on failure
        when(bookShelf.checkOut(anyString())).thenReturn(false);
        controller.bookCheckoutMenu();
        controller.bookCheckoutMenu();

        verify(consoleUI,times(2)).printCheckoutFailureMessage();
    }

    @Test
    public void printReturnSuccess() {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);

        when(userInputHandler.askForNextString()).thenReturn("book1");
        BookShelf bookShelf = mock(BookShelf.class);
        when(bookShelf.returnBook("book1")).thenReturn(true);

        Controller controller = new Controller(consoleUI, userInputHandler, bookShelf);
        controller.handleUserInput("3");

        verify(bookShelf).returnBook("book1");
        verify(consoleUI).printReturnSuccessMessage();
    }

    @Test
    public void printReturnFailure() {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);

        when(userInputHandler.askForNextString()).thenReturn("book1");
        BookShelf bookShelf = mock(BookShelf.class);
        when(bookShelf.returnBook("book2")).thenReturn(false);

        Controller controller = new Controller(consoleUI, userInputHandler, bookShelf);
        controller.handleUserInput("3");

        verify(bookShelf).returnBook("book1");
        verify(consoleUI).printReturnFailureMessage();
    }

    @Test
    public void printsErrorOnWrongInput() {

        ConsoleUI consoleUI = mock(ConsoleUI.class);
        InputStream in = mock(InputStream.class);
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));
        Controller controller = new Controller(consoleUI, new UserInputHandler(in), new BookShelf(books));

        assertThat(controller.handleUserInput("34"), is(true));
        assertThat(controller.handleUserInput("e3"), is(true));
        assertThat(controller.handleUserInput("-1"), is(true));

        verify(consoleUI, times(3)).printInvalidOptionMessage();
    }

}
