package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class MediaRepository {
    private List<Book> checkedOutBooks;
    private List<Book> books;

    public MediaRepository(List<Book> books, List<Movie> movies) {
        this.books = books;
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

        int maxLengthTitle = getMaxTitleLength();
        int maxLengthAuthor = getMaxAuthorLength();

        for (Book book : books) {
            result.append(getFormattedTitle(maxLengthTitle, book.getTitle()) + " | " + getFormattedAuthor(maxLengthAuthor, book) + " | " + book.getYear());
            result.append("\n");
        }

        return result.toString();
    }

    private Integer getMaxAuthorLength() {
        return books.stream()
                .map(Book::getAuthor)
                .map(String::length)
                .reduce(0, Integer::max);
    }

    private Integer getMaxTitleLength() {
        return books.stream()
                .map(Book::getTitle)
                .map(String::length)
                .reduce(0, Integer::max);
    }

    private String getFormattedAuthor(int maxLengthAuthor, Book book) {
        return String.format("%-" + maxLengthAuthor + "s", book.getAuthor());
    }

    private String getFormattedTitle(int maxLengthTitle, String title) {
        return String.format("%-" + maxLengthTitle + "s", title);
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
            if(book.getTitle().equals(titleToReturn)){
                this.books.add(book);
                this.checkedOutBooks.remove(book);
                return true;
            }
        }
        return false;
    }


}
