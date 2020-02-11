package com.twu.biblioteca;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AuthenticationManagerTests {

    private final String adminNr = "999-7777";
    private final String adminPwd = "admin123";
    private final String userNr = "123-1234";
    private final String userPwd = "password";


    @Test
    public void logsInAsUser() {
        AuthenticationManager authenticationManager = new AuthenticationManager();

        assertThat(authenticationManager.login(userNr,userPwd),is(true));
        assertThat(authenticationManager.getAccessLevel(),is(Privileges.USER));
    }

    @Test
    public void logsInAsAdmin() { ;
        AuthenticationManager authenticationManager = new AuthenticationManager();

        assertThat(authenticationManager.login(adminNr,adminPwd),is(true));
        assertThat(authenticationManager.getAccessLevel(),is(Privileges.ADMIN));
    }

    @Test
    public void failsWithInvalidCredentials() {
        AuthenticationManager authenticationManager = new AuthenticationManager();

        assertThat(authenticationManager.login("999-9999","password"),is(false));
        assertThat(authenticationManager.login("123-1234","pa$$word"),is(false));
        assertThat(authenticationManager.getAccessLevel(),is(Privileges.NONE));
    }

    @Test(expected = AuthenticationException.class)
    public void guestIsNoUser() throws AuthenticationException {
        AuthenticationManager authenticationManager = new AuthenticationManager();
        authenticationManager.verify(Privileges.USER);
    }

    @Test(expected = AuthenticationException.class)
    public void guestIsNoAdmin() throws AuthenticationException {
        AuthenticationManager authenticationManager = new AuthenticationManager();
        authenticationManager.verify(Privileges.ADMIN);
    }

    @Test(expected = AuthenticationException.class)
    public void userIsNoAdmin() throws AuthenticationException {
        AuthenticationManager authenticationManager = new AuthenticationManager();
        authenticationManager.login(userNr,userPwd);
        authenticationManager.verify(Privileges.ADMIN);
    }

    @Test(expected = Test.None.class)
    public void priviledgedUsersCanAccess() throws AuthenticationException {
        AuthenticationManager authenticationManager = new AuthenticationManager();
        authenticationManager.verify(Privileges.NONE);
        authenticationManager.login(userNr,userPwd);
        authenticationManager.verify(Privileges.NONE);
        authenticationManager.verify(Privileges.USER);
        authenticationManager.login(adminNr, adminPwd);
        authenticationManager.verify(Privileges.NONE);
        authenticationManager.verify(Privileges.USER);
        authenticationManager.verify(Privileges.ADMIN);


    }



}
