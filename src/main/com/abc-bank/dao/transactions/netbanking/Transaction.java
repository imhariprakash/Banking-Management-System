package dao.transactions.netbanking;

import com.google.gson.JsonObject;

public class Transaction {
    private Transaction() {
    } // private constructor

    public static void transact(String from_account, String to_account, long amount, JsonObject response) {
        // update balance
        dao.transactions.UpdateBalance.subtractBalance(from_account, amount, response);
        if (response.get("status").getAsString().equals("500")) {
            dao.transactions.rollbacks.NetbankingTransaction.rollbackFrom(from_account, amount);
            return;
        }
        java.util.UUID uuid = java.util.UUID.randomUUID();
        dao.transactions.UpdateDebit.debit(from_account, amount, uuid.toString(), response);

        dao.transactions.UpdateBalance.addBalance(to_account, amount, response);
        if (response.get("status").getAsString().equals("500")) {
            dao.transactions.rollbacks.NetbankingTransaction.rollbackTo(to_account, amount);
            return;
        }

        // update credit
        dao.transactions.UpdateCredit.updateCredit(to_account, amount, "netbanking", uuid.toString(), response);

    }
}
