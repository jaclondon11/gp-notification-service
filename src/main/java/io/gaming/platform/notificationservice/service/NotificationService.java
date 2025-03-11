package io.gaming.platform.notificationservice.service;

import io.gaming.platform.notificationservice.model.GamingPlatformEvent;

/**
 * Core service interface for handling gaming platform notifications.
 * This service is responsible for processing various gaming events and
 * delivering appropriate notifications to players based on their preferences.
 */
public interface NotificationService {

	/**
	 * Handles a gaming platform event by:
	 * 1. Checking if notifications are enabled for the player
	 * 2. Finding an appropriate handler for the event type
	 * 3. Generating a notification message
	 * 4. Delivering the notification to the player
	 *
	 * @param event The gaming platform event to process
	 */
	void handleEvent(GamingPlatformEvent event);
}