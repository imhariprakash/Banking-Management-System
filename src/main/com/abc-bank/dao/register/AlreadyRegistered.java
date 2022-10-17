package dao.register;

import com.google.gson.JsonObject;

import java.sql.Connection;

public class AlreadyRegistered {
    public static boolean isAlreadyRegistered(String email, String phone, String pan, String aadhar, JsonObject response) {
        try{
            Connection con = dao.Connection.Connection.getConnection("applications");

            // check if email is already registered

            java.sql.PreparedStatement ps = con.prepareStatement("SELECT * FROM applications WHERE email = ?");
            ps.setString(1, email);
            java.sql.ResultSet rs = ps.executeQuery();
            if(rs.next()){
                response.addProperty("status", "400");
                response.addProperty("message", "Already registered");
                return true;
            }

            // check if phone is already registered

            ps = con.prepareStatement("SELECT * FROM applications WHERE phone = ?");
            ps.setString(1, phone);
            rs = ps.executeQuery();
            if(rs.next()){
                response.addProperty("status", "400");
                response.addProperty("message", "Already registered");
                return true;
            }

            // check if pan is already registered

            ps = con.prepareStatement("SELECT * FROM applications WHERE pan = ?");
            ps.setString(1, pan);
            rs = ps.executeQuery();
            if(rs.next()){
                response.addProperty("status", "400");
                response.addProperty("message", "Already registered");
                return true;
            }

            // check if aadhar is already registered

            ps = con.prepareStatement("SELECT * FROM applications WHERE aadhar = ?");
            ps.setString(1, aadhar);
            rs = ps.executeQuery();
            if(rs.next()){
                response.addProperty("status", "400");
                response.addProperty("message", "Already registered");
                return true;
            }

            return false;
        }catch(Exception e){
            response.addProperty("status", "500");
            response.addProperty("message", "Internal server error");
            return true;
        }
    }
}
