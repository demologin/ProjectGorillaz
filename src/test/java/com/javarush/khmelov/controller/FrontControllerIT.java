package com.javarush.khmelov.controller;

import com.javarush.khmelov.BaseIT;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FrontControllerIT extends BaseIT {

    @Test
    void whenFrontSendIncorrectMethod_thenReturn501() throws Exception {
        FrontController frontController = new FrontController();
        when(request.getMethod()).thenReturn("IncorrectMethod");
        frontController.init(servletConfig);
        when(request.getRequestURI()).thenReturn("/");
        frontController.service(request, response);
        verify(response).sendError(eq(HttpServletResponse.SC_NOT_IMPLEMENTED), anyString());
    }

}