package dao.register;

import com.google.gson.JsonObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AlreadyRegistered {
    public static boolean isAlreadyRegistered(String email, String phone, String pan, String aadhar, JsonObject response) {
        try{

            // check customer database

            Connection con = dao.Connection.Connection.getConnection("customers");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM customers WHERE email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                response.addProperty("status", "400");
                response.addProperty("message", "Already registered customer - please login instead!");
                return true;
            }

            // check phone

            ps = con.prepareStatement("SELECT * FROM customers WHERE phone = ?");
            ps.setString(1, phone);
            rs = ps.executeQuery();
            if(rs.next()){
                response.addProperty("status", "400");
                response.addProperty("message", "Phone number already registered with us!");
                return true;
            }

            // check pan

            ps = con.prepareStatement("SELECT * FROM customers WHERE pan = ?");
            ps.setString(1, pan);
            rs = ps.executeQuery();
            if(rs.next()){
                response.addProperty("status", "400");
                response.addProperty("message", "PAN already registered with us!");
                return true;
            }

            // check aadhar

            ps = con.prepareStatement("SELECT * FROM customers WHERE aadhar = ?");
            ps.setString(1, aadhar);
            rs = ps.executeQuery();
            if(rs.next()){
                response.addProperty("status", "400");
                response.addProperty("message", "Aadhar already registered with us!");
                return true;
            }


            // check applications db - whether already applied
            con.close();
            con = dao.Connection.Connection.getConnection("applications");

            // check if email is already applied - pending

            ps = con.prepareStatement("SELECT * FROM applications WHERE email = ? AND verified_by IS NULL");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if(rs.next()){
                response.addProperty("status", "400");
                response.addProperty("message", "Already applied - please wait for verification!");
                return true;
            }

            // check if phone is already registered

            ps = con.prepareStatement("SELECT * FROM applications WHERE phone = ? AND verified_by IS NULL");
            ps.setString(1, phone);
            rs = ps.executeQuery();
            if(rs.next()){
                response.addProperty("status", "400");
                response.addProperty("message", "Already applied - please wait for verification!");
                return true;
            }

            // check if pan is already registered

            ps = con.prepareStatement("SELECT * FROM applications WHERE pan = ? AND verified_by IS NULL");
            ps.setString(1, pan);
            rs = ps.executeQuery();
            if(rs.next()){
                response.addProperty("status", "400");
                response.addProperty("message", "Already applied - please wait for verification!");
                return true;
            }

            // check if aadhar is already registered

            ps = con.prepareStatement("SELECT * FROM applications WHERE aadhar = ? AND verified_by IS NULL");
            ps.setString(1, aadhar);
            rs = ps.executeQuery();
            if(rs.next()){
                response.addProperty("status", "400");
                response.addProperty("message", "Already applied - please wait for verification!");
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
