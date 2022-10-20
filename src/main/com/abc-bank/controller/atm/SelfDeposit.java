package controller.atm;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.cj.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

public class SelfDeposit extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject jsonObject = new JsonObject();
        UUID uuid = UUID.randomUUID();
        try{
            JsonObject json = JsonParser.parseReader(request.getReader()).getAsJsonObject();
            HttpSession session = request.getSession();
            String account_number = dao.utility.GetAccountNumber.getAccountNumber(session.getAttribute("customer_id").toString());

            // self deposit - say both from and to account number is same

            dao.transactions.atm.Deposit.deposit(account_number, account_number, json.get("amount").getAsDouble(), uuid.toString(), "self deposit", jsonObject);

            if(jsonObject.get("status").getAsString().equals("200")){
                response.setStatus(200);
                jsonObject.addProperty("status", "200");
                jsonObject.addProperty("message", "Deposit successful");
                response.getWriter().println(jsonObject);
                return;
            }else{
                response.setStatus(500);
                jsonObject.addProperty("status", "500");
                jsonObject.addProperty("message", "Deposit failed");
                response.getWriter().println(jsonObject);
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(400);
            jsonObject.addProperty("message", "Invalid request");
            response.getWriter().println(jsonObject);
        }
    }
}
