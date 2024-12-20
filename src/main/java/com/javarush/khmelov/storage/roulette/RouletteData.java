package com.javarush.khmelov.storage.roulette;

import static com.javarush.khmelov.storage.ConstantsCommon.IMG_PATH;

public class RouletteData {
    protected final static String START_DESCRIPTION = "Добро пожаловать в Питерское казино. Делайте ставку!";
    protected final static String RED_BUTTON_DESCRIPTION = "Ставлю на красное";
    protected final static String BLACK_BUTTON_DESCRIPTION = "Ставлю на чёрное";
    protected final static String ZERO_BUTTON_DESCRIPTION = "Ставлю на зеро";
    protected final static String RESULT_COLOR_BLACK = "Выпал чёрный цвет!\n";
    protected final static String RESULT_COLOR_RED = "Выпал красный цвет!\n";
    protected final static String RESULT_COLOR_ZERO = "Выпало зеро!\n";
    protected final static String RESULT_WIN = "Вы победили!";
    protected final static String RESULT_LOSS = "Вы проиграли!";
    protected final static String RED = "RED";
    protected final static String BLACK = "BLACK";
    protected final static String ZERO = "ZERO";

    protected final static String IMAGE_URL_START = IMG_PATH + "roulette_start.jpg";
    protected final static String IMAGE_URL_RED = IMG_PATH + "roulette_red.jpg";
    protected final static String IMAGE_URL_BLACK = IMG_PATH + "roulette_black.jpg";
    protected final static String IMAGE_URL_ZERO = IMG_PATH + "roulette_zero.jpg";
}
