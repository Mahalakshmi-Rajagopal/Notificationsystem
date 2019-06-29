package com.notification.stub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * Created this stub to mock the email notification functionality
 * @author Mahalakshmi-Rajagopal
 */
public class EmailStub {

    @Autowired
    private JavaMailSender sender;
    private String from;
    private String to;
    private String cc;
    private String bcc;
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

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
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

    public void sendEmail() throws MessagingException, IOException {
        System.out.println("Email sent from " + this.getFrom() + " to " + this.getTo());
    }



}
