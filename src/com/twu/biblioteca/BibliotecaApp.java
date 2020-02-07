package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));

        BookShelf bookShelf = new BookShelf(books);

        ConsoleUI consoleUI = new ConsoleUI(System.out, bookShelf);
        Controller controller = new Controller(consoleUI, new UserInputHandler(System.in), bookShelf);
        controller.start();
    }
}

