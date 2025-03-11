package io.gaming.platform.notificationservice.handler;

import java.util.Map;

import io.gaming.platform.notificationservice.model.EventCategory;

public interface EventHandler<T extends Enum<T>> {
	String generateMessage(String eventType, Map<String, Object> eventData);

	EventCategory getCategory();

	boolean supportsEventType(T eventType);

}