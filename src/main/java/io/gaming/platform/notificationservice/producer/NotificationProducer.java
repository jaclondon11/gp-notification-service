package io.gaming.platform.notificationservice.producer;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.support.SendResult;

import io.gaming.platform.notificationservice.model.UserNotification;

/**
 * Interface for producing notifications to a message broker.
 * This component is responsible for sending notifications to a Kafka topic
 * where they can be consumed by other services or external notification systems.
 */
public interface NotificationProducer {
	
	/**
	 * Sends a notification message to the configured Kafka topic.
	 * The notification is sent asynchronously and the result can be handled
	 * using the returned CompletableFuture.
	 *
	 * @param notification The notification to send
	 * @return A CompletableFuture that completes when the message is sent successfully
	 *         or completes exceptionally if the send operation fails
	 */
    CompletableFuture<SendResult<String, String>> sendNotification(UserNotification notification);
} 