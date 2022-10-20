package dao.transactions.atm;

import com.google.gson.JsonObject;

public class Withdraw {
    private Withdraw() {
    } // private constructor

    public static void withdraw(String accountNumber, double amount, String atmId, String description, JsonObject response) {
        // update balance

        dao.transactions.UpdateBalance.subtractBalance(accountNumber, amount, response);

        if(response.get("status").getAsString().equals("500")){
            dao.transactions.rollbacks.AtmWithdraw.update(accountNumber, amount, response);
            return;
        }

        // update transactions - dual entry system log
        // withdraw - only self possible - so give here twice
        dao.transactions.UpdateTransactions.addTransaction(accountNumber, accountNumber, amount, atmId, "atm", description, "debit", response);

        if(response.get("status").getAsString().equals("500")){
            System.out.println("Rolling back transaction");
            dao.transactions.UpdateBalance.addBalance(accountNumber, amount, response);
            dao.transactions.rollbacks.AtmWithdraw.update(accountNumber, amount, response);
            dao.transactions.atm.RecordAtmTransactions.addAtmTransactions(accountNumber, String.valueOf(amount), atmId, "debit");
            return;
        }

        // update debit account - dual entry system log

        dao.transactions.UpdateDebit.debit(accountNumber, amount, atmId, response);
    }
}
