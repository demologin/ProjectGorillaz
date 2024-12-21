package com.javarush.karpov.entity;

import com.javarush.karpov.Games.CosmicJourney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void getQuest_ValidQuestId_ReturnsQuest() {

        Quest quest = user.getQuest("1");

        assertNotNull(quest, "Quest should not be null");
        assertEquals(CosmicJourney.class, quest.getClass(), "Quest should be CosmicJourney");
    }

    @Test
    void getQuest_InvalidQuestId_ThrowsIllegalArgumentException() {

        String wrongQuestId = "abc";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> user.getQuest(wrongQuestId),
                "Expected getQuest to throw IllegalArgumentException for non-numeric questId"
        );

        assertTrue(exception.getMessage().contains("Quest not found for questId: " + wrongQuestId));
    }

    @Test
    void getQuest_NonExistentQuestId_ThrowsIllegalArgumentException() {

        String wrongQuestId = "999";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> user.getQuest(wrongQuestId),
                "Expected getQuest to throw IllegalArgumentException for non-existent questId"
        );

        assertTrue(exception.getMessage().contains("Quest not found for questId: " + wrongQuestId));
    }
}