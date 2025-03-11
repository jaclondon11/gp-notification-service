package io.gaming.platform.notificationservice.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import io.gaming.platform.notificationservice.model.EventCategory;
import io.gaming.platform.notificationservice.model.NotificationPreference;

@Service
public class NotificationPreferenceServiceImpl implements NotificationPreferenceService {
    private static final Logger log = LoggerFactory.getLogger(NotificationPreferenceServiceImpl.class);
    private final Map<String, NotificationPreference> preferences = new ConcurrentHashMap<>();

    @PostConstruct
    public void initializeDefaultUsers() {
        // Player 1: Social events disabled
        updatePreference("player1", EventCategory.GAME, true);
        updatePreference("player1", EventCategory.SOCIAL, false);
        log.info("Initialized player1 preferences: Game enabled, Social disabled");

        // Player 2: Game events disabled
        updatePreference("player2", EventCategory.GAME, false);
        updatePreference("player2", EventCategory.SOCIAL, true);
        log.info("Initialized player2 preferences: Game disabled, Social enabled");

        // Player 3: Both events disabled
        updatePreference("player3", EventCategory.GAME, false);
        updatePreference("player3", EventCategory.SOCIAL, false);
        log.info("Initialized player3 preferences: Game disabled, Social disabled");
    }

    @Override
    public NotificationPreference getPreference(String playerId) {
        return preferences.computeIfAbsent(playerId, NotificationPreference::createDefault);
    }

    @Override
    public void updatePreference(String playerId, EventCategory category, boolean enabled) {
        preferences.compute(playerId, (key, value) -> {
            NotificationPreference current = value != null ? value : NotificationPreference.createDefault(key);
            Map<EventCategory, Boolean> newPrefs = new ConcurrentHashMap<>(current.preferences());
            newPrefs.put(category, enabled);
            return new NotificationPreference(key, newPrefs);
        });
    }

    @Override
    public boolean isNotificationEnabled(String playerId, EventCategory category) {
        return getPreference(playerId).isEnabled(category);
    }
} 