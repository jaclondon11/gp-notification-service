package io.gaming.platform.notificationservice.producer;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.support.SendResult;

import io.gaming.platform.notificationservice.model.UserNotification;

public interface NotificationProducer {
	
	/**
	 * 
	 * @param notification
	 * @return 
	 */
    CompletableFuture<SendResult<String, String>> sendNotification(UserNotification notification);
} 