package com.notification.wrapper;

/**
 * Wrapper to class to hold the user input values
 * @author Mahalakshmi-Rajagopal
 */
public class NotificationDetailsWrapper {
    private String from;
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String message;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getCc() {
        return cc;
    }

    public String getBcc() {
        return bcc;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }


}
