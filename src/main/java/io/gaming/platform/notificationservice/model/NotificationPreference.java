package io.gaming.platform.notificationservice.model;

import java.util.Map;

/**
 * Record representing a player's notification preferences.
 * This model stores which types of notifications a player has enabled or disabled.
 *
 * @param playerId    The unique identifier of the player
 * @param preferences Map of notification categories to their enabled/disabled status
 */
public record NotificationPreference(
    String playerId,
    Map<EventCategory, Boolean> preferences
) {
    /**
     * Creates a default NotificationPreference for a player with all notifications enabled.
     *
     * @param playerId The unique identifier of the player
     * @return A new NotificationPreference with default settings
     */
    public static NotificationPreference createDefault(String playerId) {
        return new NotificationPreference(
            playerId,
            Map.of(
                EventCategory.GAME, true,
                EventCategory.SOCIAL, true
            )
        );
    }

    /**
     * Checks if notifications are enabled for a specific category.
     *
     * @param category The notification category to check
     * @return true if notifications are enabled for the category, false otherwise
     */
    public boolean isEnabled(EventCategory category) {
        return preferences.getOrDefault(category, true);
    }
} 