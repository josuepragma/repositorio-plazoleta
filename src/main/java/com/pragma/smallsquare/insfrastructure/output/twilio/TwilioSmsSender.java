package com.pragma.smallsquare.insfrastructure.output.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Component;

@Component
public class TwilioSmsSender {

    public static final String ACCOUNT_SID = "AC3047a197f006fc8c80c61b4fb6493ed3";
    public static final String AUTH_TOKEN = "5837bb5a9a6c04d74df2dd639719c8a3";
    public static final String PHONE_NUMBER = "+14178043060";

    public void sendSms(String toPhoneNumber, String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message.creator(
                new PhoneNumber(toPhoneNumber),
                new PhoneNumber(PHONE_NUMBER),
                message
        ).create();
    }
}
