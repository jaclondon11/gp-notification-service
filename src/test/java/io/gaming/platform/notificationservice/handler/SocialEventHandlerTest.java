package io.gaming.platform.notificationservice.handler;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.gaming.platform.notificationservice.model.EventCategory;
import io.gaming.platform.notificationservice.model.SocialEventType;

class SocialEventHandlerTest {

    private SocialEventHandler socialEventHandler;

    @BeforeEach
    void setUp() {
        socialEventHandler = new SocialEventHandler();
    }

    @Test
    void testFriendRequestEventMessage() {
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("otherPlayerId", "Player123");

        String message = socialEventHandler.generateMessage("FRIEND_REQUEST", eventData);

        assertEquals("Player123 has sent you a friend request.", message);
    }

    @Test
    void testFriendAcceptedEventMessage() {
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("otherPlayerId", "Player456");

        String message = socialEventHandler.generateMessage("FRIEND_ACCEPTED", eventData);

        assertEquals("Player456 has accepted your friend request!", message);
    }

    @Test
    void testNewFollowerEventMessage() {
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("otherPlayerId", "Player789");

        String message = socialEventHandler.generateMessage("NEW_FOLLOWER", eventData);

        assertEquals("Player789 is now following you!", message);
    }

    @Test
    void testGetCategory() {
        assertEquals(EventCategory.SOCIAL, socialEventHandler.getCategory());
    }

    @Test
    void testSupportsEventType() {
        assertTrue(socialEventHandler.supportsEventType(SocialEventType.FRIEND_REQUEST));
        assertTrue(socialEventHandler.supportsEventType(SocialEventType.FRIEND_ACCEPTED));
        assertTrue(socialEventHandler.supportsEventType(SocialEventType.NEW_FOLLOWER));
    }

    @Test
    void testInvalidEventType() {
        Map<String, Object> eventData = new HashMap<>();
        assertThrows(IllegalArgumentException.class, 
            () -> socialEventHandler.generateMessage("INVALID_EVENT", eventData));
    }

} 