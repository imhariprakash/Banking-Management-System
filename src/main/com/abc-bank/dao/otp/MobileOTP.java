package dao.otp;

import dao.Connection.GetConnection;

import java.sql.Connection;

public class MobileOTP {
    private MobileOTP() {
    } // Prevents instantiation

    public static void updateDB(String phone_number, int otp) {
        System.out.println("MobileOTP from dao");
        Connection con = GetConnection.getConnection("otp");
        String query = "INSERT INTO mobile_otp (mobile, otp) VALUES (?, ?)";
        try {
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, phone_number);
            ps.setInt(2, otp);
            ps.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
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
            System.out.println(e);
        }
    }
}
