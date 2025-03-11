package io.gaming.platform.notificationservice.model;

import java.time.Instant;

/**
 * Record representing a notification to be sent to a player.
 * This model contains all the information needed to deliver
 * a notification to a specific player.
 *
 * @param notificationId Unique identifier for the notification
 * @param playerId      ID of the player to receive the notification
 * @param message       The formatted notification message
 * @param category      Category of the notification (GAME or SOCIAL)
 * @param timestamp     When the notification was created
 */
public record UserNotification(
    String notificationId,
    String playerId,
    String message,
    EventCategory category,
    Instant timestamp
) {
    /**
     * Creates a new UserNotification with a generated UUID.
     *
     * @param playerId  ID of the player to receive the notification
     * @param message   The formatted notification message
     * @param category  Category of the notification
     * @param timestamp When the notification was created
     * @return A new UserNotification instance
     */
    public static UserNotification create(String playerId, String message, EventCategory category, Instant timestamp) {
        return new UserNotification(
            java.util.UUID.randomUUID().toString(),
            playerId,
            message,
            category,
            timestamp
        );
    }
} 