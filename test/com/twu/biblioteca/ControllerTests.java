package com.twu.biblioteca;

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
