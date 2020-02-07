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

    public List<Book> getCheckedOutBooks() {
        return this.checkedOutBooks;
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public String outputBookList() {

        StringBuffer result = new StringBuffer();

        int maxLengthTitle = books.stream()
                .map(Book::getTitle)
                .map(String::length)
                .reduce(0, Integer::max);
        int maxLengthAuthor = books.stream()
                .map(Book::getAuthor)
                .map(String::length)
                .reduce(0, Integer::max);

        for (Book book : books) {
            result.append(paddString(book.getTitle(), maxLengthTitle));
            result.append( " | ");
            result.append( paddString(book.getAuthor(), maxLengthAuthor));
            result.append( " | ");
            result.append( book.getYear());
            result.append("\n");
        }

        return result.toString();
    }

    public String outputMovieList() {
        StringBuffer result = new StringBuffer();

        int maxNameLength = movies.stream()
                .map(Movie::getName)
                .map(String::length)
                .reduce(0, Integer::max);

        int maxDirectorLength = movies.stream()
                .map(Movie::getDirector)
                .map(String::length)
                .reduce(0, Integer::max);

        for (Movie movie : movies) {
            result.append(paddString(movie.getName(), maxNameLength));
            result.append( " | ");
            result.append(movie.getYear());
            result.append( " | ");
            result.append(paddString(movie.getDirector(), maxDirectorLength));
            result.append( " | ");
            result.append(movie.getRating().toString());
            result.append("\n");
        }

        return result.toString();
    }

    private String paddString(String string, int targetLength) {
        return String.format("%-" + targetLength + "s", string);
    }

    public boolean checkOut(String titleToCheckOut) {
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
