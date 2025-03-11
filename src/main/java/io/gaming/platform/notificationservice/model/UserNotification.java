package io.gaming.platform.notificationservice.model;

import java.time.Instant;

public record UserNotification(
    String notificationId,
    String playerId,
    String message,
    EventCategory category,
    Instant timestamp
) {
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