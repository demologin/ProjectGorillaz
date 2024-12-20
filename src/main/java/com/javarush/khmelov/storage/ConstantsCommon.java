package com.javarush.khmelov.storage;

import java.io.File;

public class ConstantsCommon {
    public static final String LEFT = "l";
    public static final String RIGHT = "r";

    public static final int FIRST_STEP = 0;

    public static final String ERROR_MESSAGE = "errorMessage";

    public static final String ERROR_NO_ARGS = "login or password is empty";
    public static final String ERROR_USER_EXIST = "such user already exists";

    public static final String ERROR_PASSWORD_OR_LOGIN_INCORRECT = "login or password is incorrect";

    public static final String IMG_PATH = "images" + File.separator;

    public static final String IMG_START_QUEST = IMG_PATH + "start_quest.jpg";
    public static final String IMG_START_ROULETTE = IMG_PATH + "start_roulette.jpg";
    public static final String IMG_START_QUIZ = IMG_PATH + "start_quiz.jpg";
    public static final String IMG_START_HEAD = IMG_PATH + "start_head.jpg";

    public static final String AUDIO_PATH = "audio" + File.separator;
    public static final String AUDIO_START_FOOTER = AUDIO_PATH + "Vesennijj_Leningrad.mp3";

    public static final String GO_EMPTY_ADRESS = "";
    public static final String GO_HOME = "/home";
    public static final String GO_START = "/start-page";
    public static final String GO_QUEST = "/game-quest";
    public static final String GO_QUIZ = "/game-quiz";
    public static final String GO_ROULETTE = "/game-roulette";
    public static final String GO_LOGIN_ENTRANCE = "/login-entrance";
    public static final String GO_LOGIN_REGISTRATION = "/login-registration";

}
