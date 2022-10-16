package controller.register;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Register extends HttpServlet {
    private Register() {
    } // Prevents instantiation

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Max-Age", "3600");

        try{
            JsonObject jsonRequest = JsonParser.parseString(request.getReader().readLine()).getAsJsonObject();
            model.register.Register.register(jsonRequest);
            response.setStatus(200);
            response.setContentType("application/json");
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("status", "200");
            jsonResponse.addProperty("message", "Successfully registered");
            response.getWriter().print(jsonResponse);
        }catch (Exception e){
            System.out.println(e);
            response.setStatus(400);
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("status", "400");
            jsonResponse.addProperty("message", "Invalid request");
            response.setContentType("application/json");
            response.getWriter().print(jsonResponse);
        }
    }
}
