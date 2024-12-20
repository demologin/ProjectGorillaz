package com.javarush.khmelov.cmd;

import com.javarush.khmelov.service.UserService;
import com.javarush.khmelov.storage.quiz.QuizRepository;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.javarush.khmelov.storage.ConstantsCommon.FIRST_STEP;
import static com.javarush.khmelov.storage.quiz.QuizConstants.NUMBER_OF_QUESTIONS;

@SuppressWarnings("unused")
public class GameQuiz implements Command {
    UserService userService;
    QuizRepository quizRepository;
    Map<String, String> questionsMap;
    List<String> questions = new ArrayList<>();
    Map<String, String> wrongAnswers = new HashMap<>();
    String question;
    String answer;
    int step;

    public GameQuiz(UserService userService, QuizRepository quizRepository) {
        this.userService = userService;
        this.quizRepository = quizRepository;
    }

    @Override
    public String doGet(HttpServletRequest req) {
        String paramName = req.getParameter("pickedButton");
        if (paramName == null) {
            startCondition(req);
        } else {
            if (step != questionsMap.size()) {
                getInfo(req);
                setCondition(req);
            } else {
                setFinalCondition(req);
            }
        }
        step++;
        return getView();
    }

    private void setFinalCondition(HttpServletRequest req) {
        сheckingCorrectnessAnswer(req);
        StringBuilder resultText = buildResultText();
        setFinalAttributes(req, resultText);
        setUserInfo(req);
        clearDataCash();
    }

    private void setUserInfo(HttpServletRequest req) {
        if (wrongAnswers.size() == 0) {
            addUserWin(req, userService);
        } else {
            addUserLoss(req, userService);
        }
    }

    private StringBuilder buildResultText() {
        StringBuilder resultText = new StringBuilder();
        resultText.append("Верных ответов ");
        resultText.append(NUMBER_OF_QUESTIONS - wrongAnswers.size());
        resultText.append(" из  ");
        resultText.append(questionsMap.size());
        resultText.append("\n");
        resultText.append("\n");
        for (String question : wrongAnswers.keySet()) {
            resultText.append("На вопрос: ");
            resultText.append(question);
            resultText.append("\n");
            resultText.append("Получен неверный ответ: ");
            resultText.append(wrongAnswers.get(question));
            resultText.append("\n");
            resultText.append("Верный ответ: ");
            resultText.append(questionsMap.get(question));
            resultText.append("\n\n");
        }
        return resultText;
    }

    private void setFinalAttributes(HttpServletRequest req, StringBuilder resultText) {
        req.setAttribute("description", resultText.toString());
        req.setAttribute("questionNumber", step + 1);
        req.setAttribute("isDone", true);
    }

    private void startCondition(HttpServletRequest req) {
        step = FIRST_STEP;
        questionsMap = quizRepository.getRandomQuestionMap();
        for (String question : questionsMap.keySet()) {
            questions.add(question);
        }
        question = questions.get(step);
        setCondition(req);
    }

    private void getInfo(HttpServletRequest req) {
        сheckingCorrectnessAnswer(req);
        question = questions.get(step);
    }

    private void сheckingCorrectnessAnswer(HttpServletRequest req) {
        String usersAnswer = req.getParameter("answer");
        answer = questionsMap.get(question);
        if (!usersAnswer.equalsIgnoreCase(answer)) {
            wrongAnswers.put(question, usersAnswer);
        }
    }

    private void setCondition(HttpServletRequest req) {
        req.setAttribute("description", question);
        req.setAttribute("questionNumber", step + 1);
    }

    private void clearDataCash() {
        questionsMap.clear();
        questions.clear();
        wrongAnswers.clear();
        quizRepository.clearRandomMap();
    }
}
