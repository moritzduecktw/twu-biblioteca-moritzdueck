package com.twu.biblioteca.auth;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(libraryNumber, user.libraryNumber) &&
                Objects.equals(password, user.password) &&
                privilege == user.privilege;
    }

    @Override
    public int hashCode() {
        return Objects.hash(libraryNumber, password, privilege);
    }
}
