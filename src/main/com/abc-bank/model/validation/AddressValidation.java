package model.validation;

public class AddressValidation {
    private AddressValidation() {
    } // Prevents instantiation

    public static boolean validate(String address){
        if(address.length() == 0){
            return false;
        }
        return (address.matches("^[A-Za-z0-9\s,'-]*$"));
    }
}
