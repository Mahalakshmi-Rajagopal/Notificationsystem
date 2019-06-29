package com.notification.test.controller;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

public class NotificationTest extends AbstractTest {

    @Override
    @Before
    public void setup() {
        super.setup();
    }

    @Test
    public void sendEmailNotification() throws Exception {
        String uri ="/notification/email";
        JSONObject json =new JSONObject();
        json.put("from","master@sample.com");
        json.put("to","student@sample.com");
        json.put("cc","principal@sample.com");
        json.put("subject","test");
        json.put("message","test message");

        String inputJson = json.toString();
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Email sent successfully to student@sample.com");

    }

    @Test
    public void sendSlackNotification() throws Exception {
        String uri ="/notification/slack";
        JSONObject json =new JSONObject();
        json.put("from","master@sample.com");
        json.put("to","student@sample.com");
        json.put("subject","test");
        json.put("message","test message");

        String inputJson = json.toString();
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Slack message sent to student@sample.com");

    }
}
