package com.twu.biblioteca;

import com.twu.biblioteca.auth.AuthenticationManager;
import com.twu.biblioteca.auth.Privileges;
import com.twu.biblioteca.controller.Controller;
import com.twu.biblioteca.model.MediaRepository;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ControllerTests {

    @Test()
    public void verifiesCalls() throws BibliotecaException {
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        Controller controller = new Controller(mock(MediaRepository.class), authenticationManager);

        controller.checkOutBook("");
        controller.returnBook("");
        controller.checkOutMovie("");
        controller.returnMovie("");

        verify(authenticationManager, times(4)).verify(Privileges.USER);
    }


}
