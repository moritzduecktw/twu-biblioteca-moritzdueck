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
    public void booksAsAlignedTable() {

        MediaRepository mediaRepository = new MediaRepository(books, movies);
        String expected = "Clean Code: A Handbook of Agile Software Craftsmanship       | Robert C. Martin            | 2008\n"
                + "The Pragmatic Programmer: From Journeyman to Master          | Andrew Hunt and Dave Thomas | 1999\n"
                + "Code Complete: A Practical Handbook of Software Construction | Steve McConnell             | 2004\n";

        assertThat(mediaRepository.outputBookList(), is(expected));

    }

    @Test
    public void moviesAsAlignedTable() {
        MediaRepository mediaRepository = new MediaRepository(books, movies);
        String expected = "Chef  | 2014 | Jon Favreau      | 10\n" +
                "RED   | 2010 | Robert Schwentke | 06\n" +
                "Joker | 2019 | Todd Phillips    | NA\n";

        assertThat(mediaRepository.outputMovieList(), is(expected));

    }


    @Test
    public void checksOutBook() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));

        MediaRepository mediaRepository = new MediaRepository(books, movies);

        assertThat(mediaRepository.checkOut("Clean Code: A Handbook of Agile Software Craftsmanship"), is(true));
        assertThat(mediaRepository.checkOut("Not a valid one"), is(false));
        assertThat(mediaRepository.getCheckedOutBooks().get(0).getTitle(), is("Clean Code: A Handbook of Agile Software Craftsmanship"));
        assertThat(mediaRepository.getCheckedOutBooks().size(), is(1));
        assertThat(mediaRepository.getBooks().size(), is(2));
    }

    @Test
    public void returnsBook() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));

        MediaRepository mediaRepository = new MediaRepository(books, movies);
        mediaRepository.checkOut("Clean Code: A Handbook of Agile Software Craftsmanship");

        assertThat(mediaRepository.returnBook("Not a valid one"), is(false));
        assertThat(mediaRepository.returnBook("Clean Code: A Handbook of Agile Software Craftsmanship"), is(true));
        assertThat(mediaRepository.getCheckedOutBooks().size(), is(0));
        assertThat(mediaRepository.getBooks().size(), is(3));
    }


}
