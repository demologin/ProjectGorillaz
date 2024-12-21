package com.javarush.shakhurov;

import com.javarush.shakhurov.model.FactoryGame;
import com.javarush.shakhurov.model.game.Game;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private FactoryGame factoryGame = new FactoryGame();

    @Test
    public void createCalcGameTest() {
        String nameGame = "CalcGame";
        Game game = factoryGame.getGame(nameGame);

        String expected = "Калькулятор";
        String actual = game.getName();

        assertEquals(expected, actual);
    }

    @Test
    public void getAnswerAndQuestionForProgressionGameTest() {
        String nameGame = "ProgressionGame";
        Game game = factoryGame.getGame(nameGame);

        Map<String, String> map = game.getQuestionAndAnswer();

        assertFalse(map.isEmpty());
    }

    @Test
    public void createGameWithWrongNameTest() {
        String nameGame = "Game";

        Throwable exception = assertThrows(
                IllegalStateException.class,
                () -> {
                    Game game = factoryGame.getGame(nameGame);
                }
        );

        assertEquals("Unexpected value: Game", exception.getMessage());
    }
}
