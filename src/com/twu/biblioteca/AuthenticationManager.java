package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationManager {
    private static AuthenticationManager instance;
    private final List<User> users;
    private Privileges accessLevel;

    private AuthenticationManager(){
        users = new ArrayList<User>();
        users.add(new User("123-1234","password", Privileges.USER));
        users.add(new User("999-7777","admin123", Privileges.ADMIN));
    }

    public static AuthenticationManager getInstance() {
        if(instance == null){
            instance = new AuthenticationManager();
        }
        return instance;
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
}
