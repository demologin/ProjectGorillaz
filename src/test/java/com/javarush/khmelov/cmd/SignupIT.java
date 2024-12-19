package com.javarush.khmelov.cmd;

import com.javarush.khmelov.BaseIT;
import com.javarush.khmelov.config.Winter;
import com.javarush.khmelov.repository.UserRepository;
import com.javarush.khmelov.util.Go;
import com.javarush.khmelov.util.Key;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

class SignupIT extends BaseIT {

    private final Signup signup = Winter.find(Signup.class);
    private final UserRepository repository = Winter.find(UserRepository.class);

    @Test
    void doPost() {
        Mockito.when(request.getParameter(Key.LOGIN)).thenReturn("newTestLogin");
        Mockito.when(request.getParameter(Key.PASSWORD)).thenReturn("newTestPassword");
        Mockito.when(request.getParameter(Key.ROLE)).thenReturn("GUEST");

        String uri = signup.doPost(request);
        Assertions.assertEquals(Go.PROFILE, uri);
        Assertions.assertTrue(repository.getAll().toString().contains("newTestLogin"));
    }
}