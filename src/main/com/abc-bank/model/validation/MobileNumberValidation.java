package model.validation;

public class MobileNumberValidation {

    /*
    *   This class validates mobile numbers based on regex
     */
    private MobileNumberValidation() {
    } // Prevents instantiation

    public static boolean isValidMobileNumber(String mobileNumber) {
        mobileNumber = mobileNumber.trim().replace(" ", "").replace("-", "").replace("(", "").replace(")", "").replace("+91", "").replace("+", "");
        return mobileNumber.matches("[0-9]{10}");
    }
}
