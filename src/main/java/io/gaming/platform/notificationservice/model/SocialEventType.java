package io.gaming.platform.notificationservice.model;

/**
 * Enumeration of specific social interaction event types.
 * These events represent various player-to-player interactions
 * that can trigger notifications.
 */
public enum SocialEventType {
    /** A player has sent a friend request */
    FRIEND_REQUEST,
    /** A player has accepted a friend request */
    FRIEND_ACCEPTED,
    /** A player has started following another player */
    NEW_FOLLOWER;

    /**
     * Returns the enum name in uppercase.
     * Used for consistent string representation in event processing.
     *
     * @return The uppercase string representation of the enum
     */
    public String toUpperCase() {
        return name();
    }
} 