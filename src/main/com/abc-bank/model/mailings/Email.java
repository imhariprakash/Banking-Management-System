package model.mailings;

import services.Courier;
import services.SendService;
import models.SendEnhancedRequestBody;
import models.SendEnhancedResponseBody;
import models.SendRequestMessage;
import models.SendRequestMessageRouting;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.*;

public class Email {

    private Email() {
    } // Prevents instantiation


    public static void send(String email, String subject, String body) {
        //System.out.println("Email sent to " + email + " from model - Email.java");
        Courier.init("pk_prod_21F5MEP3Z4MVC0JQKJ7PGYZQCJTH");

        SendEnhancedRequestBody sendEnhancedRequestBody = new SendEnhancedRequestBody();
        SendRequestMessage sendRequestMessage = new SendRequestMessage();
        HashMap<String, String> to = new HashMap<String, String>();
        to.put("email", email);
        sendRequestMessage.setTo(to);

        HashMap<String, String> content = new HashMap<String, String>();
        content.put("title", subject);
        content.put("body", body);
        sendRequestMessage.setContent(content);

        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("otp", body);
        sendRequestMessage.setData(data);
        sendEnhancedRequestBody.setMessage(sendRequestMessage);

        try {
            SendEnhancedResponseBody response = new SendService().sendEnhancedMessage(sendEnhancedRequestBody);
            //System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void forgotPassword(int otp, String email) {
        String subject = "Hello from ABC Bank! Here is your forgot password OTP";
        String body = "Your OTP is " + otp;
        send(email, subject, body);
    }

    public static void passwordChanged(String email) {
        String subject = "Hello from ABC Bank! Your password has been changed";
        String body = "Your password has been changed";
        send(email, subject, body);
    }

}
