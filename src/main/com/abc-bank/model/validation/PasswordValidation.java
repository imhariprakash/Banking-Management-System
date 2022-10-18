package model.validation;

public class PasswordValidation {
    private PasswordValidation() {
    } // Prevents instantiation

    public static boolean isValidPassword(String password) {
        if(password.length() < 8 || password.length() > 20 || password == null || password.equals("")){
            return false;
        }
        if(!password.matches("^[a-zA-Z0-9-!*$]*$")){
            return false;
        }
        return true;
    }
}
