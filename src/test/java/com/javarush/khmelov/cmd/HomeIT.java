package com.javarush.khmelov.cmd;

import com.javarush.khmelov.BaseIT;
import com.javarush.khmelov.config.NanoSpring;
import com.javarush.khmelov.util.Key;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.mockito.Mockito.*;

class HomeIT extends BaseIT {


    @Test
    void whenOpenPage_thenCommandReturnJspPage() {
        Home home = NanoSpring.find(Home.class);
        String view = home.doGet(request);
        Assertions.assertEquals("home", view);
        verify(request).setAttribute(eq(Key.QUESTS), any(Collection.class));
    }
}