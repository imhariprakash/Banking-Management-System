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
            JsonObject responseJson = new JsonObject();
            if(dao.otp.ForgotPassword.verifyOTP(customer_id, otp, responseJson)){
                response.setStatus(200);
                response.getWriter().println(responseJson);
            }else{
                response.setStatus(400);
                response.getWriter().println(responseJson);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
