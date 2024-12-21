package com.javarush.shakhurov.dto;

public class StartPage extends BasePage {

    public static String getTitle() {
        return "Приветствую!\n"
                + "В данном приложении ты можешь испытать свои силы в логических играх.\n"
                + "В каждой игре для победы нужно правильно ответить на 3 вопроса, "
                + "если не верно ответить на один вопрос игра считается проигранной.";
    }
}
