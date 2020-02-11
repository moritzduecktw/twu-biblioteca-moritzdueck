package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.MediaRepository;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.MovieRating;
import com.twu.biblioteca.view.ConsoleUI;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ConsoleUITests {
    

    @Test
    public void printsWelcomeMessage() {

        PrintStream out = mock(PrintStream.class);
        ConsoleUI consoleUI = new ConsoleUI(out, null);

        consoleUI.printWelcomeMessage();

        verify(out).println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    @Test
    public void printsMenu() {
        PrintStream out = mock(PrintStream.class);
        ConsoleUI consoleUI = new ConsoleUI(out, null);

        consoleUI.printMenu();

        verify(out).println("Menu:\n");
        verify(out).println("(0) Quit");
        verify(out).println("(1) List of books");
        verify(out).println("(2) List of movies");
        verify(out).println("(3) Check-out a book");
        verify(out).println("(4) Return a book");
        verify(out).println("(5) Check-out a movie");
        verify(out).println("(6) Return a movie");
        verify(out).println("(7) Login");

        verifyNoMoreInteractions(out);

    }

    @Test
    public void booksAsAlignedTable() {

        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));
        MediaRepository mediaRepository = new MediaRepository(books, null);
        PrintStream out = mock(PrintStream.class);
        ConsoleUI consoleUI = new ConsoleUI(out, mediaRepository);
        String expected = "Clean Code: A Handbook of Agile Software Craftsmanship       | Robert C. Martin            | 2008\n"
                + "The Pragmatic Programmer: From Journeyman to Master          | Andrew Hunt and Dave Thomas | 1999\n"
                + "Code Complete: A Practical Handbook of Software Construction | Steve McConnell             | 2004\n";

        consoleUI.listBooks();

        verify(out).print(expected);

    }

    @Test
    public void moviesAsAlignedTable() {
        List<Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie("Chef", 2014, "Jon Favreau", MovieRating.TEN));
        movies.add(new Movie("RED", 2010, "Robert Schwentke", MovieRating.SIX));
        movies.add(new Movie("Joker", 2019, "Todd Phillips", MovieRating.NONE));
        MediaRepository mediaRepository = new MediaRepository(null, movies);
        PrintStream out = mock(PrintStream.class);
        ConsoleUI consoleUI = new ConsoleUI(out, mediaRepository);
        String expected = "Chef  | 2014 | Jon Favreau      | 10\n" +
                "RED   | 2010 | Robert Schwentke | 06\n" +
                "Joker | 2019 | Todd Phillips    | NA\n";

        consoleUI.listMovies();

        verify(out).print(expected);

    }
}
