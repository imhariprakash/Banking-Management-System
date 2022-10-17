package controller.admin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateAccount extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Max-Age", "3600");

        JsonObject json = new JsonObject();

        try{
            JsonObject jsonObject = JsonParser.parseReader(request.getReader()).getAsJsonObject();
            model.accounts.CreateAccount.create(jsonObject, json);

            response.setContentType("application/json");

            if(json.get("status").getAsString().equals("500")) {
                response.setStatus(500);
                response.getWriter().println(json);
            }

            else if(json.get("status").getAsString().equals("200")) {
                response.setStatus(200);
                response.getWriter().println(json);
            }

            else {
                response.setStatus(400);
                response.getWriter().println(json);
            }

            dao.applications.Applications.deleteApplication(jsonObject.get("email").getAsString());

        }catch(Exception e){
            System.out.println(e + " in CreateAccount for admin controller");
            response.setContentType("application/json");
            response.setStatus(500);
            json.addProperty("message", "Internal server error");
            json.addProperty("status", "500");
            response.getWriter().println(json);
        }
    }
}
