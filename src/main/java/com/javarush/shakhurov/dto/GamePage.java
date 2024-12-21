package com.javarush.shakhurov.dto;

import com.javarush.shakhurov.model.game.Game;

public class GamePage extends BasePage {
    private Game game;

    public GamePage(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
