package com.notification.service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

/**
 * Interface for the notification type
 * @author Mahalakshmi-Rajagopal
 */
public interface INotification {
    void sendMessage() throws MessagingException, IOException;
    boolean validate(Map<String, Object> userInput);
}
