package dao.transactions.atm;

import com.google.gson.JsonObject;

public class Deposit {
    private Deposit() {
    } // private constructor

    public static void deposit(String accountNumber, long amount, String atmId, String description, JsonObject response) {
        // update balance

        dao.transactions.UpdateBalance.addBalance(accountNumber, amount, response);

        if(response.get("status").getAsString().equals("500")){
            dao.transactions.rollbacks.AtmDeposit.update(accountNumber, amount);
            return;
        }

        // update transactions - dual entry system log

        dao.transactions.UpdateTransactions.addTransaction(accountNumber, amount, atmId, "atm", description, "deposit", response);

        if(response.get("status").getAsString().equals("500")){
            System.out.println("Rolling back transaction");
            dao.transactions.UpdateBalance.subtractBalance(accountNumber, amount, response);
            dao.transactions.rollbacks.AtmDeposit.update(accountNumber, amount);
            return;
        }

        // update credit account - dual entry system log

        dao.transactions.UpdateCredit.updateCredit(accountNumber, amount, "atm", atmId, response);
    }
}
