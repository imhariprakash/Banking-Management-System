package controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.otp.MobileOTP;
import model.mailings.Email;
import model.sms.SendSMS;
import model.sms.SMS;
import model.validation.EmailValidation;
import model.validation.MobileNumberValidation;
import model.validation.OTPGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SendMobileOTP extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JsonObject jsonObject = JsonParser.parseReader(request.getReader()).getAsJsonObject();
            String mobile = jsonObject.get("phone").getAsString();
            System.out.println(mobile);
            if (MobileNumberValidation.isValidMobileNumber(mobile)) {
                int otp = OTPGenerator.generate();
                SendSMS.send(mobile, otp);
                response.setStatus(200);
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }

}