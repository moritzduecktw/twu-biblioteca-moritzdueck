package com.twu.biblioteca.model;

import java.util.ArrayList;
import java.util.List;

public class MediaRepository {
    private List<Book> checkedOutBooks;
    private List<Book> books;
    private List<Movie> movies;
    private List<Movie> checkedOutMovies;

    public MediaRepository(List<Book> books, List<Movie> movies) {
        this.books = books;
        this.movies = movies;
        this.checkedOutBooks = new ArrayList<Book>();
        this.checkedOutMovies = new ArrayList<Movie>();
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Book> getCheckedOutBooks() {
        return this.checkedOutBooks;
    }

    public List<Movie> getCheckedOutMovies() {
        return this.checkedOutMovies;
    }

    public boolean checkOutBook(String titleToCheckOut) throws MediaException {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);

            if (book.getTitle().equals(titleToCheckOut)) {
                this.checkedOutBooks.add(book);
                this.books.remove(book);
                return true;
            }
        }

        throw new MediaException("Sorry, that book is not available");
    }


    public boolean returnBook(String titleToReturn) throws MediaException {
        for (int i = 0; i < checkedOutBooks.size(); i++) {
            Book book = checkedOutBooks.get(i);
            if (book.getTitle().equals(titleToReturn)) {
                this.books.add(book);
                this.checkedOutBooks.remove(book);
                return true;
            }
        }
        throw new MediaException("That is not a valid book to return.");
    }

    public boolean checkOutMovie(String nameToCheckout) throws MediaException {
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            if(movie.getName().equals(nameToCheckout)){
                checkedOutMovies.add(movie);
                movies.remove(movie);
                return true;
            }
        }
        throw new MediaException("Sorry, that movie is not available");
    }


    public boolean returnMovie(String nameToReturn) throws MediaException {
        for (int i = 0; i < checkedOutMovies.size(); i++) {
            Movie movie = checkedOutMovies.get(i);
            if(movie.getName().equals(nameToReturn)){
                checkedOutMovies.remove(movie);
                movies.add(movie);
                return true;
            }
        }
        throw new MediaException("That is not a valid movie to return.");
    }
}
