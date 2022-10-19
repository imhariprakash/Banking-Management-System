package dao.otp;

import com.google.gson.JsonObject;

import java.sql.Connection;

public class ForgotPassword {
    public static void insertOTP(String customer_id, int otp) {
        try{
            Connection con = dao.connection.Connection.getConnection("forgot_password");
            String query = "INSERT INTO otp (customer_id, otp) VALUES (?, ?)";
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, customer_id);
            ps.setInt(2, otp);
            ps.executeUpdate();
            query = "INSERT INTO otp_log (customer_id, otp) VALUES (?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, customer_id);
            ps.setInt(2, otp);
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void removeOTP(String customer_id) {
        try{
            Connection con = dao.connection.Connection.getConnection("forgot_password");
            String query = "DELETE FROM otp WHERE customer_id = ?";
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, customer_id);
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean verifyOTP(String customer_id, int otp, JsonObject responseJson){
        try{
            Connection con = dao.connection.Connection.getConnection("forgot_password");
            String query = "SELECT * FROM otp WHERE customer_id = ? AND otp = ?";
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, customer_id);
            ps.setInt(2, otp);
            java.sql.ResultSet rs = ps.executeQuery();
            if(rs.next()){
                removeOTP(customer_id);
                responseJson.addProperty("status", "200");
                responseJson.addProperty("message", "OTP verified");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        responseJson.addProperty("status", "400");
        responseJson.addProperty("message", "Invalid OTP");
        return false;
    }

}
