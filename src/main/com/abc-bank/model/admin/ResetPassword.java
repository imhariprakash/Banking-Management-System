package model.admin;

import com.google.gson.JsonObject;

public class ResetPassword {
    private ResetPassword() {
    } // Prevents instantiation

    public static void reset(JsonObject jsonObject, JsonObject json) {
        try {
            String username = jsonObject.get("username").getAsString();
            String password = jsonObject.get("password").getAsString();
            if(!model.validation.PasswordValidation.isValidPassword(password)){
                json.addProperty("status", "400");
                json.addProperty("message", "Password is invalid. It must be at least 8 characters long, contain an uppercase and a number");
                return;
            }
            dao.admin.ResetPassword.reset(username, password, json);
        } catch (Exception e) {
            e.printStackTrace();
            json.addProperty("status", "500");
            json.addProperty("message", "Internal server error");
        }
    }
}
