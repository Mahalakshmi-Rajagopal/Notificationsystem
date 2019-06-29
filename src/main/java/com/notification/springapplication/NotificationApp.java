package com.notification.springapplication;

import com.notification.processor.QueueProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Spring boot application for Notification system
 * @author Mahalakshmi-Rajagopal
 */
@ComponentScan("com.notification")
@SpringBootApplication
public class NotificationApp {
    @Autowired
    private QueueProcessor queueProcessor;

    public static void main(String[] args){
        SpringApplication.run(NotificationApp.class, args);
    }

    @PostConstruct
    public void init(){
        Thread consThread = new Thread(queueProcessor.getConsumer());
        consThread.start();
    }

    @PreDestroy
    public void shutDown(){
        queueProcessor.KILL = true;
    }
}
