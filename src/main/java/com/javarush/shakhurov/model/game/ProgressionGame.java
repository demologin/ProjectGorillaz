package com.javarush.shakhurov.model.game;

import com.javarush.shakhurov.utils.RandomInt;

import java.util.Map;

public class ProgressionGame extends Game {
    private static final int LENGTH = 10;
    private static final int MAX_INT = 100;
    private static final int MAX_INDEX = 5;

    @Override
    public String getDescription() {
        return "В данной игре нужно дать ответ какое число пропущено в прогрессии";
    }

    @Override
    public Map<String, String> getQuestionAndAnswer() {
        int start = RandomInt.getRandomInt(0, MAX_INT);
        int index = RandomInt.getRandomInt(1, MAX_INDEX);
        String[] progression = getProgression(start, index);
        int indexEllipsis = RandomInt.getRandomInt(0, LENGTH - 1);
        String answer = progression[indexEllipsis];
        String question = getProgressionWithEllipsis(indexEllipsis, progression);
        return Map.of(question, answer);
    }

    @Override
    public String getName() {
        return "Прогрессия";
    }

    private String[] getProgression(int start, int index) {
        String[] progression = new String[LENGTH];
        progression[0] = String.valueOf(start);
        for (int i = 1; i < progression.length; i++) {
            progression[i] = String.valueOf(Integer.parseInt(progression[i - 1]) + index);
        }
        return progression;
    }

    private String getProgressionWithEllipsis(int indexEllipsis, String[] progression) {
        progression[indexEllipsis] = "...";
        String progressionWithEllipsis = String.join(" ", progression);
        return progressionWithEllipsis;
    }
}
