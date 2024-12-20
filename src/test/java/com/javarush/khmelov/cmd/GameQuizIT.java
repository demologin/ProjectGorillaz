package com.javarush.khmelov.cmd;

import com.javarush.khmelov.service.UserService;
import com.javarush.khmelov.storage.quiz.QuizRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GameQuizIT {

    private GameQuiz gameQuiz;
    private UserService userService;
    private QuizRepository quizRepository;
    private HttpServletRequest httpServletRequest;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        quizRepository = mock(QuizRepository.class);
        httpServletRequest = mock(HttpServletRequest.class);

        gameQuiz = new GameQuiz(userService, quizRepository);

        // Мокаем данные для квиза
        Map<String, String> mockQuestions = new LinkedHashMap<>();
        mockQuestions.put("What is 2 + 2?", "4");
        mockQuestions.put("What is the capital of France?", "Paris");

        when(quizRepository.getRandomQuestionMap()).thenReturn(mockQuestions);
    }

    @Test
    void testStartCondition() {
        when(httpServletRequest.getParameter("pickedButton")).thenReturn(null);

        String view = gameQuiz.doGet(httpServletRequest);

        // Проверяем вызовы методов и состояния
        assertEquals("game-quiz", view); // Имя view, соответствующее классу
        verify(quizRepository, times(1)).getRandomQuestionMap();
        verify(httpServletRequest, times(1)).setAttribute("description", "What is 2 + 2?");
        verify(httpServletRequest, times(1)).setAttribute("questionNumber", 1);
    }

    @Test
    void testCorrectAnswer() {
        when(httpServletRequest.getParameter("pickedButton")).thenReturn(null);
        gameQuiz.doGet(httpServletRequest); // Инициализирует questionsMap

        when(httpServletRequest.getParameter("pickedButton")).thenReturn("next");
        when(httpServletRequest.getParameter("answer")).thenReturn("4");

        String view = gameQuiz.doGet(httpServletRequest);

        assertEquals("game-quiz", view);
        verify(httpServletRequest, times(1)).setAttribute("description", "What is the capital of France?");
        verify(httpServletRequest, times(1)).setAttribute("questionNumber", 2);
    }


    @Test
    void testIncorrectAnswer() {
        when(httpServletRequest.getParameter("pickedButton")).thenReturn(null);
        gameQuiz.doGet(httpServletRequest); // Инициализирует questionsMap
        when(httpServletRequest.getParameter("pickedButton")).thenReturn("next");
        when(httpServletRequest.getParameter("answer")).thenReturn("5");

        // Инициализация состояния
        gameQuiz.doGet(httpServletRequest);

        String view = gameQuiz.doGet(httpServletRequest);

        // Проверяем следующий шаг и обработку неправильного ответа
        assertEquals("game-quiz", view);
        verify(httpServletRequest, times(1)).setAttribute("description", "What is the capital of France?");
        verify(httpServletRequest, times(1)).setAttribute("questionNumber", 2);
    }


}