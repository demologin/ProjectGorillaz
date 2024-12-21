package com.javarush.shakhurov.model.game;

import java.util.Map;

public abstract class Game {
    private Long id;
    private Long userId;
    private int countWin;
    private int countLose;
    public abstract String getDescription();
    public abstract Map<String, String> getQuestionAndAnswer();
    public abstract String getName();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getCountWin() {
        return countWin;
    }

    public void setCountWin(int countWin) {
        this.countWin = countWin;
    }

    public int getCountLose() {
        return countLose;
    }

    public void setCountLose(int countLose) {
        this.countLose = countLose;
    }
}
