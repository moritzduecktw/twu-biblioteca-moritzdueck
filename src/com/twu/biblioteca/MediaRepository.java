package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class MediaRepository {
    private List<Book> checkedOutBooks;
    private List<Book> books;
    private List<Movie> movies;

    public MediaRepository(List<Book> books, List<Movie> movies) {
        this.books = books;
        this.movies = movies;
        this.checkedOutBooks = new ArrayList<Book>();
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

    public boolean checkOutBook(String titleToCheckOut) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);

            if (book.getTitle().equals(titleToCheckOut)) {
                this.checkedOutBooks.add(book);
                this.books.remove(book);
                return true;
            }
        }

        return false;
    }


    public boolean returnBook(String titleToReturn) {
        for (int i = 0; i < checkedOutBooks.size(); i++) {
            Book book = checkedOutBooks.get(i);
            if (book.getTitle().equals(titleToReturn)) {
                this.books.add(book);
                this.checkedOutBooks.remove(book);
                return true;
            }
        }
        return false;
    }

}
