package model.register;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServletRequest;

public class Register {
    private Register() {
    } // Prevents instantiation

    public static void register(JsonObject json){
        try {
            dao.register.Register.register(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
