package com.twu.biblioteca.controller;

import com.twu.biblioteca.BibliotecaException;
import com.twu.biblioteca.auth.AuthenticationManager;
import com.twu.biblioteca.auth.Privileges;
import com.twu.biblioteca.auth.User;
import com.twu.biblioteca.model.MediaRepository;

public class Controller {

    private MediaRepository mediaRepository;
    private AuthenticationManager authenticationManager;

    public Controller(MediaRepository mediaRepository, AuthenticationManager authenticationManager) {
        this.mediaRepository = mediaRepository;
        this.authenticationManager = authenticationManager;
    }

    public boolean checkOutBook(String title) throws BibliotecaException {
        authenticationManager.verify(Privileges.USER);
        return mediaRepository.checkOutBook(title, authenticationManager.getCurrentUser());
    }

    public boolean returnBook(String title) throws BibliotecaException {
        authenticationManager.verify(Privileges.USER);
        return mediaRepository.returnMedia(title,authenticationManager.getCurrentUser());
    }

    public boolean checkOutMovie(String name) throws BibliotecaException {
        authenticationManager.verify(Privileges.USER);
        return mediaRepository.checkOutMovie(name, authenticationManager.getCurrentUser());
    }

    public boolean returnMovie(String name) throws BibliotecaException {
        authenticationManager.verify(Privileges.USER);
        return mediaRepository.returnMedia(name, authenticationManager.getCurrentUser());
    }

    public void login(String libraryNumber, String password) {
        authenticationManager.login(libraryNumber,password);
    }
}
