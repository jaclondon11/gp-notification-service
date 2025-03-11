package io.gaming.platform.notificationservice.service;

import io.gaming.platform.notificationservice.model.EventCategory;
import io.gaming.platform.notificationservice.model.NotificationPreference;

/**
 * Service interface for managing player notification preferences.
 * This service handles the storage and retrieval of player preferences
 * for different types of notifications in the gaming platform.
 */
public interface NotificationPreferenceService {
	
	/**
	 * Retrieves the notification preferences for a specific player.
	 * If no preferences exist for the player, returns default preferences.
	 *
	 * @param playerId The unique identifier of the player
	 * @return The player's notification preferences
	 */
	NotificationPreference getPreference(String playerId);

	/**
	 * Updates a player's notification preference for a specific event category.
	 *
	 * @param playerId The unique identifier of the player
	 * @param category The event category to update preferences for
	 * @param enabled Whether notifications should be enabled for this category
	 */
	void updatePreference(String playerId, EventCategory category, boolean enabled);

	/**
	 * Checks if notifications are enabled for a specific player and event category.
	 *
	 * @param playerId The unique identifier of the player
	 * @param category The event category to check
	 * @return true if notifications are enabled, false otherwise
	 */
	boolean isNotificationEnabled(String playerId, EventCategory category);
}