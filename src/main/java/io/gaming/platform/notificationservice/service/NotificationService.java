package io.gaming.platform.notificationservice.service;

import io.gaming.platform.notificationservice.model.GamingPlatformEvent;

public interface NotificationService {


	void handleEvent(GamingPlatformEvent event);
}