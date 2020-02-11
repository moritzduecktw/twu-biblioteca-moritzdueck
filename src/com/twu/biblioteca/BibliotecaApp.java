package com.twu.biblioteca;

import com.twu.biblioteca.auth.AuthenticationManager;
import com.twu.biblioteca.controller.Controller;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.MediaRepository;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.MovieRating;
import com.twu.biblioteca.view.ConsoleUI;
import com.twu.biblioteca.view.Menu;
import com.twu.biblioteca.view.UserInputHandler;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));

        List<Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie("Chef", 2014, "Jon Favreau", MovieRating.TEN));
        movies.add(new Movie("RED", 2010, "Robert Schwentke", MovieRating.SIX));

        MediaRepository mediaRepository = new MediaRepository(books, movies);
        AuthenticationManager authenticationManager = new AuthenticationManager();
        Controller controller = new Controller(mediaRepository, authenticationManager);
        ConsoleUI consoleUI = new ConsoleUI(System.out, mediaRepository);
        Menu menu = new Menu(consoleUI, new UserInputHandler(System.in), controller);

        menu.start();
    }
}
