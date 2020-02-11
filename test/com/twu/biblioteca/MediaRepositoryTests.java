package com.twu.biblioteca;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MediaRepositoryTests {

    private static List<Book> books;
    private static ArrayList<Movie> movies;

    @BeforeClass
    public static void beforeClass() {
        books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));

        movies = new ArrayList<Movie>();
        movies.add(new Movie("Chef", 2014, "Jon Favreau", MovieRating.TEN));
        movies.add(new Movie("RED", 2010, "Robert Schwentke", MovieRating.SIX));
        movies.add(new Movie("Joker", 2019, "Todd Phillips", MovieRating.NONE));

    }

    @Test
    public void checksOutBook() throws BibliotecaException {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));

        MediaRepository mediaRepository = new MediaRepository(books, movies);

        assertThat(mediaRepository.checkOutBook("Clean Code: A Handbook of Agile Software Craftsmanship"), is(true));
        assertThat(mediaRepository.getCheckedOutBooks().get(0).getTitle(), is("Clean Code: A Handbook of Agile Software Craftsmanship"));
        assertThat(mediaRepository.getCheckedOutBooks().size(), is(1));
        assertThat(mediaRepository.getBooks().size(), is(2));
    }

    @Test
    public void returnsBook() throws BibliotecaException {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));

        MediaRepository mediaRepository = new MediaRepository(books, movies);
        mediaRepository.checkOutBook("Clean Code: A Handbook of Agile Software Craftsmanship");

        assertThat(mediaRepository.returnBook("Clean Code: A Handbook of Agile Software Craftsmanship"), is(true));
        assertThat(mediaRepository.getCheckedOutBooks().size(), is(0));
        assertThat(mediaRepository.getBooks().size(), is(3));
    }

    @Test
    public void checksOutMovie() throws BibliotecaException {
        List <Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie("Chef", 2014, "Jon Favreau", MovieRating.TEN));
        movies.add(new Movie("RED", 2010, "Robert Schwentke", MovieRating.SIX));
        movies.add(new Movie("Joker", 2019, "Todd Phillips", MovieRating.NONE));

        MediaRepository mediaRepository = new MediaRepository(books, movies);

        assertThat(mediaRepository.checkOutMovie("Chef"), is(true));
        assertThat(mediaRepository.getCheckedOutMovies().get(0).getName(), is("Chef"));
        assertThat(mediaRepository.getCheckedOutMovies().size(), is(1));
        assertThat(mediaRepository.getMovies().size(), is(2));

    }

    @Test
    public void returnsMovie() throws BibliotecaException {
        List <Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie("Chef", 2014, "Jon Favreau", MovieRating.TEN));
        movies.add(new Movie("RED", 2010, "Robert Schwentke", MovieRating.SIX));
        movies.add(new Movie("Joker", 2019, "Todd Phillips", MovieRating.NONE));

        MediaRepository mediaRepository = new MediaRepository(books, movies);
        mediaRepository.checkOutMovie("Chef");

        assertThat(mediaRepository.returnMovie("Chef"), is(true));
        assertThat(mediaRepository.getCheckedOutMovies().size(), is(0));
        assertThat(mediaRepository.getMovies().size(), is(3));
    }

    @Test(expected = MediaException.class)
    public void checkoutMoviethrowsExceptionOnInvalidChoice() throws BibliotecaException {
        MediaRepository mediaRepository = new MediaRepository(books, movies);
        mediaRepository.checkOutMovie("not a valid one");
    }

    @Test(expected = MediaException.class)
    public void checkoutBookthrowsExceptionOnInvalidChoice() throws BibliotecaException {
        MediaRepository mediaRepository = new MediaRepository(books, movies);
        mediaRepository.checkOutBook("not a valid one");
    }

    @Test(expected = MediaException.class)
    public void returnMoviethrowsExceptionOnInvalidChoice() throws BibliotecaException {
        MediaRepository mediaRepository = new MediaRepository(books, movies);
        mediaRepository.returnMovie("not a valid one");
    }

    @Test(expected = MediaException.class)
    public void returnBookthrowsExceptionOnInvalidChoice() throws BibliotecaException {
        MediaRepository mediaRepository = new MediaRepository(books, movies);
        mediaRepository.returnBook("not a valid one");
    }


}
