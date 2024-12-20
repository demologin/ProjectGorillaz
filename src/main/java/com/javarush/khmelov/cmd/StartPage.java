package com.javarush.khmelov.cmd;

import jakarta.servlet.http.HttpServletRequest;

import static com.javarush.khmelov.storage.ConstantsCommon.*;

@SuppressWarnings("unused")
public class StartPage implements Command {
    private static void addConstatnsToSession(HttpServletRequest request) {
        request.getSession().setAttribute("IMG_START_HEAD", IMG_START_HEAD);
        request.getSession().setAttribute("IMG_START_QUEST", IMG_START_QUEST);
        request.getSession().setAttribute("IMG_START_ROULETTE", IMG_START_ROULETTE);
        request.getSession().setAttribute("IMG_START_QUIZ", IMG_START_QUIZ);
        request.getSession().setAttribute("AUDIO_START_FOOTER", AUDIO_START_FOOTER);
    }

    public String doGet(HttpServletRequest request) {
        addConstatnsToSession(request);
        return getView();
    }

}
