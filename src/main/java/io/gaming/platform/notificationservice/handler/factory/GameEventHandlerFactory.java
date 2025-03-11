package io.gaming.platform.notificationservice.handler.factory;

import org.springframework.stereotype.Component;

import io.gaming.platform.notificationservice.handler.EventHandler;
import io.gaming.platform.notificationservice.handler.GameEventHandler;
import io.gaming.platform.notificationservice.model.GameEventType;

@Component
public class GameEventHandlerFactory implements EventHandlerFactory<GameEventType> {
    private final GameEventHandler handler;

    public GameEventHandlerFactory(GameEventHandler handler) {
        this.handler = handler;
    }

    @Override
    public EventHandler<GameEventType> createHandler(String eventType) {
        try {
            GameEventType type = GameEventType.valueOf(eventType);
            return handler.supportsEventType(type) ? handler : null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public boolean supportsEventType(String eventType) {
        try {
            return GameEventType.valueOf(eventType) != null;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

} 