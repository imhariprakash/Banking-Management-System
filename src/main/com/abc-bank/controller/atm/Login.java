package controller.atm;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject jsonResponse = new JsonObject();
        try{
            JsonObject jsonRequest = JsonParser.parseReader(request.getReader()).getAsJsonObject();
            String card_number = jsonRequest.get("card_number").getAsString();
            String pin = jsonRequest.get("pin").getAsString();
            String account_number = dao.utility.GetAccountNumberFromCardNumber.getAccountNumber(card_number, jsonResponse);
            if (account_number == null) {
                response.setStatus(400);
                response.getWriter().println(jsonResponse);
                return;
            }

            if(dao.utility.CardStatus.isBlocked(card_number)){
                response.setStatus(400);
                jsonResponse.addProperty("status", "400");
                jsonResponse.addProperty("message", "Card is blocked");

                String email = dao.utility.GetEmailFromAccountNumber.getEmail(account_number, new JsonObject());
                String subject = "Card Blocked";
                String body = "Your card is blocked. Please unblock at the home page!.";
                model.mailings.Email.send(email, subject, body);


                response.getWriter().println(jsonResponse);
            }

            if(!dao.utility.CheckPin.checkPin(card_number, pin, jsonResponse)){
                response.setStatus(401);

                String email = dao.utility.GetEmailFromAccountNumber.getEmail(account_number, new JsonObject());
                String subject = "Wrong Pin";
                String body = "Wrong pin has been entered. If you have forgotten your pin, please reset it at the home page!.";
                model.mailings.Email.send(email, subject, body);

                response.getWriter().println(jsonResponse);
                return;
            }

            jsonResponse.addProperty("status", "200");
            jsonResponse.addProperty("message", "Login successful");

            String email = dao.utility.GetEmailFromAccountNumber.getEmail(account_number, new JsonObject());
            String subject = "ATM - Login successful for card number " + card_number.substring(0, 4) + " XXXX XXXX" + card_number.substring(12);
            String body = "Login successful for card number " + card_number.substring(0, 4) + " XXXX XXXX" + card_number.substring(12) + " at " + java.time.LocalDateTime.now();
            model.mailings.Email.send(email, subject, body);

            response.getWriter().println(jsonResponse);

        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(400);
            jsonResponse.addProperty("message", "Invalid request");
            response.setContentType("application/json");
            response.getWriter().println(jsonResponse);
        }
    }
}
