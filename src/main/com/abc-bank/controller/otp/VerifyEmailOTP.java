package controller.otp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.validation.EmailValidation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

public class VerifyEmailOTP extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //System.out.println("VerifyEmailOTP from controller");

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Max-Age", "3600");

        try {
            JsonObject jsonObject = JsonParser.parseReader(request.getReader()).getAsJsonObject();
            String email = jsonObject.get("email").getAsString();
            int otp = jsonObject.get("otp").getAsInt();
            if (dao.otp.EmailOTP.verifyOTP(email, otp)) {
                dao.otp.EmailOTP.updateDB(email, otp, new Timestamp(new java.util.Date().getTime()));
                dao.otp.EmailOTP.deleteOTP(email);
                response.setStatus(200);
                response.setContentType("application/json");
                JsonObject json = new JsonObject();
                json.addProperty("status", "200");
                json.addProperty("message", "Successfully verified OTP for " + email);
                response.getWriter().println(json);
            } else {
                response.setStatus(400);
                response.setContentType("application/json");
                JsonObject json = new JsonObject();
                json.addProperty("status", "400");
                json.addProperty("message", "Invalid OTP for " + email);
                response.getWriter().println(json);
            }
        } catch (Exception e) {
            System.out.println(e);
            response.setStatus(500);
            response.setContentType("application/json");
            JsonObject json = new JsonObject();
            json.addProperty("status", "500");
            json.addProperty("message", "Internals Server Error");
            response.getWriter().println(json);
        }
    }
}
