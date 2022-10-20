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

public class OtherDeposit extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String from_account_number = dao.utility.GetAccountNumber.getAccountNumber(session.getAttribute("customer_id").toString());
        JsonObject jsonObject = new JsonObject();
        try{
            JsonObject json = JsonParser.parseReader(request.getReader()).getAsJsonObject();
            String account_number = json.get("account_number").getAsString();
            String amount = json.get("amount").getAsString();

            UUID uuid = UUID.randomUUID();


            dao.transactions.atm.Deposit.deposit(from_account_number, account_number, Double.parseDouble(amount), uuid.toString(), "ATM other deposit", jsonObject);

            if(jsonObject.get("status").getAsString().equals("200")){
                response.setStatus(200);
                jsonObject.addProperty("status", "200");
                jsonObject.addProperty("message", "Deposit successful");
                response.getWriter().println(jsonObject);
            }else {
                response.setStatus(500);
                jsonObject.addProperty("status", "500");
                jsonObject.addProperty("message", "Deposit failed");
                response.getWriter().println(jsonObject);
            }
        }catch(Exception e){
            e.printStackTrace();
            response.setStatus(400);
            jsonObject.addProperty("status", "400");
            jsonObject.addProperty("message", "Invalid request");
            response.getWriter().println(jsonObject);
        }
    }
}
