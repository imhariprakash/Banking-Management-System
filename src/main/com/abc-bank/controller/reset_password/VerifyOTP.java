package controller.reset_password;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyOTP extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            JsonObject requestJson = JsonParser.parseReader(request.getReader()).getAsJsonObject();
            String customer_id = requestJson.get("customer_id").getAsString();
            int otp = requestJson.get("otp").getAsInt();
            if(dao.otp.ForgotPassword.verifyOTP(customer_id, otp)){
                response.setStatus(200);
                JsonObject responseJson = new JsonObject();
                responseJson.addProperty("status", "200");
                responseJson.addProperty("message", "OTP verified");
                response.getWriter().println(responseJson);
            }else{
                response.setStatus(400);
                JsonObject responseJson = new JsonObject();
                responseJson.addProperty("status", "400");
                responseJson.addProperty("message", "Invalid OTP");
                response.getWriter().println(responseJson);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
