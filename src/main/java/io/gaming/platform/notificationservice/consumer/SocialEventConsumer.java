package io.gaming.platform.notificationservice.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.gaming.platform.notificationservice.model.GamingPlatformEvent;
import io.gaming.platform.notificationservice.service.NotificationService;

@Component
public class SocialEventConsumer {
    private static final Logger log = LoggerFactory.getLogger(SocialEventConsumer.class);
    
    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;

    public SocialEventConsumer(NotificationService notificationService, ObjectMapper objectMapper) {
        this.notificationService = notificationService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "social-events", groupId = "notification-service")
    public void consume(String message) {
        try {
        	GamingPlatformEvent event = objectMapper.readValue(message, GamingPlatformEvent.class);
            
            if (event == null || event.eventData() == null) {
                log.error("Invalid social event format: {}", message);
                return;
            }

			notificationService.handleEvent(event);
        } catch (Exception e) {
            log.error("Error processing social event: {}", e.getMessage(), e);
        }
    }
} 