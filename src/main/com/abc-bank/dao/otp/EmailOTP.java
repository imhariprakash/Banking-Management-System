package dao.otp;

import dao.Connection.GetConnection;

import java.sql.Connection;

public class EmailOTP {
    private EmailOTP() {
    } // Prevents instantiation

    public static void updateDB(String email, int otp) {
        Connection con = GetConnection.getConnection("otp");
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
}
