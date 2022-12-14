package model.sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;


public class SMS {
    private SMS() {
    } // Prevents instantiation

    public static void send(String phone_number, String subject, String body){
        String ACCOUNT_SID = dao.sms.SMS.getTwilioAccountSID();
        String AUTH_TOKEN = dao.sms.SMS.getTwilioAuthToken();
        String messagingServiceSid = "MG2a10097e870935d347b09c78c40c2a30";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("+91" + phone_number),
                        messagingServiceSid,
                        body)
                .create();

        //System.out.println(message.getSid());
    }
}
