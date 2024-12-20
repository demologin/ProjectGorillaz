package com.javarush.khmelov.cmd;

import com.javarush.khmelov.config.Winter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.javarush.khmelov.storage.ConstantsCommon.LEFT;
import static com.javarush.khmelov.storage.ConstantsCommon.RIGHT;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameQuestIT extends AbstractTestClass {
    private GameQuest gameQuest;


    @BeforeEach
    void init() {
        gameQuest = Winter.find(GameQuest.class);
    }

    @Test
    @DisplayName("when start then init variable")
    void whenStartThenInitVariable() {
        String actualRedirect = gameQuest.doGet(req);
        Assertions.assertEquals(actualRedirect, "game-quest");
        verify(req).setAttribute(eq("buttonLeft"), eq("Принять вызов"));
        verify(req).setAttribute(eq("buttonRight"), eq("Игнорировать вызов"));
        verify(req).setAttribute(eq("result"), eq(" "));
        verify(req).setAttribute(eq("time"), eq(4));
    }

    @Test
    @DisplayName("when did wrong step then loss")
    void whenDidWrongStepThenLoss() {
        gameQuest.doGet(req);
        when(req.getParameter("pickedButton")).thenReturn(RIGHT);
        gameQuest.doGet(req);
        verify(req).setAttribute(eq("isLoss"), eq(true));
    }

    @Test
    @DisplayName("when did right steps then win")
    void whenDidRightStepsThenWin() {
        gameQuest.doGet(req);
        when(req.getParameter("pickedButton")).thenReturn(LEFT);
        gameQuest.doGet(req);
        when(req.getParameter("pickedButton")).thenReturn(RIGHT);
        gameQuest.doGet(req);
        when(req.getParameter("pickedButton")).thenReturn(RIGHT);
        gameQuest.doGet(req);
        when(req.getParameter("pickedButton")).thenReturn(RIGHT);
        gameQuest.doGet(req);
        when(req.getParameter("pickedButton")).thenReturn(LEFT);
        gameQuest.doGet(req);

        verify(req).setAttribute(eq("isWin"), eq(true));

    }

}
