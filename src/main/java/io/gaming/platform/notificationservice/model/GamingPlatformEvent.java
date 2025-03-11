package io.gaming.platform.notificationservice.model;

import java.time.Instant;
import java.util.Map;

public record GamingPlatformEvent(
    EventCategory category,
    String eventId,
    String eventType,
    Instant timestamp,
    Long playerId,
    Map<String, Object> eventData
) {} 