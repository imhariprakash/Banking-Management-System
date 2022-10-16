package model.validation;

public class PanValidation {
    private PanValidation() {
    } // Prevents instantiation

    public static boolean validate(String pan) {
        if (pan.length() != 10) {
            return false;
        }
        String regex = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
        return pan.matches(regex);
    }
}
