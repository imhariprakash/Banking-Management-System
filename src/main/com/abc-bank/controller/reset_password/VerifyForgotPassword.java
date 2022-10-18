package controller.reset_password;

import com.google.gson.JsonObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyForgotPassword extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Max-Age", "3600");

        try{

        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(400);
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("status", "400");
            responseJson.addProperty("message", "Invalid Request");
        }
    }
}
