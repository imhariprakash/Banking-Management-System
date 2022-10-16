package model.validation;

public class PincodeValidation {
    private PincodeValidation() {
    } // Prevents instantiation

    public static boolean isValidPincode(int pincode) {
        return pincode >= 100000 && pincode <= 999999;
    }
}
