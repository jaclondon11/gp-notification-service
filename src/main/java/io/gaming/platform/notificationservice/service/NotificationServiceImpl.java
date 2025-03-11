package io.gaming.platform.notificationservice.service;

import java.time.Instant;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.gaming.platform.notificationservice.handler.EventHandlerRegistry;
import io.gaming.platform.notificationservice.model.EventCategory;
import io.gaming.platform.notificationservice.model.GamingPlatformEvent;
import io.gaming.platform.notificationservice.model.UserNotification;
import io.gaming.platform.notificationservice.producer.NotificationProducer;

@Service
public class NotificationServiceImpl implements NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);
    
    private final NotificationPreferenceService preferenceService;
    private final NotificationProducer notificationProducer;
    private final EventHandlerRegistry handlerRegistry;

    public NotificationServiceImpl(
            NotificationPreferenceService preferenceService,
            NotificationProducer notificationProducer,
            EventHandlerRegistry handlerRegistry) {
        this.preferenceService = preferenceService;
        this.notificationProducer = notificationProducer;
        this.handlerRegistry = handlerRegistry;
    }

    @Override
	public void handleEvent(GamingPlatformEvent event) {
		String playerId = event.playerId().toString();
		EventCategory category = event.category();
		String eventType = event.eventType();
		Map<String, Object> eventData = event.eventData();
		Instant timestamp = event.timestamp();
		
		if (!preferenceService.isNotificationEnabled(playerId, category)) {
            log.debug("{} notifications disabled for player {}", category, playerId);
            return;
        }

        
		handlerRegistry.getHandler(category, eventType)
            .ifPresentOrElse(
                handler -> {
                    
					String message = handler.generateMessage(eventType, eventData);
                    if (message != null) {
                        
						sendNotification(playerId, message, category, timestamp);
                    } else {
                        log.warn("No message generated for event type {} in category {} for player {}", 
                            eventType, category, playerId);
                    }
                },
                () -> log.warn("No handler found for event type {} in category {} for player {}", 
                    eventType, category, playerId)
            );
    }

    private void sendNotification(String playerId, String message, EventCategory category, Instant timestamp) {
        UserNotification notification = UserNotification.create(playerId, message, category, timestamp);
        notificationProducer.sendNotification(notification)
            .exceptionally(ex -> {
                log.error("Failed to send {} notification to player {}: {}", 
                    category, playerId, ex.getMessage(), ex);
                return null;
            });
    }
} 