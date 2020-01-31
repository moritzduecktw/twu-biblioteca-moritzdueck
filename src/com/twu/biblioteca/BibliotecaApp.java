package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    private PrintStream out;
    private BookShelf bookShelf;

    public BibliotecaApp(PrintStream out, BookShelf bookShelf) {
        this.out = out;
        this.bookShelf = bookShelf;
    }

    public static void main(String[] args) {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));


        BibliotecaApp bibliotecaApp = new BibliotecaApp(System.out,new BookShelf(books));
        bibliotecaApp.start();
    }

    public void start() {
        out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        printAllBooks();
    }

    public void printAllBooks() {
        out.print(bookShelf.outputBookList());
    }


    public void showMenu() {
        out.println("Menu:\n");
        out.println("(1) List of books");
    }
}
