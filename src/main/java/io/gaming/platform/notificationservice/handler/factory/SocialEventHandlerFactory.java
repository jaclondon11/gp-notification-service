package io.gaming.platform.notificationservice.handler.factory;

import org.springframework.stereotype.Component;

import io.gaming.platform.notificationservice.handler.EventHandler;
import io.gaming.platform.notificationservice.handler.SocialEventHandler;
import io.gaming.platform.notificationservice.model.SocialEventType;

@Component
public class SocialEventHandlerFactory implements EventHandlerFactory<SocialEventType> {
    private final SocialEventHandler handler;

    public SocialEventHandlerFactory(SocialEventHandler handler) {
        this.handler = handler;
    }

    @Override
    public EventHandler<SocialEventType> createHandler(String eventType) {
        try {
            SocialEventType type = SocialEventType.valueOf(eventType);
            return handler.supportsEventType(type) ? handler : null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public boolean supportsEventType(String eventType) {
        try {
            return SocialEventType.valueOf(eventType) != null;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
} 