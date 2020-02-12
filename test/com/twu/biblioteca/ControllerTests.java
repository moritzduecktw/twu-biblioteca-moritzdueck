package com.twu.biblioteca;

import com.twu.biblioteca.auth.AuthenticationManager;
import com.twu.biblioteca.auth.Privileges;
import com.twu.biblioteca.controller.Controller;
import com.twu.biblioteca.model.MediaRepository;
import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;

public class ControllerTests {

    @Test()
    public void verifiesCalls() throws BibliotecaException {
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        Controller controller = new Controller(mock(MediaRepository.class), authenticationManager);
        InOrder inOrder = inOrder(authenticationManager);

        controller.returnBook("");
        controller.checkOutMovie("");
        controller.returnMovie("");
        controller.checkOutMovie("");
        controller.getCurrentUser();
        controller.getCheckedOutItemsWithUsers();


        inOrder.verify(authenticationManager, times(5)).verify(Privileges.USER);
        inOrder.verify(authenticationManager, times(1)).verify(Privileges.ADMIN);

    }


}
