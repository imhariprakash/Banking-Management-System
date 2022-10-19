package model.mailings;

public class SendUserMail {
    private SendUserMail() {
    } // Prevents instantiation

    public static void sendUserMail(String email){
        String otp = String.valueOf(model.validation.OTPGenerator.generate());
        String subject = "OTP for ABC Bank";
        String message = "Your OTP for ABC Bank is " + otp;
        model.mailings.Email.send(email, subject, message);

        dao.otp.EmailOTP.updateDB(email, Integer.parseInt(otp));
    }
}
