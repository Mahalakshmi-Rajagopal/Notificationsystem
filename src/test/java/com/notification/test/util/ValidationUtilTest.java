package com.notification.test.util;

import com.notification.builder.NotificationDetails;
import com.notification.service.EmailNotification;
import com.notification.util.ValidationUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ValidationUtilTest {
    private ValidationUtil validationUtil;

    private ValidationUtil getValidationUtil(){
        return new ValidationUtil();
    }

    @Test
    public void testValidateUserInput1(){
        NotificationDetails notificationDetails = new NotificationDetails.
                NotificationDetailsBuilder(" ","test@testserver.com", "test message").subject(("test")).
                cc("testmaster@testserver.com").build();
        EmailNotification emailNotification = new EmailNotification(notificationDetails);

        Map<String, Object> userInput = new HashMap<>();
        userInput.put("from", " ");
        userInput.put("to", "test@testserver.com");
        userInput.put("subject", "test");
        userInput.put("message", "test message");

        assertFalse("Userinput is not valid", emailNotification.validate(userInput));
    }

    @Test
    public void testValidateUserInput2(){
        NotificationDetails notificationDetails = new NotificationDetails.
                NotificationDetailsBuilder(" ","test@testserver.com", "test message").subject(("test")).
                cc("testmaster@testserver.com").build();
        EmailNotification emailNotification = new EmailNotification(notificationDetails);

        Map<String, Object> userInput = new HashMap<>();
        userInput.put("from", "test@testserver.com");
        userInput.put("to", " ");
        userInput.put("subject", "test");
        userInput.put("message", "test message");

        assertFalse("Userinput is not valid", emailNotification.validate(userInput));
    }

    @Test
    public void testValidateUserInput3(){
        NotificationDetails notificationDetails = new NotificationDetails.
                NotificationDetailsBuilder(" ","test@testserver.com", "test message").subject(("test")).
                cc("testmaster@testserver.com").build();
        EmailNotification emailNotification = new EmailNotification(notificationDetails);

        Map<String, Object> userInput = new HashMap<>();
        userInput.put("from", "master@testserver.com");
        userInput.put("to", "student@testserver.com");
        userInput.put("subject", "test");
        userInput.put("message", "test message");

        assertTrue("Userinput is not valid", emailNotification.validate(userInput));
    }

    @Test
    public void testEmailAddress1(){
        assertTrue("Input email address is not valid",
                getValidationUtil().validateAddressFields("billgates@microsofttest.com"));
    }

    @Test
    public void testEmailAddress2(){
        assertTrue("Input email address is not valid",
                getValidationUtil().validateAddressFields("billgates@nation.wide.com"));
    }
    @Test
    public void testEmailAddress3(){
        assertFalse("Input email address is not valid",
                getValidationUtil().validateAddressFields("'billgates@testserver.com"));
    }

    @Test
    public void testEmailAddress4(){
        assertTrue("Input email address is not valid",
                getValidationUtil().validateAddressFields("123@testserver.com"));
    }

    @Test
    public void testEmailAddress5(){
        assertFalse("Input email address is not valid",
                getValidationUtil().validateAddressFields("billgates@testserver.123"));
    }

    @Test
    public void testEmailAddress6(){
        assertTrue("Input email address is not valid",
                getValidationUtil().validateAddressFields("abc.def@testserver.com"));
    }

    @Test
    public void testEmailAddress7(){
        assertFalse("Input email address is not valid",
                getValidationUtil().validateAddressFields("abc.\\\\def@testserver.com"));
    }

    @Test
    public void testEmailAddress8(){
        assertTrue("Input email address is not valid",
                getValidationUtil().validateAddressFields("abc_def@testserver.com"));
    }

    @Test
    public void testEmailAddress9(){
        assertTrue("Input email address is not valid",
                getValidationUtil().validateAddressFields("abc_def.@testserver.com"));
    }

    @Test
    public void testEmailAddress10(){
        assertTrue("Input email address is not valid",
                getValidationUtil().validateAddressFields("abc_def-@testserver.com"));
    }

    @Test
    public void testEmailAddress11(){
        assertFalse("Input email address is not valid",
                getValidationUtil().validateAddressFields("^billgates@testserver.com"));
    }

    @Test
    public void testEmailAddress12(){
        assertTrue("Input email address is not valid",
                getValidationUtil().validateAddressFields("billgates@testserver.com;noreply@testserver.com"));
    }

    @Test
    public void testEmailAddress13(){
        assertTrue("Input email address is not valid",
                getValidationUtil().validateAddressFields("billgates@testserver.com,noreply@testserver.com"));
    }

    @Test
    public void testEmailAddress14(){
        assertFalse("Input email address is not valid",
                getValidationUtil().validateAddressFields("billgates@testserver.com;;noreply@testserver.com"));
    }

    @Test
    public void testEmailAddress15(){
        assertFalse("Input email address is not valid",
                getValidationUtil().validateAddressFields("billgates@testserver.com;,noreply@testserver.com"));
    }

    @Test
    public void testEmailAddress16(){
        assertTrue("Input email address is not valid",
                getValidationUtil().validateAddressFields("billgates@testserver.com; noreply@testserver.com"));
    }

    @Test
    public void testEmailAddress17(){
        assertTrue("Input email address is not valid",
                getValidationUtil().validateAddressFields("billgates@testserver.com ; noreply@testserver.com"));
    }

    @Test
    public void testEmailAddress18(){
        assertTrue("Input email address is not valid",
                getValidationUtil().validateAddressFields("billgates@testserver.com , noreply@testserver.com"));
    }

    @Test
    public void testEmailAddress19(){
        assertFalse("Input email address is not valid",
                getValidationUtil().validateAddressFields("billgates@testserver.com,,noreply@testserver.com"));
    }

    @Test
    public void testEmailAddress20(){
        assertFalse("Input email address is not valid",
                getValidationUtil().validateAddressFields("billgates@testserver.com  noreply@testserver.com"));
    }

    @Test
    public void testEmailAddress21(){
        assertFalse("Input email address is not valid",
                getValidationUtil().validateAddressFields("billgates@sample.verybad@sample.com"));
    }





}
