package model.validation;

public class EmailValidation {
    /*
        * Email validation
        * Verifies that the email is valid, based on regex
     */
    private EmailValidation() {
    } // Prevents instantiation

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

        java.util.regex.Pattern pat = java.util.regex.Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
