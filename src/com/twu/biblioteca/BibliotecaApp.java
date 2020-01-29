package com.twu.biblioteca;

public class BibliotecaApp {

    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }

    public void start() {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        printAllBooks();
    }

    public void printAllBooks() {
        System.out.println("Clean Code: A Handbook of Agile Software Craftsmanship");
        System.out.println("The Pragmatic Programmer: From Journeyman to Master");
        System.out.println("Code Complete: A Practical Handbook of Software Construction");
    }
}
