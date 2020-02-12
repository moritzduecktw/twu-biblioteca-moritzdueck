package com.twu.biblioteca;

import com.twu.biblioteca.controller.Controller;
import com.twu.biblioteca.view.*;
import com.twu.biblioteca.view.command.*;
import org.junit.Test;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.*;

public class MenuCommandsTests {

    @Test
    public void listBooksCommand() {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        Controller controller = mock(Controller.class);
        MenuCommand listBooksCommand = new ListBooksCommand(consoleUI, controller);

        listBooksCommand.execute();

        verify(controller).getAvailableBooks();
        verify(consoleUI).listBooks(anyList());
    }

    @Test
    public void listMoviesCommand() {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        Controller controller = mock(Controller.class);
        MenuCommand listMoviesCommand = new ListMoviesCommand(consoleUI, controller);

        listMoviesCommand.execute();

        verify(controller).getAvailableMovies();
        verify(consoleUI).listMovies(anyList());
    }

    @Test
    public void loginCommand() {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);
        when(userInputHandler.askForNextString()).thenReturn("111-1111","password");
        Controller controller = mock(Controller.class);
        MenuCommand menuCommand = new LoginCommand(consoleUI, controller, userInputHandler);

        menuCommand.execute();

        verify(controller).login("111-1111","password");
    }

    @Test
    public void checkOutBookCommand() throws BibliotecaException {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);
        when(userInputHandler.askForNextString()).thenReturn("aBook");
        Controller controller = mock(Controller.class);
        MenuCommand menuCommand = new CheckOutBookCommand(consoleUI, controller, userInputHandler);

        menuCommand.execute();

        verify(controller).checkOutBook("aBook");
    }

    @Test
    public void returnBookCommand() throws BibliotecaException {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);
        when(userInputHandler.askForNextString()).thenReturn("aBook");
        Controller controller = mock(Controller.class);
        MenuCommand menuCommand = new ReturnBookCommand(consoleUI, controller, userInputHandler);

        menuCommand.execute();

        verify(controller).returnBook("aBook");
    }

    @Test
    public void checkoutMovieCommand() throws BibliotecaException {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);
        when(userInputHandler.askForNextString()).thenReturn("aMovie");
        Controller controller = mock(Controller.class);
        MenuCommand menuCommand = new CheckOutMovieCommand(consoleUI, controller, userInputHandler);

        menuCommand.execute();

        verify(controller).checkOutMovie("aMovie");
    }

    @Test
    public void returnMovieCommand() throws BibliotecaException {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);
        when(userInputHandler.askForNextString()).thenReturn("aMovie");
        Controller controller = mock(Controller.class);
        MenuCommand menuCommand = new ReturnMovieCommand(consoleUI, controller, userInputHandler);

        menuCommand.execute();

        verify(controller).returnMovie("aMovie");
    }

    @Test
    public void printUserInfoCommand() throws BibliotecaException {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        UserInputHandler userInputHandler = mock(UserInputHandler.class);
        Controller controller = mock(Controller.class);
        MenuCommand menuCommand = new PrintUserInfoCommand(consoleUI, controller);

        menuCommand.execute();

        verify(controller).getCurrentUser();
        verify(consoleUI).printCurrentUserInfo(any());
    }

}
