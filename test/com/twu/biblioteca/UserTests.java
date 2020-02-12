package com.twu.biblioteca;

import com.twu.biblioteca.auth.Privileges;
import com.twu.biblioteca.auth.User;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class UserTests {

    @Test
    public void userInfo() {
        User user = new User("333-3333","password", Privileges.USER,"John Doe", "john.doe@gmail.com","(555) 555-1234");
        assertThat(user.toString(),equalTo("NR: 333-3333\nName: John Doe\nE-Mail: john.doe@gmail.com\nPhone: (555) 555-1234"));
    }

}
