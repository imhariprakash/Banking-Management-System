package dao.register;

import com.google.gson.JsonObject;

import javax.servlet.http.Part;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Register {
    private Register() {
    } // Prevents instantiation

    public static void register(JsonObject json, JsonObject response) {
        // get variables

        String name = json.get("name").getAsString();
        String f_name = json.get("f_name").getAsString();
        String email = json.get("email").getAsString();
        String phone = json.get("phone").getAsString();
        String aadhar = json.get("aadhar").getAsString();
        String pan = json.get("pan").getAsString();
        String address = json.get("address").getAsString();
        String notes = json.get("notes").getAsString();
        int pincode = json.get("pincode").getAsInt();
        Date dob = Date.valueOf(json.get("dob").getAsString());

        // ready to insert into database

        try{
            Connection connection = dao.Connection.Connection.getConnection("applications");
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO applications (cust_name, father_name, email, phone, aadhar, pan, address, notes, pincode, dob) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, f_name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, aadhar);
            preparedStatement.setString(6, pan);
            preparedStatement.setString(7, address);
            preparedStatement.setString(8, notes);
            preparedStatement.setInt(9, pincode);
            preparedStatement.setDate(10, dob);

            preparedStatement.executeUpdate();
            response.addProperty("status", "200");
            response.addProperty("message", "Application submitted successfully at " + new Timestamp(System.currentTimeMillis()) + "!" + " We will get back to you soon.");

        }catch(Exception e){
            System.out.println("Error in Register.java from dao.register");
            System.out.println(e);
            response.addProperty("status", "500");
            response.addProperty("message", "Internal server error");
        }
    }

    public static void register_log(JsonObject json, JsonObject response) {
        String name = json.get("name").getAsString();
        String f_name = json.get("f_name").getAsString();
        String email = json.get("email").getAsString();
        String phone = json.get("phone").getAsString();
        String aadhar = json.get("aadhar").getAsString();
        String pan = json.get("pan").getAsString();
        String address = json.get("address").getAsString();
        String notes = json.get("notes").getAsString();
        int pincode = json.get("pincode").getAsInt();
        Date dob = Date.valueOf(json.get("dob").getAsString());

        try{
            Connection connection = dao.Connection.Connection.getConnection("applications");
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO applications_log (cust_name, father_name, email, phone, aadhar, pan, address, notes, pincode, dob) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, f_name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, aadhar);
            preparedStatement.setString(6, pan);
            preparedStatement.setString(7, address);
            preparedStatement.setString(8, notes);
            preparedStatement.setInt(9, pincode);
            preparedStatement.setDate(10, dob);

            preparedStatement.executeUpdate();
            response.addProperty("status", "200");
            response.addProperty("message", "Application submitted successfully at " + new Timestamp(System.currentTimeMillis()) + "!" + " We will get back to you soon.");

        }catch(Exception e){
            System.out.println("Error in Register.java from dao.register");
            System.out.println(e);
            response.addProperty("status", "500");
            response.addProperty("message", "Internal server error");
        }
    }
}
