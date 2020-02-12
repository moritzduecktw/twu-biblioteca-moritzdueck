package com.twu.biblioteca.auth;

import java.util.List;

public class AuthenticationManager {
    private final List<User> users;
    private User user;

    public AuthenticationManager(List<User> users) {
        this.users = users;
        setDefaultUser();
    }

    public boolean login(String libraryNumber, String password) {
        for (User user: users) {
            if(user.getLibraryNumber().equals(libraryNumber)){
                if(user.checkPassword(password)){
                    this.user = user;
                    return true;
                }
            }
        }
        setDefaultUser();
        return false;
    }

    public void verify(Privileges privilege) throws AuthenticationException {
        if(this.user.getPrivilege().compareTo(privilege)<0){
            throw new AuthenticationException("Access Denied. You do not have permission to access this functionality.");
        }
    }

    public User getCurrentUser() {
        return user;
    }

    public Privileges getPrivilege() {
        return this.user.getPrivilege();
    }

    private void setDefaultUser(){
        this.user = new User("000-0000","",Privileges.NONE, "John Doe", "john.doe@gmail.com", "(555) 555-1234");
    }
}
