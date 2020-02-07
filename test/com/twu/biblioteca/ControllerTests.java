package com.twu.biblioteca;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ControllerTests {

    private static List<Movie> movies;
    private static List<Book> books;

    @BeforeClass
    public static void beforeClass() {

        books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));


        movies = new ArrayList<Movie>();
        movies.add(new Movie("Chef", 2014, "Jon Favreau", MovieRating.TEN));
        movies.add(new Movie("RED", 2010, "Robert Schwentke", MovieRating.SIX));

    }

    @Test
    public void exitsByMenu() {

        Controller controller = new Controller(mock(ConsoleUI.class), new UserInputHandler(mock(InputStream.class)), new MediaRepository(books, movies));
        assertThat(controller.handleUserInput("0"), is(false));
    }

    @Test
    public void listsBooksByMenu() {

        ConsoleUI consoleUI = mock(ConsoleUI.class);
        Controller controller = new Controller(consoleUI, new UserInputHandler(mock(InputStream.class)), new MediaRepository(books, movies));

        assertThat(controller.handleUserInput("1"), is(true));
        verify(consoleUI).listBooks();
    }

    @Test
    public void checksOutBooksByMenu() {
        ConsoleUI consoleUI = mock(ConsoleUI.class);

        UserInputHandler userInputHandler = mock(UserInputHandler.class);
        when(userInputHandler.askForNextString()).thenReturn("Clean Code: A Handbook of Agile Software Craftsmanship");

        MediaRepository mediaRepository = mock(MediaRepository.class);
        when(mediaRepository.checkOut(anyString())).thenReturn(true);

        Controller controller = new Controller(consoleUI, userInputHandler, mediaRepository);
        controller.handleUserInput("2");

        verify(mediaRepository).checkOut("Clean Code: A Handbook of Agile Software Craftsmanship");
        verify(consoleUI).printCheckoutSuccessMessage();
    }

    @Test
    public void returnsBooksByMenu() {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);

        when(userInputHandler.askForNextString()).thenReturn("book1");
        MediaRepository mediaRepository = mock(MediaRepository.class);
        when(mediaRepository.returnBook("book1")).thenReturn(true);

        Controller controller = new Controller(consoleUI, userInputHandler, mediaRepository);
        controller.handleUserInput("3");

        verify(mediaRepository).returnBook("book1");
        verify(consoleUI).printReturnSuccessMessage();
    }

    @Test
    public void printCheckoutSuccess() {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);

        MediaRepository mediaRepository = mock(MediaRepository.class);
        when(mediaRepository.checkOut(anyString())).thenReturn(true);

        Controller controller = new Controller(consoleUI,userInputHandler, mediaRepository);
        controller.checkoutBook();

        //only print on success
        when(mediaRepository.checkOut(anyString())).thenReturn(false);
        controller.checkoutBook();
        controller.checkoutBook();

        verify(consoleUI,times(1)).printCheckoutSuccessMessage();
    }

    @Test
    public void printCheckoutFailure() {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);

        MediaRepository mediaRepository = mock(MediaRepository.class);
        when(mediaRepository.checkOut(anyString())).thenReturn(true);

        Controller controller = new Controller(consoleUI,userInputHandler, mediaRepository);
        controller.checkoutBook();

        //only print on failure
        when(mediaRepository.checkOut(anyString())).thenReturn(false);
        controller.checkoutBook();
        controller.checkoutBook();

        verify(consoleUI,times(2)).printCheckoutFailureMessage();
    }

    @Test
    public void printReturnSuccess() {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);

        when(userInputHandler.askForNextString()).thenReturn("book1");
        MediaRepository mediaRepository = mock(MediaRepository.class);
        when(mediaRepository.returnBook("book1")).thenReturn(true);

        Controller controller = new Controller(consoleUI, userInputHandler, mediaRepository);
        controller.handleUserInput("3");

        verify(mediaRepository).returnBook("book1");
        verify(consoleUI).printReturnSuccessMessage();
    }

    @Test
    public void printReturnFailure() {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);

        when(userInputHandler.askForNextString()).thenReturn("book1");
        MediaRepository mediaRepository = mock(MediaRepository.class);
        when(mediaRepository.returnBook("book2")).thenReturn(false);

        Controller controller = new Controller(consoleUI, userInputHandler, mediaRepository);
        controller.handleUserInput("3");

        verify(mediaRepository).returnBook("book1");
        verify(consoleUI).printReturnFailureMessage();
    }

    @Test
    public void printsErrorOnWrongMenuInput() {

        ConsoleUI consoleUI = mock(ConsoleUI.class);
        InputStream in = mock(InputStream.class);
        Controller controller = new Controller(consoleUI, new UserInputHandler(in), new MediaRepository(books, movies));

        assertThat(controller.handleUserInput("34"), is(true));
        assertThat(controller.handleUserInput("e3"), is(true));
        assertThat(controller.handleUserInput("-1"), is(true));

        verify(consoleUI, times(3)).printInvalidOptionMessage();
    }

}
