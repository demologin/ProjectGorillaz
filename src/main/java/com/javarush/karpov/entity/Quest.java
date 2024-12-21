package com.javarush.karpov.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public abstract class Quest {

    private int id;
    private final String name;
    private final List<QuestStage> stages;
    private int totalGames;
    private int wins;
    private int loses;

    public Quest(String name, int id, List<QuestStage> stages) {
        this.name = name;
        this.id = id;
        this.stages = stages;
    }

    public QuestStage getStage(String stageId) {
        try {
            int number = Integer.parseInt(stageId);
            for (QuestStage stage : stages) {
                if (stage.getId() == number) {
                    return stage;
                }
            }
            throw new IllegalArgumentException("QuestStage not found for stageId: " + stageId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("QuestStage not found for stageId: " + stageId, e);
        }
    }

    public void incrementWins() {
        totalGames++;
        wins++;
    }

    public void incrementLoses() {
        totalGames++;
        loses++;
    }

    public Map<String, Integer> calculateGeneralStatistics(User user) {
        int totalGames = 0;
        int totalWins = 0;
        int totalLoses = 0;

        for (Quest quest : user.getAllQuests().values()) {
            totalGames += quest.getTotalGames();
            totalWins += quest.getWins();
            totalLoses += quest.getLoses();
        }

        return Map.of(
                "totalGames", totalGames,
                "totalWins", totalWins,
                "totalLoses", totalLoses);
    }

}