package com.notification.factory;

import com.notification.builder.NotificationDetails;
import com.notification.service.EmailNotification;
import com.notification.service.INotification;

/**
 * Factory to create email notification object
 * @author Mahalakshmi-Rajagopal
 */
public class EmailNotificationFactory extends NotificationFactory{

    @Override
    public INotification createNotification(NotificationDetails notificationDetails){
        return new EmailNotification(notificationDetails);
    }
}
