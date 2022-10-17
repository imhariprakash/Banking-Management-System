package controller.admin;

import com.google.gson.JsonObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateAccount extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Max-Age", "3600");

        

        try{

        }catch(Exception e){
            System.out.println(e + " in CreateAccount for admin controller");
            JsonObject json = new JsonObject();
            response.setContentType("application/json");
            response.setStatus(500);
            json.addProperty("message", "Internal server error");
            json.addProperty("status", "500");
            response.getWriter().println(json);
        }
    }
}
