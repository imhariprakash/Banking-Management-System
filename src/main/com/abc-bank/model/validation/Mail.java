package model.validation;

public class Mail {
    private Mail() {
    } // Prevents instantiation

    public static void mail(String to){
        String Subject = "Welcome to ABC Bank";
        String body = "Welcome to ABC Bank. You have submitted your application successfully. We will contact you soon.";
        model.mailings.Email.send(to, Subject, body);
    }
}
