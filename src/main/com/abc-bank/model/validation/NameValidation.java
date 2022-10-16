package model.validation;

public class NameValidation {
    private NameValidation() {
    } // Prevents instantiation

    public static boolean validate(String name){
        return name.matches("^[a-zA-Z ]*$");
    }
}
