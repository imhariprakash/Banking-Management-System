package controller.otp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyMobileOTP extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        //System.out.println("VerifyMobileOTP from controller");

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Max-Age", "3600");

        try {
            JsonObject jsonObject = JsonParser.parseReader(request.getReader()).getAsJsonObject();
            String mobile = "";
            int otp = 0;
            try {
                mobile = jsonObject.get("mobile").getAsString();
                otp = jsonObject.get("otp").getAsInt();
            } catch (Exception e) {
                JsonObject jsonObject1 = new JsonObject();
                jsonObject1.addProperty("status", "400");
                jsonObject1.addProperty("message", "Check mobile and otp");
                response.setStatus(400);
                response.getWriter().println(jsonObject1);
            }
            if (dao.otp.MobileOTP.verifyOTP(mobile, otp)) {
                dao.otp.MobileOTP.updateDB(mobile, otp, new java.sql.Timestamp(new java.util.Date().getTime()));
                dao.otp.MobileOTP.deleteOTP(mobile);
                response.setStatus(200);
                response.setContentType("application/json");
                com.google.gson.JsonObject json = new com.google.gson.JsonObject();
                json.addProperty("status", "200");
                json.addProperty("message", "Successfully verified OTP for " + mobile);
                response.getWriter().println(json);
            } else {
                response.setStatus(400);
                response.setContentType("application/json");
                com.google.gson.JsonObject json = new com.google.gson.JsonObject();
                json.addProperty("status", "400");
                json.addProperty("message", "Invalid OTP for " + mobile);
                response.getWriter().println(json);
            }
        } catch (java.lang.Exception e) {
            // TODO: handle exception
            response.setStatus(500);
            response.setContentType("application/json");
            com.google.gson.JsonObject json = new com.google.gson.JsonObject();
            json.addProperty("status", "500");
            json.addProperty("message", "Internal Server Error");
            response.getWriter().println(json);
        }
    }
}
