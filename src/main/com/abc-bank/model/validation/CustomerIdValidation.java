package model.validation;

public class CustomerIdValidation {
    private CustomerIdValidation() {
    }

    public static boolean isValidCustomerId(String customerId) {
        return customerId.matches("^[1-9]{1}[0-9]{9}$");
    }
}
