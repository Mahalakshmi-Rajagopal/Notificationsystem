package com.notification.util;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * Validate the user input to the notification system
 * @author Mahalakshmi-Rajagopal
 */
public class ValidationUtil {

    public static String regex =
            "(([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4}))(((;|,|; | ;| ; | , | ,){1}"
                    +"([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4}))*)";

    public boolean validateAddressFields(String addressField){
        boolean validated = true;
        if(addressField.equals("") || addressField.equals(" ")){
            validated = false;
            return validated;
        }else if(!Pattern.matches(regex, addressField)){
            validated = false;
            return validated;
        }
        return validated;
    }

    public boolean validateUserInput(Map<String, Object> userInput){

        boolean validated = true;
        if(userInput.containsKey("from") && null != userInput.get("from")){
            validated = validateAddressFields((String)userInput.get("from"));
            if(!validated) {
                return validated;
            }
        }
        if(userInput.containsKey("to") && null != userInput.get("to")){
            validated = validateAddressFields((String)userInput.get("to"));
            if(!validated) {
                return validated;
            }
        }
        return validated;
    }
}
