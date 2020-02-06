package com.twu.biblioteca;

import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class IntegrationTests {

    @Test
    public void printMenuAndWelcomeMessageOnStartUp() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));

        PrintStream out = mock(PrintStream.class);
        UserInterface userInterface = mock(UserInterface.class);
        when(userInterface.getUserSelection()).thenReturn(0);
        throw new UnsupportedOperationException("cannot actually call welcome user method which would be required");

        /*Controller controller = new Controller(out, new BookShelf(books));
        controller.setUserInterface(userInterface);

        controller.start();

        verify(out).println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        verify(out).println("Menu:");
        verify(out).println("(1) List of Books");*/

    }

    @Test
    public void showBookListOnUserInputOne() {

        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));

        PrintStream mockedPrintStream = mock(PrintStream.class);

        int userInput = 1;
        UserInterface userInterface = mock(UserInterface.class);
        when(userInterface.getUserSelection()).thenReturn(userInput);

        Controller controller = new Controller(mockedPrintStream, new BookShelf(books));
        controller.setUserInterface(userInterface);
        controller.start();

        verify(mockedPrintStream).print("Clean Code: A Handbook of Agile Software Craftsmanship       | Robert C. Martin            | 2008\n" +
                "The Pragmatic Programmer: From Journeyman to Master          | Andrew Hunt and Dave Thomas | 1999\n" +
                "Code Complete: A Practical Handbook of Software Construction | Steve McConnell             | 2004\n");


    }
}
