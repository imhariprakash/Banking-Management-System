package model.transactions;

import com.google.gson.JsonObject;

public class Transaction {
    public static void send(JsonObject jsonRequest, JsonObject json) {
        try{
            String from_account = jsonRequest.get("from_account").getAsString();
            String to_account = jsonRequest.get("to_account").getAsString();
            String amount = jsonRequest.get("amount").getAsString();
            String description = jsonRequest.get("description").getAsString();

            dao.transactions.UpdateBalance.subtractBalance(from_account, Long.parseLong(amount), json);
            if (json.get("status").getAsString().equals("500")) {
                dao.transactions.rollbacks.NetbankingTransaction.rollbackFrom(from_account, Long.parseLong(amount));
                return;
            }
            String uuid = java.util.UUID.randomUUID().toString();

            dao.transactions.UpdateDebit.debit(from_account, Long.parseLong(amount), uuid, json);

            if(json.get("status").getAsString().equals("500")){
                dao.transactions.rollbacks.NetbankingTransaction.rollbackFrom(from_account, Long.parseLong(amount));
                dao.transactions.rollbacks.NetbankingTransaction.rollbackTo(to_account, Long.parseLong(amount));
                return;
            }

            dao.transactions.netbanking.Transaction.transact(from_account, to_account, Long.parseLong(amount), description, json);

        }catch(Exception e){
            e.printStackTrace();
            json.addProperty("status", "400");
            json.addProperty("message", "Bad Request");
        }
    }
}
