package controller.transaction;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SendMoney extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try{
            HttpSession session = request.getSession();
            JsonObject jsonRequest = JsonParser.parseReader(request.getReader()).getAsJsonObject();
            String from_account = dao.utility.GetAccountNumber.getAccountNumber(session.getAttribute("customer_id").toString());
            String to_account = jsonRequest.get("to_account").getAsString();
            if(from_account.equals(to_account)){
                response.setStatus(400);
                JsonObject jsonResponse = new JsonObject();
                jsonResponse.addProperty("status", "400");
                jsonResponse.addProperty("message", "You cannot send money to your own account");
                response.getWriter().println(jsonResponse);
                return;
            }
            jsonRequest.addProperty("from_account", from_account);
            JsonObject json = new JsonObject();
            model.transactions.Transaction.send(jsonRequest, json);
            if(json.get("status").getAsString().equals("200")) {
                response.setStatus(200);
                response.getWriter().println(json);
            }else{
                response.setStatus(400);
                response.getWriter().println(json);
            }
        }catch(Exception e){
            e.printStackTrace();
            response.setStatus(400);
            JsonObject json = new JsonObject();
            json.addProperty("status", "400");
            json.addProperty("message", "Bad Request");
        }
    }
}
