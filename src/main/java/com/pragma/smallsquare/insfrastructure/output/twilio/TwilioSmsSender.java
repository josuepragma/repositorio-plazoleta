package com.pragma.smallsquare.insfrastructure.output.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TwilioSmsSender {

    @Value("${twilio.account-sid}")
    public static final String ACCOUNT_SID = "";
    @Value("${twilio.auth-token}")
    public static String AUTH_TOKEN;
    @Value("${twilio.phone-number}")
    public static String PHONE_NUMBER;

    public void sendSms(String toPhoneNumber, String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message.creator(
                new PhoneNumber(toPhoneNumber),
                new PhoneNumber(PHONE_NUMBER),
                message
        ).create();
    }
}
