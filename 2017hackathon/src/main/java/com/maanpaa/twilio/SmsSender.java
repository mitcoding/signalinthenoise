package com.maanpaa.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URISyntaxException;

public class SmsSender {

    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC1c382dd12cad3f67bdd73fffd4a95355";
    public static final String AUTH_TOKEN = "83de4642678f98baed71a522969b4b78";

    public static void main(String[] args) throws URISyntaxException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+13103090654"),  // to
                         new PhoneNumber("+12138934598"),  // from
                         "Thank you for contacting Capital Group, REPLY 1 for wholesaler to contact you, REPLY 2 to request literature")
                .create();
    }
}