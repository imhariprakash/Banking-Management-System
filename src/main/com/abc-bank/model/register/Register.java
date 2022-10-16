package model.register;

import com.google.gson.JsonObject;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Register {
    private Register() {
    } // Prevents instantiation

    public static void register(JsonObject json){
        try {
            String name = capitalize(json.get("name").getAsString().trim().toLowerCase());
            String f_name = capitalize(json.get("f_name").getAsString().trim());
            String email = json.get("email").getAsString().trim().toLowerCase();
            String phone = json.get("phone").getAsString().trim().replace("\\s", "");
            String aadhar = json.get("aadhar").getAsString().trim().replace("\\s", "").replace(" ", "");
            String pan = json.get("pan").getAsString().trim().replace("\\s", "").toUpperCase();
            String address = json.get("address").getAsString().trim();
            int pincode = json.get("pincode").getAsInt();
            Date dob = getDate(json.get("dob").getAsString());
            String notes = json.get("notes").getAsString().trim();
            System.out.println(name);
            System.out.println(f_name);
            System.out.println(email);
            System.out.println(phone);
            System.out.println(aadhar);
            System.out.println(pan);
            System.out.println(address);
            System.out.println(pincode);
            System.out.println(dob);
            System.out.println(notes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String capitalize(String str){
        str = str.trim().replace("\\s", " ");
        String[] words = str.split(" ");

        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }
        return String.join(" ", words);
    }

    public static Date getDate(String date){
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return new Date(dateFormat.parse(date).getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
