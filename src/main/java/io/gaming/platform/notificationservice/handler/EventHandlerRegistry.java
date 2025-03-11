package io.gaming.platform.notificationservice.handler;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import io.gaming.platform.notificationservice.model.EventCategory;
import io.gaming.platform.notificationservice.handler.factory.EventHandlerFactory;
import io.gaming.platform.notificationservice.handler.factory.GameEventHandlerFactory;
import io.gaming.platform.notificationservice.handler.factory.SocialEventHandlerFactory;

@Component
public class EventHandlerRegistry {
    private final Map<EventCategory, EventHandlerFactory<?>> factories;

    public EventHandlerRegistry(GameEventHandlerFactory gameFactory, SocialEventHandlerFactory socialFactory) {
        this.factories = Map.of(
            EventCategory.GAME, gameFactory,
            EventCategory.SOCIAL, socialFactory
        );
    }

    @SuppressWarnings("unchecked")
    public <T extends Enum<T>> Optional<EventHandler<T>> getHandler(EventCategory category, String eventType) {
        EventHandlerFactory<?> factory = factories.get(category);
        if (factory == null) {
            return Optional.empty();
        }
        return Optional.ofNullable((EventHandler<T>) factory.createHandler(eventType));
    }
} 