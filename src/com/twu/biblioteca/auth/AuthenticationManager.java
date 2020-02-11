package com.twu.biblioteca.auth;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationManager {
    private static AuthenticationManager instance;
    private final List<User> users;
    private Privileges accessLevel;

    public AuthenticationManager(){
        users = new ArrayList<User>();
        users.add(new User("123-1234","password", Privileges.USER));
        users.add(new User("999-7777","admin123", Privileges.ADMIN));
        this.accessLevel = Privileges.NONE;
    }

    public boolean login(String libraryNumber, String password) {
        for (User user: users) {
            if(user.getLibraryNumber().equals(libraryNumber)){
                if(user.getPassword().equals(password)){
                    this.accessLevel = user.getPrivilege();
                    return true;
                }
            }
        }
        this.accessLevel = Privileges.NONE;
        return false;
    }

    public Privileges getAccessLevel() {
        return accessLevel;
    }

    public void verify(Privileges privilege) throws AuthenticationException {
        if(this.accessLevel.compareTo(privilege)<0){
            throw new AuthenticationException("Access Denied. You do not have permission to access this functionality.");
        }
    }
}
