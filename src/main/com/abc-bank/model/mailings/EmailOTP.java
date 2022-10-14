package model.mailings;

import java.util.Date;


public class EmailOTP {
    private EmailOTP() {
    } // Prevents instantiation

    public static void sendEmail(String email, int otp) {
        String created_at = new java.sql.Timestamp(new java.util.Date().getTime()).toString();
        String subject = "OTP from ABC Bank";
        String body = "Date : " + created_at + "\n\nThis is your One Time Password (OTP) valid for only 5 minutes.\n\n" + otp + "\n\nPlease do not share this OTP with anyone.\n\nRegards,\nABC Bank";
        Email.send(email, subject, body);
        dao.otp.EmailOTP.updateDB(email, otp);
    }
}
