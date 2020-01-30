package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    private List<Book> books;

    public BibliotecaApp() {
        this.books = new ArrayList<>();
    }

    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }

    public void start() {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        printAllBooks();
    }

    public void printAllBooks() {

       int maxLengthTitle = books.stream()
               .map(Book::getTitle)
               .map(String::length)
               .reduce(0,Integer::max);

        int maxLengthAuthor = books.stream()
                .map(Book::getAuthor)
                .map(String::length)
                .reduce(0,Integer::max);

        for (Book book : books) {
            System.out.println(String.format("%-"+maxLengthTitle+"s",book.getTitle())+" | "+String.format("%-"+maxLengthAuthor+"s",book.getAuthor())+" | "+book.getYear());
        }
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
