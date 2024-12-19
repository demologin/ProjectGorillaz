package com.javarush.khmelov.cmd;

import com.javarush.khmelov.BaseIT;
import com.javarush.khmelov.config.NanoSpring;
import com.javarush.khmelov.repository.UserRepository;
import com.javarush.khmelov.util.Go;
import com.javarush.khmelov.util.Key;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class SignupIT extends BaseIT {

    private final Signup signup = NanoSpring.find(Signup.class);
    private final UserRepository repository = NanoSpring.find(UserRepository.class);

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