package com.twu.biblioteca.view.command;

import com.twu.biblioteca.controller.Controller;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.view.ConsoleUI;

import java.util.List;

public class ListBooksCommand implements MenuCommand {

    private final ConsoleUI consoleUI;
    private final Controller controller;

    public ListBooksCommand(ConsoleUI consoleUI, Controller controller) {
        this.consoleUI = consoleUI;
        this.controller = controller;
    }

    @Override
    public void execute() {
        List<Book> books = controller.getAvailableBooks();
        consoleUI.listBooks(books);
    }
}
