package com.twu.biblioteca;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BookShelfTests {

    private static List<Book> books;

    @BeforeClass
    public static void beforeClass() {
        books = new ArrayList<Book>();
        books.add(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008));
        books.add(new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt and Dave Thomas", 1999));
        books.add(new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 2004));

    }

    @Test
    public void printBooksAsAlignedTable() {

        BookShelf bookShelf = new BookShelf(books);
        String expected = "Clean Code: A Handbook of Agile Software Craftsmanship       | Robert C. Martin            | 2008\n"
                + "The Pragmatic Programmer: From Journeyman to Master          | Andrew Hunt and Dave Thomas | 1999\n"
                +"Code Complete: A Practical Handbook of Software Construction | Steve McConnell             | 2004\n";

        assertThat(bookShelf.outputBookList(),is(expected));

    }
}
