package io.gaming.platform.notificationservice.model;

import java.time.Instant;
import java.util.Map;

/**
 * Record representing an event in the gaming platform.
 * This is the core event model that carries information about various
 * game-related and social events that occur in the platform.
 *
 * @param category    The category of the event (GAME or SOCIAL)
 * @param eventId     Unique identifier for the event
 * @param eventType   Type of the event within its category
 * @param timestamp   When the event occurred
 * @param playerId    ID of the player associated with the event
 * @param eventData   Additional data specific to the event type
 */
public record GamingPlatformEvent(
    EventCategory category,
    String eventId,
    String eventType,
    Instant timestamp,
    Long playerId,
    Map<String, Object> eventData
) {} 