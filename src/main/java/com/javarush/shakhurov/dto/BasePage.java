package com.javarush.shakhurov.dto;

import java.util.HashMap;
import java.util.Map;

public class BasePage {
    private String flash;
    private String statusAnswer;
    private String question;
    private static Map<String, String> userInfo = new HashMap<>();

    public String getFlash() {
        return flash;
    }

    public void setFlash(String flash) {
        this.flash = flash;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getStatusAnswer() {
        return statusAnswer;
    }

    public void setStatusAnswer(String statusAnswer) {
        this.statusAnswer = statusAnswer;
    }

    public static Map<String, String> getUserInfo() {
        return userInfo;
    }

    public static void setUserInfo(Map<String, String> userInfo) {
        BasePage.userInfo = userInfo;
    }
}
