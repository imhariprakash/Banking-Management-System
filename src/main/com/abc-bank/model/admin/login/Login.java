package model.admin.login;

import com.google.gson.JsonObject;

public class Login {
    private Login() {
    } // private constructor

    public static void login(JsonObject request, JsonObject response) {
        try{
            String username = request.get("username").getAsString();
            String password = request.get("password").getAsString();
            if(dao.admin.Login.login(username, password, response)) {
                response.addProperty("status", "200");
                response.addProperty("message", "Login Successful");
            }
        }
        catch(Exception e) {
            response.addProperty("status", "400");
            response.addProperty("message", "Bad Request Format");
        }
    }
}
