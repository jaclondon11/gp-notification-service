package io.gaming.platform.notificationservice.handler;

import java.util.EnumSet;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.gaming.platform.notificationservice.model.EventCategory;
import io.gaming.platform.notificationservice.model.SocialEventType;

@Component
public class SocialEventHandler implements EventHandler<SocialEventType> {
    private static final EnumSet<SocialEventType> SUPPORTED_EVENTS = EnumSet.allOf(SocialEventType.class);

    @Override
    public String generateMessage(String eventType, Map<String, Object> eventData) {
        String otherPlayerId = String.valueOf(eventData.get("otherPlayerId"));
        return switch (SocialEventType.valueOf(eventType)) {
            case FRIEND_REQUEST -> 
                String.format("%s has sent you a friend request.", otherPlayerId);
            case FRIEND_ACCEPTED -> 
                String.format("%s has accepted your friend request!", otherPlayerId);
            case NEW_FOLLOWER -> 
                String.format("%s is now following you!", otherPlayerId);
        };
    }

    @Override
    public EventCategory getCategory() {
        return EventCategory.SOCIAL;
    }

    @Override
    public boolean supportsEventType(SocialEventType eventType) {
        return SUPPORTED_EVENTS.contains(eventType);
    }
} 