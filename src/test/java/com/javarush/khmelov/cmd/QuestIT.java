package com.javarush.khmelov.cmd;

import com.javarush.khmelov.BaseIT;
import com.javarush.khmelov.config.Winter;
import com.javarush.khmelov.util.Go;
import com.javarush.khmelov.util.Key;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

class QuestIT extends BaseIT {

    private final Quest quest = Winter.find(Quest.class);

    @Test
    void whenOpenQuestPageWithCorrectId_thenGetJsp() {
        Mockito.when(request.getParameter(Key.ID)).thenReturn("1");
        String jsp = quest.doGet(request);
        Assertions.assertEquals("quest", jsp);
    }    
    
    @Test
    void whenAnonymousPostQuest_thenRedirectBackward() {
        Mockito.when(request.getParameter(Key.ID)).thenReturn("1");
        String uri = quest.doPost(request);
        Assertions.assertEquals(Go.QUEST, uri);
    }

    @Test
    void whenNonAdminPostQuest_thenRedirectBackward() {
        Mockito.when(session.getAttribute(Key.USER)).thenReturn(testGuest);
        Mockito.when(request.getParameter(Key.ID)).thenReturn("1");
        String uri = quest.doPost(request);
        Assertions.assertEquals(Go.QUEST, uri);
    }

    @Test
    void whenAdminPostQuest_thenRedirectBackward() {
        Mockito.when(session.getAttribute(Key.USER)).thenReturn(testAdmin);
        Mockito.when(request.getParameter(Key.ID)).thenReturn("1");
        Mockito.when(request.getParameter(Key.QUEST_ID)).thenReturn("1");
        Mockito.when(request.getParameter(Key.QUESTION_ID)).thenReturn("1");
        Mockito.when(request.getParameter(Key.TEXT)).thenReturn("newTestTextQuestion");
        String actualUri = quest.doPost(request);
        String expectedUri = "%s?id=%d#bookmark%d".formatted(Go.QUEST, 1, 1);
        Assertions.assertEquals(expectedUri, actualUri);
    }
}