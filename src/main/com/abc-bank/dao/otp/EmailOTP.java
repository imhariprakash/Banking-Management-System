package dao.otp;

import dao.Connection.Connection;

public class EmailOTP {
    private EmailOTP() {
    } // Prevents instantiation

    public static void updateDB(String email, int otp) {
        java.sql.Connection con = Connection.getConnection("otp");
        String query = "INSERT INTO email_otp (email, otp) VALUES (?, ?)";
        try {
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setInt(2, otp);
            ps.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }

        query = "INSERT INTO email_otp_log (email, otp, time_created) VALUES (?, ?, ?)";
        try {
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setInt(2, otp);
            ps.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
            ps.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static boolean verifyOTP(String email, int otp){
        try{
            java.sql.Connection con = Connection.getConnection("otp");
            String query = "SELECT otp FROM email_otp WHERE email = ? ORDER BY time_created DESC LIMIT 1";
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            java.sql.ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int otp_db = rs.getInt("otp");
                if(otp_db == otp){
                    return true;
                }
            }
        }catch(Exception e){
            // TODO : handle exception
        }
        return false;
    }

    public static void updateDB(String email, int otp, java.sql.Timestamp time_verified){
        try{
            java.sql.Connection con = Connection.getConnection("otp");
            String query = "INSERT INTO email_otp_log (email, otp, time_verified) VALUES (?, ?, ?)";
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setInt(2, otp);
            ps.setTimestamp(3, time_verified);
            ps.executeUpdate();
        }catch(Exception e){
            // TODO : handle exception
        }
    }

    public static void deleteOTP(String email){
        try{
            java.sql.Connection con = Connection.getConnection("otp");
            String query = "DELETE FROM email_otp WHERE email = ?";
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.executeUpdate();
        }catch(Exception e){
            // TODO : handle exception
        }
    }
}
