package model.accounts;

public class Mail {
    private Mail() {
    } // Prevents instantiation

    public static void mail(String to){
        String Subject = "Welcome to ABC Bank";
        String body = "Welcome to ABC Bank. Your account has been created successfully. You can now login to your account and start using our services.";
        model.mailings.Email.send(to, Subject, body);
    }

    public static void rejectApplication(String to, String name, String review, String applied_on) {
        String subject;
        if(applied_on == null) {
            subject = "ABC Bank - Application Rejected";
        }else{
            subject = "ABC Bank - Application Rejected for " + applied_on;
        }
        String body = "Dear " + name + ",\n\n" +
                "Your application for ABC Bank has been rejected. The reason for rejection is as follows:\n\n" +
                review + "\n\n" +
                "You can apply again or visit us in person.\n\n" +
                "Regards,\n" +
                "ABC Bank";
        model.mailings.Email.send(to, subject, body);
    }
}
