package com.notification.stub;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * Created this stub to mock the slack notification functionality
 * @author Mahalakshmi-Rajagopal
 */
public class SlackStub {
    private String from;
    private String to;
    private String subject;
    private String message;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void sendSlackMessage() throws MessagingException, IOException {
        System.out.println("Slack sent from " + this.getFrom() + " to " + this.getTo());
    }



}
