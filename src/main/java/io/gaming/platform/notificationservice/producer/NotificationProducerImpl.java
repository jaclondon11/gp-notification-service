package io.gaming.platform.notificationservice.producer;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.gaming.platform.notificationservice.model.UserNotification;

@Component
public class NotificationProducerImpl implements NotificationProducer {
    private static final Logger log = LoggerFactory.getLogger(NotificationProducerImpl.class);
    
    private final String topic;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public NotificationProducerImpl(
            @Value("${app.kafka.topic}") String topic,
            KafkaTemplate<String, String> kafkaTemplate, 
            ObjectMapper objectMapper) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public CompletableFuture<SendResult<String, String>> sendNotification(UserNotification notification) {
        try {
            String message = objectMapper.writeValueAsString(notification);
            String key = notification.playerId().toString();

            return kafkaTemplate.send(topic, key, message)
                .thenApply(result -> {
                	log.info("Notification sent successfully to player {}: {}", 
                            notification.playerId(), notification.message());
                    return result;
                })
                .exceptionally(ex -> {
                	log.error("Failed to send notification to player {}: {}", 
                            notification.playerId(), ex.getMessage(), ex);
                    throw new RuntimeException("Failed to send notification", ex);
                });
        } catch (Exception e) {
            log.error("Error preparing player notification {}: {}", notification.message(), e.getMessage(), e);
            return CompletableFuture.failedFuture(e);
        }
    }
} 