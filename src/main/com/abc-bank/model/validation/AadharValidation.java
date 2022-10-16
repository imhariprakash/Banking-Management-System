package model.validation;

public class AadharValidation {
    private AadharValidation() {
    } // Prevents instantiation

    public static boolean isValidAadhar(String aadhar) {
        return aadhar.matches("^[2-9]{1}[0-9]{3}[0-9]{8}$");
    }
}
