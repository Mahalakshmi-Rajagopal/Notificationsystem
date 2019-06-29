package com.notification.service;

import com.notification.builder.NotificationDetails;
import com.notification.stub.EmailStub;
import com.notification.util.ValidationUtil;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

/**
 * Validate the email notification details and send the email notification
 * @author Mahalakshmi-Rajagopal
 */
public class EmailNotification implements INotification {

    public NotificationDetails notificationDetails;

    public NotificationDetails getNotificationDetails() {
        return notificationDetails;
    }

    public void setNotificationDetails(NotificationDetails notificationDetails) {
        this.notificationDetails = notificationDetails;
    }

    public EmailNotification(NotificationDetails notificationDetails){
        this.notificationDetails = notificationDetails;
    }


    @Override
    public void sendMessage() throws MessagingException, IOException {
        EmailStub emailStub = new EmailStub();
        emailStub.setFrom(this.getNotificationDetails().getFrom());
        emailStub.setTo(this.getNotificationDetails().getTo());
        emailStub.setSubject(this.getNotificationDetails().getSubject());
        emailStub.setMessage(this.getNotificationDetails().getMessage());
        emailStub.sendEmail();
    }

    @Override
    public boolean validate(Map<String, Object> userInput) {
        ValidationUtil validationUtil = new ValidationUtil();
        return validationUtil.validateUserInput(userInput);
    }
}
