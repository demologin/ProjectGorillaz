package com.javarush.shakhurov.model;

import com.javarush.shakhurov.model.game.CalcGame;
import com.javarush.shakhurov.model.game.EvenGame;
import com.javarush.shakhurov.model.game.Game;
import com.javarush.shakhurov.model.game.ProgressionGame;

public class FactoryGame {

    public Game getGame(String nameGame) {
        switch (nameGame) {
            case "CalcGame" : return new CalcGame();
            case "Калькулятор" : return new CalcGame();
            case "EvenGame" : return new EvenGame();
            case "Чётное/нечётное" : return new EvenGame();
            case "ProgressionGame" : return new ProgressionGame();
            case "Прогрессия" : return new ProgressionGame();
            default : throw new IllegalStateException("Unexpected value: " + nameGame);
        }
    }
}
