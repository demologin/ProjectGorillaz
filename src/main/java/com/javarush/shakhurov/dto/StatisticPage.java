package com.javarush.shakhurov.dto;

import com.javarush.shakhurov.model.game.Game;

import java.util.List;
import java.util.Map;

public class StatisticPage {
    private List<Map<String, Game>> usersWithGames;

    public StatisticPage(List<Map<String, Game>> usersWithGames) {
        this.usersWithGames = usersWithGames;
    }

    public List<Map<String, Game>> getUsersWithGames() {
        return usersWithGames;
    }
}
