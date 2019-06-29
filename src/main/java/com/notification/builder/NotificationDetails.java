package com.notification.builder;

/**
 * Bean to holdall the user input for all the email/slack etc. notifications
 * Build the objects using builder patterns since all the notifications will not require the same inputfields
 * @author Mahalakshmi-Rajagopal
 */
public class NotificationDetails {
    private final String from; //required for all the notifications
    private final String to; //required for all the notifications
    private final String subject; //not required for all the notifications so keeping this field as an optional
    private final String message; //required for all the notifications
    private final String cc; //not required for all the notifications so keeping this field as an optional

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    private final String bcc; //not required for all the notifications so keeping this field as an optional

    private NotificationDetails(NotificationDetailsBuilder builder){
        this.from = builder.from;
        this.to = builder.to;
        this.subject = builder.subject;
        this.message = builder.message;
        this.cc = builder.cc;
        this.bcc = builder.bcc;
    }

    //Builder to buld the object for all the notifications
    public static class NotificationDetailsBuilder {
        private final String from;
        private final String to;
        private String subject;
        private final String message;
        private String cc;
        private String bcc;

        public NotificationDetailsBuilder(String from, String to, String message){
            this.from = from;
            this.to = to;
            this.message = message;
        }

        public NotificationDetailsBuilder subject(String subject){
            this.subject = subject;
            return this;
        }

        public NotificationDetailsBuilder cc(String cc){
            this.cc = cc;
            return this;
        }

        public NotificationDetailsBuilder bcc(String bcc){
            this.bcc = bcc;
            return this;
        }
        public NotificationDetails build(){
            NotificationDetails notificationDetails = new NotificationDetails(this);
            return notificationDetails;
        }
    }

}
