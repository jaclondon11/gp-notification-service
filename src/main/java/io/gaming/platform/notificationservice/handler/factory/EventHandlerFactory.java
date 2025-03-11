package io.gaming.platform.notificationservice.handler.factory;

import io.gaming.platform.notificationservice.handler.EventHandler;

/**
 * Factory interface for creating event handlers based on event types.
 * This factory pattern allows for dynamic creation of appropriate handlers
 * for different types of events in the gaming platform.
 *
 * @param <T> The enum type representing the specific category of events
 */
public interface EventHandlerFactory<T extends Enum<T>> {
	
	/**
	 * Creates an appropriate event handler for the given event type.
	 *
	 * @param eventType The type of event as a string
	 * @return An event handler that can process the given event type,
	 *         or null if no suitable handler is available
	 */
	EventHandler<T> createHandler(String eventType);

	/**
	 * Checks if this factory can create a handler for the given event type.
	 *
	 * @param eventType The type of event as a string
	 * @return true if this factory supports the event type, false otherwise
	 */
	boolean supportsEventType(String eventType);
}