package controller.otp;

import com.google.gson.JsonObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

public class VerifyUserOTP extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject jsonResponse = new JsonObject();
        response.setContentType("application/json");
        HttpSession session = request.getSession();
        String customer_id = session.getAttribute("customer_id").toString();
        try{
            JsonObject jsonRequest = com.google.gson.JsonParser.parseReader(request.getReader()).getAsJsonObject();
            String otp = jsonRequest.get("otp").getAsString();
            String email = dao.utility.GetEmail.getEmail(customer_id, jsonResponse);

            if(dao.otp.EmailOTP.verifyOTP(email, Integer.parseInt(otp))){
                dao.otp.EmailOTP.updateDB(email, Integer.parseInt(otp), Timestamp.valueOf(java.time.LocalDateTime.now()));
                dao.otp.EmailOTP.deleteOTP(email);
                response.getWriter().println(jsonResponse);
            }

        }catch (Exception e){
            e.printStackTrace();
            jsonResponse.addProperty("status", "400");
            jsonResponse.addProperty("message", "Bad Request");
            response.getWriter().println(jsonResponse);
        }
    }
}
