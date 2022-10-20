package dao.utility;

public class EnoughBalance {
    private EnoughBalance() {
    } // private constructor

    public static boolean isEnough(String from_account, double amount) {
        double from_account_balance = Double.parseDouble(GetBalanceFromAccountNumber.getBalance(from_account));
        if (from_account_balance >= amount) {
            return true;
        } else {
            return false;
        }
    }
}
