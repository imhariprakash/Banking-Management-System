package model.register;

import com.google.gson.JsonObject;

public class Register {  // This class does nothing but forward the parameter - this call is for meaningful flow
    private Register() {
    } // Prevents instantiation

    public static void register(JsonObject json, JsonObject response) {
        try {
            model.validation.ValidateApplication.validate(json, response);
        } catch (Exception e) { // This catch block is almost useless
            System.out.println("Error in Register.java at model.register.Register.register()");
            System.out.println(e);
        }
    }
}
