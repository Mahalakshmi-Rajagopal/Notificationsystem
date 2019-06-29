package com.notification.factory;

import com.notification.builder.NotificationDetails;
import com.notification.service.INotification;

/**
 * Factory for all the notification type
 * @author Mahalakshmi-Rajagopal
 */
public abstract class NotificationFactory {
    public abstract INotification createNotification(NotificationDetails notificationDetails);
}
