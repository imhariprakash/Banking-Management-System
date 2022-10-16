package dao.register;

import com.google.gson.JsonObject;

import javax.servlet.http.Part;
import java.io.InputStream;
import java.sql.Time;
import java.sql.Timestamp;

public class Register {
    private Register() {
    } // Prevents instantiation

    public static void register(JsonObject json){
        try {
            String name = json.get("name").getAsString();
            String f_name = json.get("f_name").getAsString();
            String email = json.get("email").getAsString();
            String phone = json.get("phone").getAsString();
            String aadhar = json.get("aadhar").getAsString();
            String pan = json.get("pan").getAsString();
            String address = json.get("address").getAsString();
            int pincode = json.get("pincode").getAsInt();
            Timestamp timestamp = Timestamp.valueOf(json.get("dob").getAsString());
            String notes = json.get("notes").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
