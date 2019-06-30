package com.notification.test.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

/**
 * Test the email and slack message notification functionality
 * @author Mahalakshmi-Rajagopal
 */
public class NotificationTest extends AbstractTest {

    @Override
    @Before
    public void setup() {
        super.setup();
    }

    @Test
    public void sendEmailNotification() throws Exception {
        String uri ="/notification/email";
        JSONObject json1 =new JSONObject();
        json1.put("from","master1@sample.com");
        json1.put("to","student1@sample.com");
        json1.put("cc","principal@sample.com");
        json1.put("subject","test");
        json1.put("message","test message");

        JSONObject json2 =new JSONObject();
        json2.put("from","master1@sample.com");
        json2.put("to","student2@sample.com");
        json2.put("cc","principal@sample.com");
        json2.put("subject","test");
        json2.put("message","test message");

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(json1);
        jsonArray.put(json2);

        String inputJson = jsonArray.toString();
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        StringBuilder builder = new StringBuilder();
        builder.append("[").append("\"Email successfully sent to student1@sample.com\"").
                append(",").append("\"Email successfully sent to student2@sample.com\"").append("]");
        assertEquals(content, builder.toString());

    }

    @Test
    public void sendSlackNotification() throws Exception {
        String uri ="/notification/slack";
        JSONObject json1 =new JSONObject();
        json1.put("from","master1@sample.com");
        json1.put("to","student1@sample.com");
        json1.put("cc","principal@sample.com");
        json1.put("subject","test");
        json1.put("message","test message");

        JSONObject json2 =new JSONObject();
        json2.put("from","master1@sample.com");
        json2.put("to","student2@sample.com");
        json2.put("cc","principal@sample.com");
        json2.put("subject","test");
        json2.put("message","test message");

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(json1);
        jsonArray.put(json2);

        String inputJson = jsonArray.toString();
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        StringBuilder builder = new StringBuilder();
        builder.append("[").append("\"Slack message successfully sent to student1@sample.com\"").
                append(",").append("\"Slack message successfully sent to student2@sample.com\"").append("]");
        assertEquals(content, builder.toString());

    }
}
