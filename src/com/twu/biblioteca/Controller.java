package com.twu.biblioteca;

public class Controller {

    private MediaRepository mediaRepository;
    private AuthenticationManager authenticationManager;

    public Controller(MediaRepository mediaRepository, AuthenticationManager authenticationManager) {
        this.mediaRepository = mediaRepository;
        this.authenticationManager = authenticationManager;
    }

    public boolean checkOutBook(String title) throws AuthenticationException {
        authenticationManager.verify(Privileges.USER);
        return mediaRepository.checkOutBook(title);
    }

    public boolean returnBook(String title) throws AuthenticationException {
        authenticationManager.verify(Privileges.USER);
        return mediaRepository.returnBook(title);
    }

    public boolean checkOutMovie(String name) throws AuthenticationException {
        authenticationManager.verify(Privileges.USER);
        return mediaRepository.checkOutMovie(name);
    }

    public boolean returnMovie(String name) throws AuthenticationException {
        authenticationManager.verify(Privileges.USER);
        return mediaRepository.returnMovie(name);
    }

}
