package com.javarush.karpov.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import com.javarush.karpov.entity.Quest;
import com.javarush.karpov.entity.QuestStage;
import com.javarush.karpov.entity.State;
import com.javarush.karpov.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class QuestServiceTest {

    private QuestService questService;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private User userMock;
    private Quest questMock;
    private QuestStage stageMock;

    @BeforeEach
    void setUp() {
        questService = new QuestService();
        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);
        userMock = mock(User.class);
        questMock = mock(Quest.class);
        stageMock = mock(QuestStage.class);
    }

    @Test
    void testManageQuest_WinState() {
        when(requestMock.getParameter("questId")).thenReturn("1");
        when(requestMock.getParameter("stageId")).thenReturn("2");
        when(userMock.getQuest("1")).thenReturn(questMock);
        when(questMock.getStage("2")).thenReturn(stageMock);
        when(stageMock.getState()).thenReturn(State.WON);
        when(stageMock.getId()).thenReturn(2);

        questService.manageQuest(userMock, requestMock, sessionMock);

        verify(userMock).getQuest("1");
        verify(questMock).getStage("2");
        verify(sessionMock).setAttribute("quest", questMock);
        verify(sessionMock).setAttribute("stage", stageMock);
        verify(questMock).incrementWins();
        verify(sessionMock).setAttribute(eq("generalStats"), any());
    }

    @Test
    void testManageQuest_LoseState() {
        when(requestMock.getParameter("questId")).thenReturn("1");
        when(requestMock.getParameter("stageId")).thenReturn("3");
        when(userMock.getQuest("1")).thenReturn(questMock);
        when(questMock.getStage("3")).thenReturn(stageMock);
        when(stageMock.getState()).thenReturn(State.LOST);
        when(stageMock.getId()).thenReturn(3);

        questService.manageQuest(userMock, requestMock, sessionMock);

        verify(userMock).getQuest("1");
        verify(questMock).getStage("3");
        verify(sessionMock).setAttribute("quest", questMock);
        verify(sessionMock).setAttribute("stage", stageMock);
        verify(questMock).incrementLoses();
        verify(sessionMock).setAttribute(eq("generalStats"), any());
    }

    @Test
    void testManageQuest_PlayingState() {
        when(requestMock.getParameter("questId")).thenReturn("1");
        when(requestMock.getParameter("stageId")).thenReturn("4");
        when(userMock.getQuest("1")).thenReturn(questMock);
        when(questMock.getStage("4")).thenReturn(stageMock);
        when(stageMock.getState()).thenReturn(State.PLAYING);
        when(stageMock.getId()).thenReturn(4);

        questService.manageQuest(userMock, requestMock, sessionMock);

        verify(userMock).getQuest("1");
        verify(questMock).getStage("4");
        verify(sessionMock).setAttribute("quest", questMock);
        verify(sessionMock).setAttribute("stage", stageMock);
        verify(questMock, never()).incrementWins();
        verify(questMock, never()).incrementLoses();
        verify(sessionMock, never()).setAttribute(eq("generalStats"), any());
    }

    @Test
    void testHandleUserInput() {
        when(requestMock.getParameter("questId")).thenReturn("1");
        when(requestMock.getParameter("stageId")).thenReturn("4");
        when(userMock.getQuest("1")).thenReturn(questMock);
        when(questMock.getStage("4")).thenReturn(stageMock);
        when(stageMock.getId()).thenReturn(1);
        when(requestMock.getParameter("userInput")).thenReturn("Test Input");

        questService.manageQuest(userMock, requestMock, sessionMock);

        verify(sessionMock).setAttribute("userInput", "Test Input");
    }
}
