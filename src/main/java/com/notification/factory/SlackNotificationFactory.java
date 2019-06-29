package com.notification.factory;

import com.notification.service.INotification;
import com.notification.service.SlackNotification;
import com.notification.builder.*;

/**
 * Factory to create slack notification object
 * @author Mahalakshmi-Rajagopal
 */
public class SlackNotificationFactory extends NotificationFactory{

    @Override
    public INotification createNotification(NotificationDetails notificationDetails) {
        return new SlackNotification(notificationDetails);
    }
}
