package dao.otp;

import dao.connection.Connection;

import java.sql.Timestamp;

public class MobileOTP {
    private MobileOTP() {
    } // Prevents instantiation

    public static void updateDB(String phone_number, int otp) {
        //System.out.println("MobileOTP from dao");
        java.sql.Connection con = Connection.getConnection("otp");
        String query = "INSERT INTO mobile_otp (mobile, otp) VALUES (?, ?)";
        try {
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, phone_number);
            ps.setInt(2, otp);
            ps.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
            //System.out.println(e);
            e.printStackTrace();
        }

        query = "INSERT INTO mobile_otp_log (mobile, otp, time_created) VALUES (?, ?, ?)";
        try {
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, phone_number);
            ps.setInt(2, otp);
            ps.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
            ps.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
            //System.out.println(e);
            e.printStackTrace();
        }
    }

    public static boolean verifyOTP(String phone_number, int otp){
        try{
            java.sql.Connection con = Connection.getConnection("otp");
            String query = "SELECT otp FROM mobile_otp WHERE mobile = ? ORDER BY time_created DESC LIMIT 1";
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, phone_number);
            java.sql.ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int otp_db = rs.getInt("otp");
                if(otp_db == otp){
                    return true;
                }
            }
        }catch(Exception e){
            // TODO : handle exception
            //System.out.println(e);
            e.printStackTrace();
        }
        return false;
    }

    public static void updateDB(String phone_number, int otp, Timestamp time_verified){
        try{
            java.sql.Connection con = Connection.getConnection("otp");
            String query = "UPDATE mobile_otp_log SET time_verified = ? WHERE mobile = ? AND otp = ?";
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setTimestamp(1, time_verified);
            ps.setString(2, phone_number);
            ps.setInt(3, otp);
            ps.executeUpdate();
        }catch(Exception e){
            // TODO : handle exception
        }
    }

    public static void deleteOTP(String phone_number){
        try{
            java.sql.Connection con = Connection.getConnection("otp");
            String query = "DELETE FROM mobile_otp WHERE mobile = ?";
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, phone_number);
            ps.executeUpdate();
        }catch(Exception e){
            // TODO : handle exception
        }
    }
}
