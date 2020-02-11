package com.twu.biblioteca;

import org.junit.Test;

import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class MenuTests {

    @Test
    public void exitsByMenu() {
        Menu menu = new Menu(mock(ConsoleUI.class), new UserInputHandler(mock(InputStream.class)),  null);
        assertThat(menu.handleUserInput("0"), is(false));
    }

    @Test
    public void listsBooksByMenu() {

        ConsoleUI consoleUI = mock(ConsoleUI.class);
        Menu menu = new Menu(consoleUI, new UserInputHandler(mock(InputStream.class)), null);

        assertThat(menu.handleUserInput("1"), is(true));
        verify(consoleUI).listBooks();
    }

    @Test
    public void listsMoviesByMenu() {

        ConsoleUI consoleUI = mock(ConsoleUI.class);
        Menu menu = new Menu(consoleUI, new UserInputHandler(mock(InputStream.class)),  null);

        assertThat(menu.handleUserInput("2"), is(true));
        verify(consoleUI).listMovies();
    }

    @Test
    public void checksOutBooksByMenu() throws BibliotecaException {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);
        when(userInputHandler.askForNextString()).thenReturn("Clean Code: A Handbook of Agile Software Craftsmanship");
        Controller controller = mock(Controller.class);
        when(controller.checkOutBook(anyString())).thenReturn(true);
        Menu menu = new Menu(consoleUI, userInputHandler, controller);

        menu.handleUserInput("3");

        verify(controller).checkOutBook("Clean Code: A Handbook of Agile Software Craftsmanship");
        verify(consoleUI).printCheckoutSuccessMessage();
    }

    @Test
    public void returnsBooksByMenu() throws BibliotecaException {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);
        when(userInputHandler.askForNextString()).thenReturn("book1");
        Controller controller = mock(Controller.class);
        when(controller.returnBook(anyString())).thenReturn(true);
        Menu menu = new Menu(consoleUI, userInputHandler, controller);

        menu.handleUserInput("4");

        verify(controller).returnBook("book1");
        verify(consoleUI).printReturnSuccessMessage();
    }

    @Test
    public void checksOutMovieByMenu() throws BibliotecaException {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);
        when(userInputHandler.askForNextString()).thenReturn("Chef");
        Controller controller = mock(Controller.class);
        when(controller.checkOutMovie(anyString())).thenReturn(true);
        Menu menu = new Menu(consoleUI, userInputHandler, controller);

        menu.handleUserInput("5");

        verify(controller).checkOutMovie("Chef");
        verify(consoleUI).printCheckoutSuccessMessage();
    }

    @Test
    public void returnsMovieByMenu() throws BibliotecaException {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);
        when(userInputHandler.askForNextString()).thenReturn("Chef");
        Controller controller = mock(Controller.class);
        when(controller.returnMovie(anyString())).thenReturn(true);
        Menu menu = new Menu(consoleUI, userInputHandler, controller);

        menu.handleUserInput("6");

        verify(controller).returnMovie("Chef");
        verify(consoleUI).printReturnSuccessMessage();
    }

    @Test
    public void printsErrorOnWrongMenuInput() {

        ConsoleUI consoleUI = mock(ConsoleUI.class);
        InputStream in = mock(InputStream.class);
        Menu menu = new Menu(consoleUI, new UserInputHandler(in), null);

        assertThat(menu.handleUserInput("34"), is(true));
        assertThat(menu.handleUserInput("e3"), is(true));
        assertThat(menu.handleUserInput("-1"), is(true));

        verify(consoleUI, times(3)).printInvalidOptionMessage();
    }

}
