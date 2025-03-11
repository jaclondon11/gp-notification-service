package io.gaming.platform.notificationservice.handler;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.EnumSet;

import org.springframework.stereotype.Component;

import io.gaming.platform.notificationservice.model.EventCategory;
import io.gaming.platform.notificationservice.model.GameEventType;

@Component
public class GameEventHandler implements EventHandler<GameEventType> {
    private static final EnumSet<GameEventType> SUPPORTED_EVENTS = EnumSet.allOf(GameEventType.class);

    @Override
    public String generateMessage(String eventType, Map<String, Object> eventData) {
        
        return switch (GameEventType.valueOf(eventType)) {
            case LEVEL_UP ->
                String.format("Congratulations! You've reached level %s!",
                		eventData.get("newLevel"));
            case ITEM_ACQUIRED -> 
                String.format("You've acquired the %s %s",
                		eventData.get("rarity"),
                		eventData.get("itemName"));
            case CHALLENGE_COMPLETED -> 
                String.format("You've achieved the %s challenge!",
                		eventData.get("challengeName"));
            case PVP_ATTACK -> 
                String.format("You've been attacked by player %s!, damage dealt %s", 
                		eventData.get("attackerId"), 
                		eventData.get("damageDealt"));
            case PVP_DEFEAT -> {
                String defeatTime = (String) eventData.get("defeatTime");
                Instant instant = Instant.parse(defeatTime);
                LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                String formattedTime = dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                yield String.format("You've been defeated by player %s in %s at %s!", 
                		eventData.get("victorPlayerId"),
                		eventData.get("battleLocation"),
                		formattedTime);
            }
        };
    }

    @Override
    public EventCategory getCategory() {
        return EventCategory.GAME;
    }

    @Override
    public boolean supportsEventType(GameEventType eventType) {
        return SUPPORTED_EVENTS.contains(eventType);
    }
} 