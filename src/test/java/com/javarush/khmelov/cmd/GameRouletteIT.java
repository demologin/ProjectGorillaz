package com.javarush.khmelov.cmd;

import com.javarush.khmelov.service.UserService;
import com.javarush.khmelov.storage.roulette.RouletteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GameRouletteIT {
    private GameRoulette gameRoulette;
    private HttpSession session;
    private HttpServletRequest req;
    private RouletteService rouletteService;
    private UserService userService;
    private Map<String, String> rouletteMap;

    @BeforeEach
    void init() {
        // Создание моков
        session = mock(HttpSession.class);
        req = mock(HttpServletRequest.class);
        rouletteService = mock(RouletteService.class);
        userService = mock(UserService.class);

        // Настройка карты значений рулетки
        rouletteMap = new HashMap<>();
        rouletteMap.put("RESULT_COLOR_RED", "Red");
        rouletteMap.put("RESULT_COLOR_BLACK", "Black");
        rouletteMap.put("RESULT_COLOR_ZERO", "Zero");
        rouletteMap.put("IMAGE_URL_RED", "/images/red.png");
        rouletteMap.put("IMAGE_URL_BLACK", "/images/black.png");
        rouletteMap.put("IMAGE_URL_ZERO", "/images/zero.png");
        rouletteMap.put("RESULT_WIN", "You win!");
        rouletteMap.put("RESULT_LOSS", "You lose!");
        rouletteMap.put("START_DESCRIPTION", "Place your bet!");
        rouletteMap.put("RED_BUTTON_DESCRIPTION", "Bet on red");
        rouletteMap.put("BLACK_BUTTON_DESCRIPTION", "Bet on black");
        rouletteMap.put("ZERO_BUTTON_DESCRIPTION", "Bet on zero");
        rouletteMap.put("IMAGE_URL_START", "/images/start.png");

        when(rouletteService.getRoulletteMap()).thenReturn(rouletteMap);

        // Создание объекта тестируемого класса
        gameRoulette = new GameRoulette(userService, rouletteService);
    }

    @Test
    @DisplayName("Test if roulette color is RED and user picks RED")
    void testRouletteColorRedAndUserPicksRed() {
        // Настройка мока для запроса
        when(req.getSession()).thenReturn(session);
        when(req.getParameter("pickedButton")).thenReturn("RED");

        // Настройка результата вращения рулетки
        when(rouletteService.getResultOfRotation()).thenReturn("RED");

        // Выполнение тестируемого метода
        String actualRedirect = gameRoulette.doGet(req);

        // Проверки
        verify(req).setAttribute("imageUrl", "/images/red.png");
        verify(req).setAttribute("resultColor", "Red");
        verify(req).setAttribute("winLossDescription", "You win!");
        verify(req).setAttribute("isDone", true);

        assertEquals("game-roulette", actualRedirect);
    }

    @Test
    @DisplayName("Test if roulette color is BLACK and user picks RED")
    void testRouletteColorBlackAndUserPicksRed() {
        // Настройка мока для запроса
        when(req.getSession()).thenReturn(session);
        when(req.getParameter("pickedButton")).thenReturn("RED");

        // Настройка результата вращения рулетки
        when(rouletteService.getResultOfRotation()).thenReturn("BLACK");

        // Выполнение тестируемого метода
        String actualRedirect = gameRoulette.doGet(req);

        // Проверки
        verify(req).setAttribute("imageUrl", "/images/black.png");
        verify(req).setAttribute("resultColor", "Black");
        verify(req).setAttribute("winLossDescription", "You lose!");
        verify(req).setAttribute("isDone", true);

        assertEquals("game-roulette", actualRedirect);
    }

    @Test
    @DisplayName("Test start condition when no button is picked")
    void testStartCondition() {
        // Настройка мока для запроса
        when(req.getSession()).thenReturn(session);
        when(req.getParameter("pickedButton")).thenReturn(null);

        // Выполнение тестируемого метода
        String actualRedirect = gameRoulette.doGet(req);

        // Проверки
        verify(req).setAttribute("START_DESCRIPTION", "Place your bet!");
        verify(req).setAttribute("RED_BUTTON_DESCRIPTION", "Bet on red");
        verify(req).setAttribute("BLACK_BUTTON_DESCRIPTION", "Bet on black");
        verify(req).setAttribute("ZERO_BUTTON_DESCRIPTION", "Bet on zero");
        verify(req).setAttribute("IMAGE_URL_START", "/images/start.png");

        assertEquals("game-roulette", actualRedirect);
    }
}