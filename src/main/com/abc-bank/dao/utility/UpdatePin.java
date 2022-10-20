package dao.utility;

import com.google.gson.JsonObject;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdatePin {
    private UpdatePin() {
    } // private constructor

    public static void updatePin(String account_number, String pin, JsonObject jsonObject){
        try{
            Connection conn = dao.connection.Connection.getConnection("customers");
            String query = "UPDATE cards SET pin = ? WHERE account_number = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, pin);
            ps.setString(2, account_number);
            ps.executeUpdate();
            jsonObject.addProperty("status", "200");
            jsonObject.addProperty("message", "Pin updated successfully");
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.addProperty("status", "400");
            jsonObject.addProperty("message", "Invalid request");
        }
    }
}
