package controller.register;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Register extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Max-Age", "3600");

        try{
            JsonObject jsonRequest = JsonParser.parseString(request.getReader().readLine()).getAsJsonObject();
            JsonObject jsonResponse = new JsonObject();
            model.validation.ValidateApplication.validate(jsonRequest, jsonResponse);
            if(jsonResponse.get("status").getAsString().equals("200")) {
                response.setStatus(200);
            }
            else if(jsonResponse.get("status").getAsString().equals("500")){
                response.setStatus(500);
            }else{
                response.setStatus(400);
            }
            response.setContentType("application/json");
            response.getWriter().print(jsonResponse);
        }catch (Exception e){ // This is almost never reached because of the try-catch in the model
            System.out.println("Error in Register.java from controller.register");
            System.out.println(e);
            e.printStackTrace();
            response.setStatus(400);
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("status", "400");
            jsonResponse.addProperty("message", "Invalid request");
            response.setContentType("application/json");
            response.getWriter().print(jsonResponse);
        }
    }
}
