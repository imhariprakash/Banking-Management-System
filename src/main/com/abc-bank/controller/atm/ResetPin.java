package controller.atm;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.cj.Session;
import com.mysql.cj.protocol.x.Notice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ResetPin extends HttpServlet {
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject jsonObject = new JsonObject();
        try{
            HttpSession session = request.getSession();
            String account_number = dao.utility.GetAccountNumber.getAccountNumber(session.getAttribute("customer_id").toString());
            JsonObject json = JsonParser.parseReader(request.getReader()).getAsJsonObject();
            String otp = json.get("pin").getAsString();
            dao.utility.UpdatePin.updatePin(account_number, otp, jsonObject);
            response.setStatus(200);

            String email = dao.utility.GetEmailFromAccountNumber.getEmail(account_number, new JsonObject());
            String subject = "Pin Reset";
            String message = "Your pin has been reset at " + new java.util.Date().toString() + ". If you did not do this, please block your account.";
            model.mailings.Email.send(email, subject, message);

            response.getWriter().println(jsonObject);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.addProperty("status", "400");
            jsonObject.addProperty("message", "Invalid request");
            response.getWriter().println(jsonObject);
        }
    }
}
