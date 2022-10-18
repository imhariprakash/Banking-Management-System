package controller.reset_password;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Password extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Max-Age", "3600");

        try{
            JsonObject requestJson = JsonParser.parseReader(request.getReader()).getAsJsonObject();
            JsonObject responseJson = new JsonObject();
            String customer_id = requestJson.get("customer_id").getAsString();
            String email = dao.utility.GetEmail.getEmail(customer_id, responseJson);
            if(responseJson.get("status").getAsString().equals("400")){
                response.setStatus(400);
                response.setContentType("application/json");
                response.getWriter().println(responseJson);
                return;
            }
            int otp = model.validation.OTPGenerator.generate();
            dao.otp.ForgotPassword.insertOTP(customer_id, otp);
            model.mailings.Email.forgotPassword(otp, email);
            response.setStatus(200);
            response.setContentType("application/json");
            JsonObject json = new JsonObject();
            json.addProperty("status", "200");
            json.addProperty("message", "OTP sent to " + email);
            response.getWriter().println(json);
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(400);
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("status", "400");
            responseJson.addProperty("message", "Invalid Request");
        }
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "PUT");
        response.setHeader("Access-Control-Max-Age", "3600");

        try{
            JsonObject requestJson = JsonParser.parseReader(request.getReader()).getAsJsonObject();
            JsonObject responseJson = new JsonObject();
            String customer_id = requestJson.get("customer_id").getAsString();
            String password = requestJson.get("password").getAsString();
            String email = dao.utility.GetEmail.getEmail(customer_id, responseJson);
            if(responseJson.get("status").getAsString().equals("400")){
                response.setStatus(400);
                response.setContentType("application/json");
                response.getWriter().println(responseJson);
                return;
            }
            if(!model.validation.PasswordValidation.isValidPassword(password)){
                response.setStatus(400);
                response.setContentType("application/json");
                JsonObject json = new JsonObject();
                json.addProperty("status", "400");
                json.addProperty("message", "Invalid Password");
                response.getWriter().println(json);
                return;
            }
            dao.login.Password.updatePassword(customer_id, password, responseJson);
            if(responseJson.get("status").getAsString().equals("400")){
                response.setStatus(400);
                response.setContentType("application/json");
                response.getWriter().println(responseJson);
                return;
            }
            response.setStatus(200);
            response.setContentType("application/json");
            JsonObject json = new JsonObject();
            json.addProperty("status", "200");
            json.addProperty("message", "Password updated successfully");
            response.getWriter().println(json);
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(400);
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("status", "400");
            responseJson.addProperty("message", "Invalid Request");
        }
    }
}
