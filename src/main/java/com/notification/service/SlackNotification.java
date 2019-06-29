package com.notification.service;

import com.notification.builder.*;
import com.notification.stub.*;
import com.notification.util.*;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

/**
 * Validate the email notification details and send the email notification
 * @author Mahalakshmi-Rajagopal
 */
public class SlackNotification implements INotification {

    public NotificationDetails notificationDetails;

    public NotificationDetails getNotificationDetails() {
        return notificationDetails;
    }

    public void setNotificationDetails(NotificationDetails notificationDetails) {
        this.notificationDetails = notificationDetails;
    }

    public SlackNotification(NotificationDetails notificationDetails){
        this.notificationDetails = notificationDetails;
    }

    @Override
    public void sendMessage() throws MessagingException, IOException {
        SlackStub slackStub = new SlackStub();
        slackStub.setFrom(this.getNotificationDetails().getFrom());
        slackStub.setTo(this.getNotificationDetails().getTo());
        slackStub.setSubject(this.getNotificationDetails().getSubject());
        slackStub.setMessage(this.getNotificationDetails().getMessage());
        slackStub.sendSlackMessage();
    }

    @Override
    public boolean validate(Map<String, Object> userInput) {
        ValidationUtil validationUtil = new ValidationUtil();
        return validationUtil.validateUserInput(userInput);
    }
}
