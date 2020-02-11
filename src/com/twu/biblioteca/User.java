package com.twu.biblioteca;

public class User {

    private final String libraryNumber;
    private final String password;
    private Privileges privilege;

    public User(String libraryNumber, String password, Privileges privilege) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.privilege = privilege;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    public Privileges getPrivilege() {
        return privilege;
    }
}
