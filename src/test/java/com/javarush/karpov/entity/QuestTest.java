package com.javarush.karpov.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class QuestTest {

    private Quest quest;

    @BeforeEach
    void setUp() {
        quest = new Quest("Test Quest", 1, List.of(
                QuestStage.builder()
                        .id(1)
                        .title("Stage 1")
                        .image("stage1.png")
                        .text("Welcome to Stage 1")
                        .selector(Selector.NONE)
                        .defaultOption(new Choice("Default Choice", 2))
                        .button("Next")
                        .state(State.PLAYING)
                        .build(),
                QuestStage.builder()
                        .id(2)
                        .title("Stage 2")
                        .image("stage21.png")
                        .text("Welcome to Stage 2")
                        .selector(Selector.OPTIONS)
                        .option1(new Choice("Option 1", 3))
                        .option2(new Choice("Option 2", 201))
                        .button("Choose")
                        .state(State.PLAYING)
                        .build()
        )) {};
    }

    @Test
    void getStage_ValidStageId_ReturnsStage() {
        QuestStage stage = quest.getStage("1");

        assertNotNull(stage, "Stage should not be null");
        assertEquals(1, stage.getId(), "Stage ID should match the input ID");
        assertEquals("Stage 1", stage.getTitle(), "Stage title should match expected value");
        assertEquals("stage1.png", stage.getImage(), "Stage image should match expected value");
        assertEquals("Proceed", stage.getButton(), "Button text should match expected value");
    }

    @Test
    void getStage_InvalidStageId_ThrowsIllegalArgumentException() {
        String wrongStageId = "999";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> quest.getStage(wrongStageId),
                "Expected getStage to throw IllegalArgumentException for non-existent stage ID"
        );

        assertTrue(exception.getMessage().contains("QuestStage not found for stageId: " + wrongStageId));
    }

    @Test
    void getStage_NonNumericStageId_ThrowsIllegalArgumentException() {
        String wrongStageId = "abc";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> quest.getStage(wrongStageId),
                "Expected getStage to throw IllegalArgumentException for non-numeric stage ID"
        );

        assertTrue(exception.getMessage().contains("QuestStage not found for stageId: " + wrongStageId));
    }

    @Test
    void incrementWins_IncrementsWinsAndTotalGames() {
        quest.incrementWins();

        assertEquals(1, quest.getWins(), "Wins should be incremented");
        assertEquals(1, quest.getTotalGames(), "Total games should be incremented");
        assertEquals(0, quest.getLoses(), "Loses should not change");
    }

    @Test
    void incrementLoses_IncrementsLosesAndTotalGames() {
        quest.incrementLoses();

        assertEquals(1, quest.getLoses(), "Loses should be incremented");
        assertEquals(1, quest.getTotalGames(), "Total games should be incremented");
        assertEquals(0, quest.getWins(), "Wins should not change");
    }

    @Test
    void calculateGeneralStatistics_CorrectlyCalculatesStats() {
        User user = new User();

        Quest quest1 = user.getAllQuests().get(1);
        Quest quest2 = user.getAllQuests().get(2);
        Quest quest3 = user.getAllQuests().get(3);

        quest1.incrementWins();
        quest1.incrementLoses();

        quest2.incrementWins();
        quest2.incrementWins();
        quest2.incrementLoses();

        quest3.incrementLoses();

        Map<String, Integer> stats = quest1.calculateGeneralStatistics(user);

        assertEquals(6, stats.get("totalGames"), "Total games should be the sum of all games across quests");
        assertEquals(3, stats.get("totalWins"), "Total wins should be the sum of all wins across quests");
        assertEquals(3, stats.get("totalLoses"), "Total loses should be the sum of all loses across quests");
    }


}
