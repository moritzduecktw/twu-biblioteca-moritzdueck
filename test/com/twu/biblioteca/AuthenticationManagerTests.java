package com.twu.biblioteca;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AuthenticationManagerTests {

    @Test
    public void logsInWithValidCredentials() {
        String libraryNumber = "123-1234";
        String password = "password";
        AuthenticationManager authenticationManager = AuthenticationManager.getInstance();

        assertThat(authenticationManager.login(libraryNumber,password),is(true));
    }

    @Test
    public void failsWithInvalidCredentials() {
        AuthenticationManager authenticationManager = AuthenticationManager.getInstance();

        assertThat(authenticationManager.login("999-9999","password"),is(false));
        assertThat(authenticationManager.login("123-1234","pa$$word"),is(false));

    }
}