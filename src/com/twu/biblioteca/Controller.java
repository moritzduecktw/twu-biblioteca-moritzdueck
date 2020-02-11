package com.twu.biblioteca;

public class Controller {

    private MediaRepository mediaRepository;

    public Controller(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public boolean checkOutBook(String title) {
        return mediaRepository.checkOutBook(title);
    }

    public boolean returnBook(String title) {
        return mediaRepository.returnBook(title);
    }

    public boolean checkOutMovie(String name) {
        return mediaRepository.checkOutMovie(name);
    }

    public boolean returnMovie(String name) {
        return mediaRepository.returnMovie(name);
    }

}
