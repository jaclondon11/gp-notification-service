package io.gaming.platform.notificationservice.handler.factory;

import io.gaming.platform.notificationservice.handler.EventHandler;

public interface EventHandlerFactory<T extends Enum<T>> {
	EventHandler<T> createHandler(String eventType);

	boolean supportsEventType(String eventType);
}