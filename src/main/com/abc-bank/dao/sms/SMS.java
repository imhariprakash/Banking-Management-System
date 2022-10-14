package dao.sms;

import java.sql.Connection;
import dao.Connection.GetConnection;

public class SMS {
    private SMS() {
    } // Prevents instantiation

    public static String getTwilioAccountSID() {
        String twilioAccountSID = null;
        try {
            Connection con = GetConnection.getConnection("otp");
            java.sql.Statement stmt = con.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("select account_sid from twilio_credentials");
            while (rs.next()) {
                twilioAccountSID = rs.getString(1);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return twilioAccountSID;
    }

    public static String getTwilioAuthToken() {
        String twilioAuthToken = null;
        try {
            Connection con = GetConnection.getConnection("otp");
            java.sql.Statement stmt = con.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("select auth_token from twilio_credentials");
            while (rs.next()) {
                twilioAuthToken = rs.getString(1);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return twilioAuthToken;
    }
}
