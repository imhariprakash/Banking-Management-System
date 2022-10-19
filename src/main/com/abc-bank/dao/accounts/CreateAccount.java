package dao.accounts;

import com.google.gson.JsonObject;

import java.sql.Connection;
import java.sql.Date;

public class CreateAccount {
    private CreateAccount(){
    } // private constructor to prevent instantiation

    public static void create(JsonObject req, JsonObject res){
        String cust_name, father_name, email, phone, aadhar, pan, address;
        int pincode;
        Date dob;

        try {
            cust_name = req.get("name").getAsString().trim();
            father_name = req.get("f_name").getAsString().trim();
            email = req.get("email").getAsString().trim().toLowerCase();
            phone = req.get("phone").getAsString().trim();
            aadhar = req.get("aadhar").getAsString().trim();
            pan = req.get("pan").getAsString().trim().toUpperCase();
            address = req.get("address").getAsString().trim();
            pincode = req.get("pincode").getAsInt();
            dob = Date.valueOf(req.get("dob").getAsString().trim());
        }catch(Exception e){
            res.addProperty("status", "400");
            res.addProperty("message", "Invalid request");
            return;
        }

        try{
            Connection conn = dao.connection.Connection.getConnection("customers");
            java.sql.PreparedStatement ps = conn.prepareStatement("INSERT INTO customers (cust_name, father_name, email, phone, aadhar, pan, address, pincode, dob) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, cust_name);
            ps.setString(2, father_name);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, aadhar);
            ps.setString(6, pan);
            ps.setString(7, address);
            ps.setInt(8, pincode);
            ps.setDate(9, dob);
            ps.executeUpdate();
            res.addProperty("status", "200");
            res.addProperty("message", "Account created successfully");
        }catch(Exception e){
            res.addProperty("status", "500");
            res.addProperty("message", "Internal server error");
            return;
        }
    }
}
