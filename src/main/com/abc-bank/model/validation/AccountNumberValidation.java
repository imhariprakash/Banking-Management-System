package model.validation;

public class AccountNumberValidation {
    private AccountNumberValidation() {
    }

    public static boolean isValidAccountNumber(String accountNumber) {
        if (accountNumber.length() != 10) {
            return false;
        }
        for (int i = 0; i < 10; i++) {
            if (!Character.isDigit(accountNumber.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
