package io.gaming.platform.notificationservice.model;

public enum SocialEventType {
    FRIEND_REQUEST,
    FRIEND_ACCEPTED,
    NEW_FOLLOWER;

    public String toUpperCase() {
        return name();
    }
} 