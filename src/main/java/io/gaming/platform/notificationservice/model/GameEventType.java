package io.gaming.platform.notificationservice.model;

public enum GameEventType {
    /** Player has reached a new level */
    LEVEL_UP,
    /** Player has acquired a new item */
    ITEM_ACQUIRED,
    /** Player has completed a challenge or achievement */
    CHALLENGE_COMPLETED,
    /** Player has been attacked by another player */
    PVP_ATTACK,
    /** Player has been defeated in PvP combat */
    PVP_DEFEAT;

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