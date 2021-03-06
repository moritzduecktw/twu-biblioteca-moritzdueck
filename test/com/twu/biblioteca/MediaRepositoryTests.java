package com.twu.biblioteca;

import com.twu.biblioteca.auth.Privileges;
import com.twu.biblioteca.auth.User;
import com.twu.biblioteca.model.*;
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

        assertThat(mediaRepository.checkOutBook("Clean Code: A Handbook of Agile Software Craftsmanship",new User("111-1111","password", Privileges.USER, "John Doe", "john.doe@gmail.com", "(555) 555-1234")), is(true));

        assertThat(mediaRepository.getCheckedOutBooks().get(0).getTitle(), is("Clean Code: A Handbook of Agile Software Craftsmanship"));
        assertThat(mediaRepository.getCheckedOutBooks().size(), is(1));
        assertThat(mediaRepository.getAvailableBooks().size(), is(2));
        assert(mediaRepository.getCheckedOutItemsWithUsers().containsKey(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008)));
        assert(mediaRepository.getCheckedOutItemsWithUsers().containsValue(new User("111-1111","password", Privileges.USER, "John Doe", "john.doe@gmail.com", "(555) 555-1234")));

    }

    @Test
    public void returnsBook() throws BibliotecaException {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));
        MediaRepository mediaRepository = new MediaRepository(books, movies);
        mediaRepository.checkOutBook("Clean Code: A Handbook of Agile Software Craftsmanship", new User("111-1111", "password", Privileges.USER, "John Doe", "john.doe@gmail.com", "(555) 555-1234"));

        assertThat(mediaRepository.returnMedia("Clean Code: A Handbook of Agile Software Craftsmanship", new User("111-1111", "password", Privileges.USER, "John Doe", "john.doe@gmail.com", "(555) 555-1234")), is(true));

        assertThat(mediaRepository.getCheckedOutBooks().size(), is(0));
        assertThat(mediaRepository.getAvailableBooks().size(), is(3));
        assert(!mediaRepository.getCheckedOutItemsWithUsers().containsKey(new Movie("Chef", 2014, "Jon Favreau", MovieRating.TEN)));
        assert(!mediaRepository.getCheckedOutItemsWithUsers().containsValue(new User("111-1111","password", Privileges.USER, "John Doe", "john.doe@gmail.com", "(555) 555-1234")));

    }

    @Test(expected = BibliotecaException.class)
    public void returnMediaAsOtherUserFails() throws BibliotecaException {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));
        MediaRepository mediaRepository = new MediaRepository(books, movies);
        mediaRepository.checkOutBook("Clean Code: A Handbook of Agile Software Craftsmanship", new User("111-1111", "password", Privileges.USER, "John Doe", "john.doe@gmail.com", "(555) 555-1234"));

        assertThat(mediaRepository.returnMedia("Clean Code: A Handbook of Agile Software Craftsmanship", new User("222-2222", "password", Privileges.USER, "Bob", "bob@gmail.com", "(555) 555-1221")), is(false));
    }

    @Test
    public void checksOutMovie() throws BibliotecaException {
        List <Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie("Chef", 2014, "Jon Favreau", MovieRating.TEN));
        movies.add(new Movie("RED", 2010, "Robert Schwentke", MovieRating.SIX));
        movies.add(new Movie("Joker", 2019, "Todd Phillips", MovieRating.NONE));

        MediaRepository mediaRepository = new MediaRepository(books, movies);

        assertThat(mediaRepository.checkOutMovie("Chef", new User("111-1111", "password", Privileges.USER, "John Doe", "john.doe@gmail.com", "(555) 555-1234")), is(true));
        assertThat(mediaRepository.getCheckedOutMovies().get(0).getTitle(), is("Chef"));
        assertThat(mediaRepository.getCheckedOutMovies().get(0).getTitle(), is("Chef"));
        assertThat(mediaRepository.getCheckedOutMovies().size(), is(1));
        assertThat(mediaRepository.getAvailableMovies().size(), is(2));
        assert(mediaRepository.getCheckedOutItemsWithUsers().containsKey(new Movie("Chef", 2014, "Jon Favreau", MovieRating.TEN)));
        assert(mediaRepository.getCheckedOutItemsWithUsers().containsValue(new User("111-1111","password", Privileges.USER, "John Doe", "john.doe@gmail.com", "(555) 555-1234")));

    }

    @Test
    public void returnsMovie() throws BibliotecaException {
        List <Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie("Chef", 2014, "Jon Favreau", MovieRating.TEN));
        movies.add(new Movie("RED", 2010, "Robert Schwentke", MovieRating.SIX));
        movies.add(new Movie("Joker", 2019, "Todd Phillips", MovieRating.NONE));
        MediaRepository mediaRepository = new MediaRepository(books, movies);
        mediaRepository.checkOutMovie("Chef", new User("111-1111", "password", Privileges.USER, "John Doe", "john.doe@gmail.com", "(555) 555-1234"));

        assertThat(mediaRepository.returnMedia("Chef", new User("111-1111", "password", Privileges.USER, "John Doe", "john.doe@gmail.com", "(555) 555-1234")), is(true));

        assertThat(mediaRepository.getCheckedOutMovies().size(), is(0));
        assertThat(mediaRepository.getAvailableMovies().size(), is(3));
        assert(!mediaRepository.getCheckedOutItemsWithUsers().containsKey(new Movie("Chef", 2014, "Jon Favreau", MovieRating.TEN)));
        assert(!mediaRepository.getCheckedOutItemsWithUsers().containsValue(new User("111-1111","password", Privileges.USER, "John Doe", "john.doe@gmail.com", "(555) 555-1234")));

    }

    @Test(expected = MediaException.class)
    public void checkoutMovieThrowsExceptionOnInvalidChoice() throws BibliotecaException {
        MediaRepository mediaRepository = new MediaRepository(books, movies);
        mediaRepository.checkOutMovie("not a valid one", new User("111-1111", "password", Privileges.USER, "John Doe", "john.doe@gmail.com", "(555) 555-1234"));
    }

    @Test(expected = MediaException.class)
    public void checkoutBookThrowsExceptionOnInvalidChoice() throws BibliotecaException {
        MediaRepository mediaRepository = new MediaRepository(books, movies);
        mediaRepository.checkOutBook("not a valid one", new User("111-1111", "password", Privileges.USER, "John Doe", "john.doe@gmail.com", "(555) 555-1234"));
    }

    @Test(expected = MediaException.class)
    public void returnMovieThrowsExceptionOnInvalidChoice() throws BibliotecaException {
        MediaRepository mediaRepository = new MediaRepository(books, movies);
        mediaRepository.returnMedia("not a valid one", new User("111-1111", "password", Privileges.USER, "John Doe", "john.doe@gmail.com", "(555) 555-1234"));
    }

    @Test(expected = MediaException.class)
    public void returnBookThrowsExceptionOnInvalidChoice() throws BibliotecaException {
        MediaRepository mediaRepository = new MediaRepository(books, movies);
        mediaRepository.returnMedia("not a valid one", new User("111-1111", "password", Privileges.USER, "John Doe", "john.doe@gmail.com", "(555) 555-1234"));
    }

}
