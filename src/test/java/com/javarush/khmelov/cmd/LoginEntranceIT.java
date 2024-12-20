package com.javarush.khmelov.cmd;

import com.javarush.khmelov.config.Winter;
import com.javarush.khmelov.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.javarush.khmelov.storage.ConstantsCommon.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginEntranceIT extends AbstractTestClass {

    private LoginEntrance loginEntr;

    @BeforeEach
    void init() {
        loginEntr = Winter.find(LoginEntrance.class);
    }

    @Test
    @DisplayName("when log then return to start with  user in session")
    void whenLogThenReturnToStartWithNeUserInSession() {
        when(req.getParameter("login")).thenReturn("Khmelov");
        when(req.getParameter("password")).thenReturn("admin");

        String actualRedirect = loginEntr.doPost(req);
        Assertions.assertEquals(actualRedirect, GO_START);

        verify(session).setAttribute(eq("user"), any(User.class));
    }

    @Test
    @DisplayName("when empty field then exception")
    void whenEmptyFieldThenException() {
        when(req.getParameter("login")).thenReturn("");
        when(req.getParameter("password")).thenReturn("123");

        loginEntr.doPost(req);

        verify(session, never()).setAttribute(eq("user"), any(User.class));

        Assertions.assertEquals(eq(ERROR_NO_ARGS), session.getAttribute(ERROR_MESSAGE));
    }

    @Test
    @DisplayName("when invalid data then error msg")
    void whenInvalidDataThenErrorMsg() {
        when(req.getParameter("login")).thenReturn("Khmelov");
        when(req.getParameter("password")).thenReturn("123");

        loginEntr.doPost(req);

        verify(session, never()).setAttribute(eq("user"), any(User.class));

        Assertions.assertEquals(eq(ERROR_PASSWORD_OR_LOGIN_INCORRECT), session.getAttribute(ERROR_MESSAGE));
    }


}