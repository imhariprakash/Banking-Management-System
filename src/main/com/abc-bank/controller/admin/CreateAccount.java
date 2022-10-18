package controller.admin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateAccount extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Max-Age", "3600");

        JsonObject json = new JsonObject();
        HttpSession session = request.getSession();
        String verified_by = (String) session.getAttribute("username");

        System.out.println("Create Account Servlet");

        if(verified_by == null){
            response.setStatus(401);
            json.addProperty("status", "401");
            json.addProperty("message", "Admin : You are not logged in to verify applications");
            response.getWriter().println(json);
            return;
        }

        try{
            JsonObject jsonObject = JsonParser.parseReader(request.getReader()).getAsJsonObject();
            System.out.println(verified_by);
            jsonObject.addProperty("verified_by", verified_by);
            model.accounts.CreateAccount.create(jsonObject, json);
            dao.applications.Applications.deleteApplication(jsonObject.get("email").getAsString());

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

        }catch(Exception e){
            e.printStackTrace();
            response.setContentType("application/json");
            response.setStatus(500);
            json.addProperty("message", "Internal server error");
            json.addProperty("status", "500");
            response.getWriter().println(json);
        }
    }
}
