package com.javarush.karpov.entity;

import com.javarush.karpov.Games.CosmicJourney;
import com.javarush.karpov.Games.LastWish;
import com.javarush.karpov.Games.PinebrookPeril;
import lombok.Getter;
import java.util.Map;

@Getter
public class User {

    private final Map<Integer, Quest> allQuests = Map.of(
            1, new CosmicJourney(),
            2, new PinebrookPeril(),
            3, new LastWish()
    );

    public Quest getQuest(String questId) {
        try {
            int number = Integer.parseInt(questId);
            Quest quest = allQuests.get(number);
            if (quest == null) {
                throw new IllegalArgumentException("Quest not found for questId: " + questId);
            }
            return quest;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Quest not found for questId: " + questId, e);
        }
    }


}
