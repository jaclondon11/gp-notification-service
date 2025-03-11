package io.gaming.platform.notificationservice.service;

import io.gaming.platform.notificationservice.model.EventCategory;
import io.gaming.platform.notificationservice.model.NotificationPreference;

public interface NotificationPreferenceService {
	
	/**
	 * 
	 * @param playerId
	 * @return
	 */
	NotificationPreference getPreference(String playerId);

	/**
	 * 
	 * @param playerId
	 * @param category
	 * @param enabled
	 */
	void updatePreference(String playerId, EventCategory category, boolean enabled);

	/**
	 * 
	 * @param playerId
	 * @param category
	 * @return
	 */
	boolean isNotificationEnabled(String playerId, EventCategory category);
}