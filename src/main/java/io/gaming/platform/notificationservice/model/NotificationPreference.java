package io.gaming.platform.notificationservice.model;

import java.util.Map;

public record NotificationPreference(
    String playerId,
    Map<EventCategory, Boolean> preferences
) {
    public static NotificationPreference createDefault(String playerId) {
        return new NotificationPreference(
            playerId,
            Map.of(
                EventCategory.GAME, true,
                EventCategory.SOCIAL, true
            )
        );
    }

    public boolean isEnabled(EventCategory category) {
        return preferences.getOrDefault(category, true);
    }
} 