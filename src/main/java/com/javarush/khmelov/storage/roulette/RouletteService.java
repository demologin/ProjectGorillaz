package com.javarush.khmelov.storage.roulette;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.javarush.khmelov.storage.roulette.RouletteData.*;

public class RouletteService {
    private final static Map<String, String> rouletteMap = new HashMap<>();
    private final Random random = new Random();

    public RouletteService() {
        rouletteMap.put("START_DESCRIPTION", START_DESCRIPTION);
        rouletteMap.put("RED_BUTTON_DESCRIPTION", RED_BUTTON_DESCRIPTION);
        rouletteMap.put("BLACK_BUTTON_DESCRIPTION", BLACK_BUTTON_DESCRIPTION);
        rouletteMap.put("ZERO_BUTTON_DESCRIPTION", ZERO_BUTTON_DESCRIPTION);
        rouletteMap.put("RESULT_COLOR_BLACK", RESULT_COLOR_BLACK);
        rouletteMap.put("RESULT_COLOR_RED", RESULT_COLOR_RED);
        rouletteMap.put("RESULT_COLOR_ZERO", RESULT_COLOR_ZERO);
        rouletteMap.put("RESULT_WIN", RESULT_WIN);
        rouletteMap.put("RESULT_LOSS", RESULT_LOSS);
        rouletteMap.put("RED", RED);
        rouletteMap.put("BLACK", BLACK);
        rouletteMap.put("ZERO", ZERO);

        rouletteMap.put("IMAGE_URL_START", IMAGE_URL_START);
        rouletteMap.put("IMAGE_URL_RED", IMAGE_URL_RED);
        rouletteMap.put("IMAGE_URL_BLACK", IMAGE_URL_BLACK);
        rouletteMap.put("IMAGE_URL_ZERO", IMAGE_URL_ZERO);
    }

    public Map<String, String> getRoulletteMap() {
        return rouletteMap;
    }

    public String getResultOfRotation() {
        // Генерируем случайное число от 0 до 36 (включительно)
        int randomNumber = random.nextInt(37);

        // Вероятность 18/37 для первого варианта
        if (randomNumber < 18) {
            return RED;
        }
        // Вероятность 18/37 для второго варианта
        else if (randomNumber < 36) {
            return BLACK;
        }
        // Вероятность 1/37 для третьего варианта
        else {
            return ZERO;
        }
    }


}
