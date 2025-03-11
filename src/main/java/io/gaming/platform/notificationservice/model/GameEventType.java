package io.gaming.platform.notificationservice.model;

public enum GameEventType {
    LEVEL_UP,
    ITEM_ACQUIRED,
    CHALLENGE_COMPLETED,
    PVP_ATTACK,
    PVP_DEFEAT;

    public String toUpperCase() {
        return name();
    }
} 