package model.sms;


import model.sms.SMS;

public class SendSMS {
    public static void send(String phone_number, int otp) {
        String created_at = new java.sql.Timestamp(new java.util.Date().getTime()).toString();
        String subject = "OTP from ABC Bank";
        String body = "OTP : " + otp + "\nDate : " + created_at + "\n\nThis is your One Time Password (OTP) valid for only 5 minutes.\n\n" + otp + "\n\nPlease do not share this OTP with anyone.\n\nRegards,\nABC Bank";
        SMS.send(phone_number, subject, body);
        dao.otp.MobileOTP.updateDB(phone_number, otp);
    }
}



