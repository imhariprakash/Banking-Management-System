package controller.atm;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

public class Withdraw extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String account_number = dao.utility.GetAccountNumber.getAccountNumber(session.getAttribute("customer_id").toString());
            JsonObject jsonObject = JsonParser.parseReader(request.getReader()).getAsJsonObject();
            JsonObject json = new JsonObject();
            String amount = jsonObject.get("amount").getAsString();

            UUID uuid = UUID.randomUUID();

            String balance = dao.utility.GetBalanceFromAccountNumber.getBalance(account_number);

            if(Double.valueOf(balance) < Double.valueOf(amount)) {
                response.setStatus(400);
                json.addProperty("status", "400");
                json.addProperty("message", "Insufficient balance");
                response.getWriter().println(json);
            }

            dao.transactions.atm.Withdraw.withdraw(account_number, Double.valueOf(amount), uuid.toString(), "ATM withdraw", json);

            if(json.get("status").getAsString().equals("200")) {
                response.setStatus(200);
                json.addProperty("status", "200");
                json.addProperty("message", "Withdraw successful");
                response.getWriter().println(json);
            } else {
                response.setStatus(400);
                json.addProperty("status", "400");
                json.addProperty("message", "Withdraw failed");
                response.getWriter().println(json);
            }


        }catch(Exception e){
            e.printStackTrace();
            response.sendError(400);
            JsonObject json = new JsonObject();
            json.addProperty("status", "400");
            json.addProperty("message", "Bad request");
            response.getWriter().println(json);
        }
    }
}

