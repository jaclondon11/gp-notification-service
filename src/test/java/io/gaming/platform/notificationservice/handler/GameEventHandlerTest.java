package io.gaming.platform.notificationservice.handler;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.gaming.platform.notificationservice.model.EventCategory;
import io.gaming.platform.notificationservice.model.GameEventType;

class GameEventHandlerTest {

    private GameEventHandler gameEventHandler;

    @BeforeEach
    void setUp() {
        gameEventHandler = new GameEventHandler();
    }

    @Test
    void testLevelUpEventMessage() {
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("newLevel", 10);

        String message = gameEventHandler.generateMessage("LEVEL_UP", eventData);

        assertEquals("Congratulations! You've reached level 10!", message);
    }

    @Test
    void testItemAcquiredEventMessage() {
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("rarity", "Legendary");
        eventData.put("itemName", "Excalibur");

        String message = gameEventHandler.generateMessage("ITEM_ACQUIRED", eventData);

        assertEquals("You've acquired the Legendary Excalibur", message);
    }

    @Test
    void testChallengeCompletedEventMessage() {
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("challengeName", "Dragon Slayer");

        String message = gameEventHandler.generateMessage("CHALLENGE_COMPLETED", eventData);

        assertEquals("You've achieved the Dragon Slayer challenge!", message);
    }

    @Test
    void testPvpAttackEventMessage() {
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("attackerId", "123");
        eventData.put("damageDealt", 150);

        String message = gameEventHandler.generateMessage("PVP_ATTACK", eventData);

        assertEquals("You've been attacked by player 123!, damage dealt 150", message);
    }

    @Test
    void testPvpDefeatEventMessage() {
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("victorPlayerId", "456");
        eventData.put("battleLocation", "Dark Forest");
        String defeatTime = "2024-03-20T15:30:00Z";
        eventData.put("defeatTime", defeatTime);

        String message = gameEventHandler.generateMessage("PVP_DEFEAT", eventData);

        // Convert the time to expected format
        Instant instant = Instant.parse(defeatTime);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        String expectedTime = dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        String expectedMessage = String.format("You've been defeated by player 456 in Dark Forest at %s!", expectedTime);

        assertEquals(expectedMessage, message);
    }

    @Test
    void testGetCategory() {
        assertEquals(EventCategory.GAME, gameEventHandler.getCategory());
    }

    @Test
    void testSupportsEventType() {
        assertTrue(gameEventHandler.supportsEventType(GameEventType.LEVEL_UP));
        assertTrue(gameEventHandler.supportsEventType(GameEventType.ITEM_ACQUIRED));
        assertTrue(gameEventHandler.supportsEventType(GameEventType.CHALLENGE_COMPLETED));
        assertTrue(gameEventHandler.supportsEventType(GameEventType.PVP_ATTACK));
        assertTrue(gameEventHandler.supportsEventType(GameEventType.PVP_DEFEAT));
    }

    @Test
    void testInvalidEventType() {
        Map<String, Object> eventData = new HashMap<>();
        assertThrows(IllegalArgumentException.class, 
            () -> gameEventHandler.generateMessage("INVALID_EVENT", eventData));
    }
} 