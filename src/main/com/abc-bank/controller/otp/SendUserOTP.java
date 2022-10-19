package controller.otp;

import com.google.gson.JsonObject;

import javax.mail.Session;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SendUserOTP extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject responseJson = new JsonObject();
        try {
            HttpSession session = request.getSession();
            String customer_id = session.getAttribute("customer_id").toString();
            String email = dao.utility.GetEmail.getEmail(customer_id, responseJson);
            if (responseJson.get("status").getAsString().equals("200")) {
                model.mailings.SendUserMail.sendUserMail(email);
                responseJson.addProperty("status", "200");
                responseJson.addProperty("message", "OTP sent to your email");
                response.setContentType("application/json");
                response.getWriter().println(responseJson);
            } else {
                response.setContentType("application/json");
                response.getWriter().println(responseJson);
            }
        }catch(Exception e){
            e.printStackTrace();
            responseJson = new JsonObject();
            responseJson.addProperty("status", "400");
            responseJson.addProperty("message", "Bad Request");
            response.setContentType("application/json");
            response.getWriter().println(responseJson);
        }

    }
}
