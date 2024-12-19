package com.javarush.khmelov.cmd;

import com.javarush.khmelov.config.Config;
import com.javarush.khmelov.config.Winter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BaseIT {
    protected final HttpServletRequest request;
    protected final HttpServletResponse response;
    protected final HttpSession session;
    protected final Config config;

    BaseIT() {
        config = Winter.find(Config.class);
        config.fillEmptyRepository();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
    }
}
