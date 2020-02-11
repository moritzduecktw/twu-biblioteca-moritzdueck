package com.twu.biblioteca.controller;

import com.twu.biblioteca.BibliotecaException;
import com.twu.biblioteca.auth.AuthenticationManager;
import com.twu.biblioteca.auth.Privileges;
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
        return mediaRepository.checkOutBook(title);
    }

    public boolean returnBook(String title) throws BibliotecaException {
        authenticationManager.verify(Privileges.USER);
        return mediaRepository.returnBook(title);
    }

    public boolean checkOutMovie(String name) throws BibliotecaException {
        authenticationManager.verify(Privileges.USER);
        return mediaRepository.checkOutMovie(name);
    }

    public boolean returnMovie(String name) throws BibliotecaException {
        authenticationManager.verify(Privileges.USER);
        return mediaRepository.returnMovie(name);
    }

}
