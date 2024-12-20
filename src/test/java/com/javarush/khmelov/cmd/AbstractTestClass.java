package com.javarush.khmelov.cmd;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class AbstractTestClass {
    protected HttpSession session;
    protected HttpServletRequest req;
    protected HttpServletResponse res;

    @BeforeEach
    void initCommon() {
        session = mock(HttpSession.class);
        req = mock(HttpServletRequest.class);
        res = mock(HttpServletResponse.class);
        lenient().when(req.getSession()).thenReturn(session);
    }
}
