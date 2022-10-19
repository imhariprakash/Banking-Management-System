package controller.reset_password;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyForgotPassword extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Max-Age", "3600");

        try{
            JsonObject requestJson = JsonParser.parseReader(request.getReader()).getAsJsonObject();
            JsonObject responseJson = new JsonObject();
            String customer_id = requestJson.get("customer_id").getAsString();
            int otp = requestJson.get("otp").getAsInt();
            if(dao.otp.ForgotPassword.verifyOTP(customer_id, otp, responseJson)){
                responseJson.addProperty("status", "200");
                responseJson.addProperty("message", "OTP verified");
                response.setStatus(200);
                response.setContentType("application/json");
                response.getWriter().println(responseJson);
            }else{
                responseJson.addProperty("status", "400");
                responseJson.addProperty("message", "Invalid OTP");
                response.setStatus(400);
                response.setContentType("application/json");
                response.getWriter().println(responseJson);
            }
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(400);
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("status", "400");
            responseJson.addProperty("message", "Invalid Request");
        }
    }
}
