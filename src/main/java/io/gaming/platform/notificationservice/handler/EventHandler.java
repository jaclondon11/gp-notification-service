package io.gaming.platform.notificationservice.handler;

import java.util.Map;

import io.gaming.platform.notificationservice.model.EventCategory;

/**
 * Generic interface for handling different types of gaming platform events.
 * Implementations of this interface are responsible for generating appropriate notification
 * messages based on specific event types and their associated data.
 *
 * @param <T> The enum type representing the specific category of events (e.g., GameEventType, SocialEventType)
 */
public interface EventHandler<T extends Enum<T>> {
	
	/**
	 * Generates a notification message based on the event type and its associated data.
	 *
	 * @param eventType The type of event as a string (must be convertible to type T)
	 * @param eventData A map containing event-specific data required for message generation
	 * @return A formatted message string suitable for notification
	 * @throws IllegalArgumentException if the eventType is not valid for this handler
	 */
	String generateMessage(String eventType, Map<String, Object> eventData);

	/**
	 * Returns the category of events this handler is responsible for.
	 *
	 * @return The EventCategory this handler manages
	 */
	EventCategory getCategory();

	/**
	 * Checks if this handler supports the given event type.
	 *
	 * @param eventType The event type to check
	 * @return true if this handler can process the given event type, false otherwise
	 */
	boolean supportsEventType(T eventType);
}