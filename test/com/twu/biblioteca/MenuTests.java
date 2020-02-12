package com.twu.biblioteca;

import com.twu.biblioteca.auth.AuthenticationException;
import com.twu.biblioteca.auth.Privileges;
import com.twu.biblioteca.auth.User;
import com.twu.biblioteca.controller.Controller;
import com.twu.biblioteca.view.ConsoleUI;
import com.twu.biblioteca.view.Menu;
import com.twu.biblioteca.view.UserInputHandler;
import org.junit.Test;

import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class MenuTests {


    @Test
    public void printsGuestMenuWithoutLogin() {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        Controller controller = mock(Controller.class);
        when(controller.getPrivilege()).thenReturn(Privileges.NONE);
        Menu menu = new Menu(consoleUI, new UserInputHandler(mock(InputStream.class)),  controller);

        menu.show();

        verify(consoleUI).printGuestMenu();
    }

    @Test
    public void printsUserMenuAfterLogin() {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        Controller controller = mock(Controller.class);
        when(controller.getPrivilege()).thenReturn(Privileges.USER);
        Menu menu = new Menu(consoleUI, mock(UserInputHandler.class), controller);

        menu.show();

        verify(consoleUI).printUserMenu();
    }

    @Test
    public void printsAdminMenuAfterLogin() {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        Controller controller = mock(Controller.class);
        when(controller.getPrivilege()).thenReturn(Privileges.ADMIN);
        Menu menu = new Menu(consoleUI, mock(UserInputHandler.class), controller);

        menu.show();

        verify(consoleUI).printAdminMenu();
    }

    @Test
    public void exitsByMenu() {
        Menu menu = new Menu(mock(ConsoleUI.class), mock(UserInputHandler.class),  null);
        assertThat(menu.handleUserInput("0"), is(false));
    }

    @Test
    public void listsBooksByMenu() {

        ConsoleUI consoleUI = mock(ConsoleUI.class);
        Menu menu = new Menu(consoleUI, mock(UserInputHandler.class), mock(Controller.class));

        assertThat(menu.handleUserInput("1"), is(true));
        verify(consoleUI).listBooks(anyList());
    }

    @Test
    public void listsMoviesByMenu() {

        ConsoleUI consoleUI = mock(ConsoleUI.class);
        Menu menu = new Menu(consoleUI, mock(UserInputHandler.class),  mock(Controller.class));

        assertThat(menu.handleUserInput("2"), is(true));
        verify(consoleUI).listMovies(anyList());
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
    public void handleLogin() {

        UserInputHandler userInputHandler = mock(UserInputHandler.class);
        when(userInputHandler.askForNextString()).thenReturn("111-1111","password");
        Controller controller = mock(Controller.class);
        Menu menu = new Menu(mock(ConsoleUI.class),userInputHandler,controller);
        menu.handleUserInput("7");
        verify(controller).login("111-1111","password");

    }

    @Test
    public void showCurrentBorrowings() {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        Menu menu = new Menu(consoleUI,mock(UserInputHandler.class),mock(Controller.class));

        menu.handleUserInput("8");

        verify(consoleUI).listCurrentBorrowings(anyMap());

    }

    @Test
    public void printCurrentUser() throws AuthenticationException {
        ConsoleUI consoleUI = mock(ConsoleUI.class);
        User user = new User("333-3333","password", Privileges.USER,"John Doe", "john.doe@gmail.com","(555) 555-1234");
        Controller controller = mock(Controller.class);
        when(controller.getCurrentUser()).thenReturn(user);
        Menu menu = new Menu(consoleUI, mock(UserInputHandler.class), controller);

        menu.handleUserInput("9");

        verify(consoleUI).printCurrentUserInfo(user);
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
