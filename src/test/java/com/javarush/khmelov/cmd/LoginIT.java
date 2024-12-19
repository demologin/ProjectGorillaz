package com.javarush.khmelov.cmd;

import com.javarush.khmelov.config.Config;
import com.javarush.khmelov.config.Winter;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.util.Key;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class LoginIT {

    private Login login;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @BeforeEach
    void init() {
        Config config = Winter.find(Config.class);
        config.fillEmptyRepository();
        login = Winter.find(Login.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
    }

    @Test
    @DisplayName("When login admin then redirect to profile")
    void whenLoginAdminThenRedirectToProfile() {
        when(request.getParameter("login")).thenReturn("Carl");
        when(request.getParameter("password")).thenReturn("admin");

        String actualRedirect = login.doPost(request);
        Assertions.assertEquals("/profile", actualRedirect);

        verify(session)
                .setAttribute(eq(Key.USER), any(User.class));
    }

    @Test
    @DisplayName("When incorrect login then redirect to login")
    void whenIncorrectLoginThenRedirectToLogin() {
        when(request.getParameter("login")).thenReturn("Carl");
        when(request.getParameter("password")).thenReturn("err");

        String actualRedirect = login.doPost(request);
        Assertions.assertEquals("/login", actualRedirect);
    }
}