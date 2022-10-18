package model.admin;

import com.google.gson.JsonObject;

public class ResetPassword {
    private ResetPassword() {
    } // Prevents instantiation

    public static void reset(JsonObject jsonObject, JsonObject json) {
        try {
            String username = jsonObject.get("username").getAsString();
            String password = jsonObject.get("password").getAsString();
            dao.admin.ResetPassword.reset(username, password, json);
        } catch (Exception e) {
            json.addProperty("status", "500");
            json.addProperty("message", "Internal server error");
        }
    }
}
