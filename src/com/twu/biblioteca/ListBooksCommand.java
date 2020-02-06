package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.List;

public class ListBooksCommand implements Command {

    private List<Book> books;
    private PrintStream out;

    public ListBooksCommand(List<Book> books, PrintStream out) {
        this.books = books;
        this.out = out;
    }

    @Override
    public void execute() {
        StringBuffer result = new StringBuffer();

        int maxLengthTitle = getMaxTitleLength();
        int maxLengthAuthor = getMaxAuthorLength();

        for (Book book : books) {
            result.append(getFormattedTitle(maxLengthTitle, book.getTitle()) + " | " + getFormattedAuthor(maxLengthAuthor, book) + " | " + book.getYear());
            result.append("\n");
        }

        out.print(result.toString());

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
}
