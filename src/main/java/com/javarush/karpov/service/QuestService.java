package com.javarush.karpov.service;

import com.javarush.karpov.entity.Quest;
import com.javarush.karpov.entity.QuestStage;
import com.javarush.karpov.entity.State;
import com.javarush.karpov.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class QuestService {

    public void manageQuest(User user, HttpServletRequest req, HttpSession session) {
        String questId = req.getParameter("questId");
        String stageId = req.getParameter("stageId");

        //Getting quest
        Quest quest = user.getQuest(questId);
        session.setAttribute("quest", quest);

        //Moving to next stage
        QuestStage nextStage = quest.getStage(stageId);
        session.setAttribute("stage", nextStage);

        //Checking Quest State
        if (nextStage.getState() == State.WON || nextStage.getState() == State.LOST) {
            updateQuestStats(quest, nextStage);
            session.setAttribute("generalStats", quest.calculateGeneralStatistics(user));
        }

        handleUserInput(nextStage, session, req);

    }

    private void handleUserInput(QuestStage nextStage, HttpSession session, HttpServletRequest req) {
        if (nextStage.getId() == 0) {
            session.setAttribute("userInput", null);
        } else {
            String userInput = req.getParameter("userInput");
            if (userInput != null) {
                session.setAttribute("userInput", userInput);
            }
        }
    }

    private void updateQuestStats(Quest quest, QuestStage nextStage) {
        if (nextStage.getState() == State.WON) {
            quest.incrementWins();
        } else if (nextStage.getState() == State.LOST) {
            quest.incrementLoses();
        }
    }
}
