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
            String name = json.get("name").getAsString().trim();
            String f_name = json.get("f_name").getAsString().trim();
            String email = json.get("email").getAsString().trim();
            String phone = json.get("phone").getAsString().trim();
            String aadhar = json.get("aadhar").getAsString().trim();
            String pan = json.get("pan").getAsString().trim();
            String address = json.get("address").getAsString().trim();
            int pincode = json.get("pincode").getAsInt();
            Timestamp timestamp = Timestamp.valueOf(json.get("dob").getAsString());
            String notes = json.get("notes").getAsString().trim();
            System.out.println(name);
            System.out.println(f_name);
            System.out.println(email);
            System.out.println(phone);
            System.out.println(aadhar);
            System.out.println(pan);
            System.out.println(address);
            System.out.println(pincode);
            System.out.println(timestamp);
            System.out.println(notes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
