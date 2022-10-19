package controller.otp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.sms.SendSMS;
import model.validation.MobileNumberValidation;
import model.validation.OTPGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SendMobileOTP extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Max-Age", "3600");
        try {
            JsonObject jsonObject = JsonParser.parseReader(request.getReader()).getAsJsonObject();
            String mobile;
            try {
                mobile = jsonObject.get("phone").getAsString();
            }catch (Exception e) {
                JsonObject jsonObject1 = new JsonObject();
                jsonObject1.addProperty("status", "400");
                jsonObject1.addProperty("message", "Invalid mobile number");
                response.setStatus(400);
                response.getWriter().println(jsonObject1);
                return;
            }
            if(!MobileNumberValidation.isValidMobileNumber(mobile)){
                JsonObject responseJsonObject = new JsonObject();
                responseJsonObject.addProperty("status", "400");
                responseJsonObject.addProperty("message", "Invalid Mobile Number");
                response.getWriter().write(responseJsonObject.toString());
                return;
            }
            if (MobileNumberValidation.isValidMobileNumber(mobile)) {
                int otp;
                while(true){
                    otp = OTPGenerator.generate();
                    if(otp > 99999 && otp < 1000000){
                        break;
                    }
                }
                SendSMS.send(mobile, otp);
                response.setStatus(200);
                JsonObject jsonObject1 = new JsonObject();
                jsonObject1.addProperty("status", "200");
                jsonObject1.addProperty("message", "OTP sent successfully");
                response.getWriter().println(jsonObject1);
            }else{
                response.setStatus(400);
                JsonObject jsonObject1 = new JsonObject();
                jsonObject1.addProperty("status", "400");
                jsonObject1.addProperty("message", "Invalid mobile number");
                response.getWriter().println(jsonObject1);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println(e);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("status", "500");
            jsonObject.addProperty("message", "Internal Server Error");
            response.getWriter().println(jsonObject);
        }
    }

}