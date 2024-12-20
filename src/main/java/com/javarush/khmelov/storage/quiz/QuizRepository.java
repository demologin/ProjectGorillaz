package com.javarush.khmelov.storage.quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.javarush.khmelov.storage.quiz.QuizConstants.NUMBER_OF_QUESTIONS;
import static com.javarush.khmelov.storage.quiz.QuizConstants.TEXT_FILE;

public class QuizRepository {
    private final static Map<String, String> questionMap = new LinkedHashMap<>();
    Map<String, String> randomMap = new LinkedHashMap<>();

    public QuizRepository() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(TEXT_FILE);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                String[] split = line.split("\\|");
                if (split.length == 2) { // Проверяем, что строка содержит ровно один символ |
                    questionMap.put(split[0].trim(), split[1].trim());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getRandomQuestionMap() {
        List<String> keys = new ArrayList<>(questionMap.keySet());
        Collections.shuffle(keys);
        for (int i = 0; i < NUMBER_OF_QUESTIONS; i++) {
            String key = keys.get(i);
            randomMap.put(key, questionMap.get(key));
        }
        return randomMap;
    }

    public void clearRandomMap() {
        randomMap.clear();
    }

    public Map<String, String> getRandomMapForTest() {
        return randomMap;
    }
}
