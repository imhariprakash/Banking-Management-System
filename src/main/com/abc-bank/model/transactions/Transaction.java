package model.transactions;

import com.google.gson.JsonObject;

public class Transaction {
    public static void send(JsonObject jsonRequest, JsonObject json) {
        try{
            String from_account = jsonRequest.get("from_account").getAsString();
            String to_account = jsonRequest.get("to_account").getAsString();
            String amount = jsonRequest.get("amount").getAsString();
            String description = jsonRequest.get("description").getAsString();

            // debit and credit will be done - roll back in case of failure !

            dao.transactions.netbanking.Transaction.transact(from_account, to_account, Long.parseLong(amount), description, json);

        }catch(Exception e){
            e.printStackTrace();
            json.addProperty("status", "400");
            json.addProperty("message", "Bad Request");
        }
    }
}
