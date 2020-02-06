package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserInputHandlerTests {

    @Test
    public void asksForNumber1() {
        InputStream in = new ByteArrayInputStream("1".getBytes());
        UserInputHandler userInputHandler = new UserInputHandler(in);
        assertThat(userInputHandler.askForNextString(),is("1"));
    }

    @Test
    public void asksForNumber12() {
        InputStream in = new ByteArrayInputStream("12".getBytes());
        UserInputHandler userInputHandler = new UserInputHandler(in);
        assertThat(userInputHandler.askForNextString(),is("12"));
    }


}
