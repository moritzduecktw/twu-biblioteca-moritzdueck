package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationManager {
    private static AuthenticationManager instance;
    private final List<User> users;

    private AuthenticationManager(){
        users = new ArrayList<User>();
        users.add(new User("123-1234","password"));
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
                    return true;
                }
            }
        }
        return false;
    }
}
