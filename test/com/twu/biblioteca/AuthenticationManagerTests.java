package com.twu.biblioteca;

import com.twu.biblioteca.auth.AuthenticationException;
import com.twu.biblioteca.auth.AuthenticationManager;
import com.twu.biblioteca.auth.Privileges;
import com.twu.biblioteca.auth.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AuthenticationManagerTests {

    private static List<User> users;
    private final String adminNr = "999-7777";
    private final String adminPwd = "admin123";
    private final String userNr = "123-1234";
    private final String userPwd = "password";

    private AuthenticationManager authenticationManager;

    @BeforeClass
    public static void beforeClass() throws Exception {
        users = new ArrayList<User>();
        users.add(new User("123-1234","password", Privileges.USER, "John Doe", "john.doe@gmail.com", "(555) 555-1234"));
        users.add(new User("999-7777","admin123", Privileges.ADMIN, "John Doe", "john.doe@gmail.com", "(555) 555-1234"));
    }

    @Before
    public void setUp() throws Exception {
        authenticationManager =  new AuthenticationManager(users);
    }

    @Test
    public void logsInAsUser() {
        assertThat(authenticationManager.login(userNr,userPwd),is(true));
        assertThat(authenticationManager.getPrivilege(),is(Privileges.USER));
    }

    @Test
    public void logsInAsAdmin() { ;
        assertThat(authenticationManager.login(adminNr,adminPwd),is(true));
        assertThat(authenticationManager.getPrivilege(),is(Privileges.ADMIN));
    }

    @Test
    public void failsWithInvalidCredentials() {
        assertThat(authenticationManager.login("999-9999","password"),is(false));
        assertThat(authenticationManager.login("123-1234","pa$$word"),is(false));
        assertThat(authenticationManager.getPrivilege(),is(Privileges.NONE));
    }

    @Test(expected = AuthenticationException.class)
    public void guestIsNoUser() throws AuthenticationException {
        authenticationManager.verify(Privileges.USER);
    }

    @Test(expected = AuthenticationException.class)
    public void guestIsNoAdmin() throws AuthenticationException {
        authenticationManager.verify(Privileges.ADMIN);
    }

    @Test(expected = AuthenticationException.class)
    public void userIsNoAdmin() throws AuthenticationException {
        authenticationManager.login(userNr,userPwd);
        authenticationManager.verify(Privileges.ADMIN);
    }

    @Test(expected = Test.None.class)
    public void priviledgedUsersCanAccess() throws AuthenticationException {
        authenticationManager.verify(Privileges.NONE);
        authenticationManager.login(userNr,userPwd);
        authenticationManager.verify(Privileges.NONE);
        authenticationManager.verify(Privileges.USER);
        authenticationManager.login(adminNr, adminPwd);
        authenticationManager.verify(Privileges.NONE);
        authenticationManager.verify(Privileges.USER);
        authenticationManager.verify(Privileges.ADMIN);


    }

    @Test(expected = Test.None.class)
    public void getUser() throws AuthenticationException {
        authenticationManager.login(userNr,userPwd);
        assertThat(authenticationManager.getCurrentUser(),is(new User("123-1234","password", Privileges.USER, "John Doe", "john.doe@gmail.com", "(555) 555-1234")));
    }



}
