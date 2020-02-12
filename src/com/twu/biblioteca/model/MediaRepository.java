package com.twu.biblioteca.model;

import com.twu.biblioteca.auth.User;

import java.util.*;

public class MediaRepository {
    private List<Book> availableBooks;
    private List<Movie> availableMovies;
    private Map<Media,User> checkedOutItemsWithUsers;

    public MediaRepository(List<Book> availableBooks, List<Movie> availableMovies) {
        this.availableBooks = availableBooks;
        this.availableMovies = availableMovies;
        checkedOutItemsWithUsers = new HashMap<>();
    }

    public List<Book> getAvailableBooks() {
        return this.availableBooks;
    }

    public List<Movie> getAvailableMovies() {
        return availableMovies;
    }

    public Map<Media, User> getCheckedOutItemsWithUsers() {
        return checkedOutItemsWithUsers;
    }

    public List<Book> getCheckedOutBooks() {
        Set<Media> allMedia = this.checkedOutItemsWithUsers.keySet();
        List<Book> books = new ArrayList<>();
        for (Media media : allMedia) {
            if(media.getClass() == Book.class){
                books.add((Book) media);
            }
        }
        return books;
    }

    public List<Movie> getCheckedOutMovies() {
        Set<Media> allMedia = this.checkedOutItemsWithUsers.keySet();
        List<Movie> movies = new ArrayList<>();
        for (Media media : allMedia) {
            if(media.getClass() == Movie.class){
                movies.add((Movie) media);
            }
        }
        return movies;
    }

    public boolean checkOutBook(String titleToCheckOut, User user) throws MediaException {
        for (int i = 0; i < availableBooks.size(); i++) {
            Book book = availableBooks.get(i);

            if (book.getTitle().equals(titleToCheckOut)) {
                this.checkedOutItemsWithUsers.put(book,user);
                this.availableBooks.remove(book);
                return true;
            }
        }
        throw new MediaException("Sorry, that book is not available");
    }


    public boolean checkOutMovie(String nameToCheckout, User user) throws MediaException {
        for (int i = 0; i < availableMovies.size(); i++) {
            Movie movie = availableMovies.get(i);
            if(movie.getTitle().equals(nameToCheckout)){
                checkedOutItemsWithUsers.put(movie,user);
                availableMovies.remove(movie);
                return true;
            }
        }
        throw new MediaException("Sorry, that movie is not available");
    }

    public boolean returnMedia(String titleToReturn, User user) throws MediaException {
        for (Media media: checkedOutItemsWithUsers.keySet()) {
            if(media.getTitle().equals(titleToReturn)){
                checkItemBelongsToUser(media,user);
                checkedOutItemsWithUsers.remove(media);
                if(media.getClass()==Book.class){
                    availableBooks.add((Book)media);
                }else{
                    availableMovies.add((Movie)media);
                }
                return true;
            }
        }
        throw new MediaException("That is not a valid item to return.");
    }

    private void checkItemBelongsToUser(Media media, User user) throws MediaException {
        if(!checkedOutItemsWithUsers.get(media).equals(user)){
            throw new MediaException("You cannot return the item of another user.");
        }
    }
}
