package controller;

import model.validation.EmailValidation;
import model.validation.OTPGenerator;
import model.mailings.EmailOTP;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SendEmailOTP extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("SendEmailOTP from controller");

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Max-Age", "3600");

        try{
            JsonObject jsonObject = JsonParser.parseReader(request.getReader()).getAsJsonObject();
            String email = jsonObject.get("email").getAsString();
            if(EmailValidation.isValidEmail(email)) {
                int otp = OTPGenerator.generate();
                EmailOTP.sendEmail(email, otp);
                //System.out.println("OTP sent to " + email + " is " + otp + " from controller");
                response.setStatus(200);
                response.setContentType("application/json");
                response.getWriter().write("{\"message\": Successfully sent otp to the mail.\"}");
            }

        }catch(Exception e){
            // TODO: handle exception
            response.setStatus(400);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"Invalid email.\"}");
        }
    }
}
