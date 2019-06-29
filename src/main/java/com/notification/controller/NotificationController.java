package com.notification.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.builder.NotificationDetails;
import com.notification.factory.EmailNotificationFactory;
import com.notification.factory.NotificationFactory;
import com.notification.factory.SlackNotificationFactory;
import com.notification.processor.QueueProcessor;
import com.notification.service.INotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
    public String emailNotification(@RequestBody String userInput) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = objectMapper.readValue(userInput,Map.class);

        NotificationFactory emailNotificationFactory = new EmailNotificationFactory();
        NotificationDetails notificationDetails = new NotificationDetails.
                NotificationDetailsBuilder((String)jsonMap.get("from"),(String)jsonMap.get("to"), (String)jsonMap.get("message")).subject((String)jsonMap.get("subject")).
                cc((String)jsonMap.get("cc")).bcc((String)jsonMap.get("bcc")).build();
        INotification emaiINotification =emailNotificationFactory.createNotification(notificationDetails);
        if(!emaiINotification.validate(jsonMap))
            return "Some of the input is not correct";
        try{
            queueProcessor.addQueueProcessor(emaiINotification);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        return "Email sent successfully to " + (String)jsonMap.get("to") ;
    }
    /**
     * Rest service method for email notification
     * @param userInput
     * @return
     * @throws Exception
     */
    @PostMapping(path="/notification/slack")
    public String slackNotification(@RequestBody String userInput) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = objectMapper.readValue(userInput, Map.class);

        NotificationFactory slackNotificationFactory = new SlackNotificationFactory();
        NotificationDetails notificationDetails = new NotificationDetails.
                NotificationDetailsBuilder((String)jsonMap.get("from"),(String)jsonMap.get("to"), (String)jsonMap.get("message"))
                .subject((String)jsonMap.get("subject")).build();
        INotification slackNotification =slackNotificationFactory.createNotification(notificationDetails);
        if(!slackNotification.validate(jsonMap))
            return "Some of the input is not correct";
        try{
            queueProcessor.addQueueProcessor(slackNotification);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        return "Slack message sent to " + (String)jsonMap.get("to");
    }

}
