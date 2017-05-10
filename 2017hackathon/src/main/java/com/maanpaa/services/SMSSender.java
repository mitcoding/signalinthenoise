package com.maanpaa.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URISyntaxException;

import org.springframework.stereotype.Service;

@Service
public class SMSSender {

    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC1c382dd12cad3f67bdd73fffd4a95355";
    public static final String AUTH_TOKEN = "83de4642678f98baed71a522969b4b78";

    public void sendMarketingFlowStart() throws URISyntaxException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+13103090654"),  // to
                         new PhoneNumber("+12138934598"),  // from
                         "Thank you for contacting Capital Group, REPLY 1 for wholesaler to contact you, REPLY 2 to request literature")
                .create();
    }
    public void sendSalesMeetingStart(String intiationMessage, String phoneNumber) throws URISyntaxException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber(phoneNumber),  // to
                         new PhoneNumber("+12138934598"),  // from
                         intiationMessage)
                .create();
    }
}