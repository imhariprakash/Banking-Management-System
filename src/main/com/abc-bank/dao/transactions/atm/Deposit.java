package dao.transactions.atm;

import com.google.gson.JsonObject;

public class Deposit {
    private Deposit() {
    } // private constructor

    public static void deposit(String from_account_number, String to_account_number, double amount, String atmId, String description, JsonObject response) {
        // update balance

        dao.transactions.UpdateBalance.addBalance(to_account_number, amount, response);

        if(response.get("status").getAsString().equals("500")){
            dao.transactions.rollbacks.AtmDeposit.update(to_account_number, amount);
            return;
        }

        // update transactions - dual entry system log

        dao.transactions.UpdateTransactions.addTransaction(from_account_number, to_account_number, amount, atmId, "atm", description, "credit", response);

        if(response.get("status").getAsString().equals("500")){
            System.out.println("Rolling back transaction");
            dao.transactions.UpdateBalance.subtractBalance(to_account_number, amount, response);
            dao.transactions.rollbacks.AtmDeposit.update(to_account_number, amount);
            return;
        }

        // update credit account - dual entry system log

        dao.transactions.UpdateCredit.updateCredit(to_account_number, amount, "atm", atmId, response);

        dao.transactions.atm.RecordAtmTransactions.addAtmTransactions(to_account_number, String.valueOf(amount), atmId, "credit");
    }
}
