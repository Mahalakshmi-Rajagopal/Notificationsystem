package com.notification.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.builder.NotificationDetails;
import com.notification.factory.EmailNotificationFactory;
import com.notification.factory.NotificationFactory;
import com.notification.factory.SlackNotificationFactory;
import com.notification.processor.QueueProcessor;
import com.notification.service.INotification;
import com.notification.wrapper.NotificationDetailsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * REST controller for the notification system
 * @author Mahalakshmi-Rajagopal
 */
@RestController("/")
public class NotificationController {
    @Autowired
    private QueueProcessor queueProcessor;

    /**
     * Rest service method for email notification
     * @param userInput
     * @return
     * @throws Exception
     */
    @PostMapping(path="/notification/email")
    public List<String> emailNotification(@RequestBody String userInput) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<NotificationDetailsWrapper> jsonArray = Arrays.asList(objectMapper.readValue(userInput,NotificationDetailsWrapper[].class));
        List<String> responseMessages = new ArrayList<>();
        for(NotificationDetailsWrapper notificationDetailsWrapper: jsonArray){
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("from", notificationDetailsWrapper.getFrom());
            jsonMap.put("to", notificationDetailsWrapper.getTo());
            jsonMap.put("cc", notificationDetailsWrapper.getCc());
            jsonMap.put("subject", notificationDetailsWrapper.getSubject());
            jsonMap.put("message", notificationDetailsWrapper.getMessage());


        NotificationFactory emailNotificationFactory = new EmailNotificationFactory();
        NotificationDetails notificationDetails = new NotificationDetails.
                NotificationDetailsBuilder( notificationDetailsWrapper.getFrom(), notificationDetailsWrapper.getTo(), notificationDetailsWrapper.getMessage()).subject(notificationDetailsWrapper.getSubject()).
                cc(notificationDetailsWrapper.getCc()).bcc(notificationDetailsWrapper.getBcc()).build();
        INotification emaiINotification =emailNotificationFactory.createNotification(notificationDetails);
        if(!emaiINotification.validate(jsonMap))
            continue;
        try{
            queueProcessor.addQueueProcessor(emaiINotification);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        responseMessages.add("Email successfully sent to " + notificationDetailsWrapper.getTo());
        }
        return responseMessages ;
    }
    /**
     * Rest service method for email notification
     * @param userInput
     * @return
     * @throws Exception
     */
    @PostMapping(path="/notification/slack")
    public List<String> slackNotification(@RequestBody String userInput) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<NotificationDetailsWrapper> jsonArray = Arrays.asList(objectMapper.readValue(userInput,NotificationDetailsWrapper[].class));
        List<String> responseMessages = new ArrayList<>();
        for(NotificationDetailsWrapper notificationDetailsWrapper: jsonArray) {
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("from", notificationDetailsWrapper.getFrom());
            jsonMap.put("to", notificationDetailsWrapper.getTo());
            jsonMap.put("subject", notificationDetailsWrapper.getSubject());
            jsonMap.put("message", notificationDetailsWrapper.getMessage());

            NotificationFactory slackNotificationFactory = new SlackNotificationFactory();
            NotificationDetails notificationDetails = new NotificationDetails.
                    NotificationDetailsBuilder(notificationDetailsWrapper.getFrom(), notificationDetailsWrapper.getTo(), notificationDetailsWrapper.getMessage())
                    .subject(notificationDetailsWrapper.getSubject()).build();
            INotification slackNotification = slackNotificationFactory.createNotification(notificationDetails);
            if (!slackNotification.validate(jsonMap))
                continue;
            try {
                queueProcessor.addQueueProcessor(slackNotification);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            responseMessages.add("Slack message successfully sent to " + notificationDetailsWrapper.getTo());
        }
        return responseMessages;
    }

}
