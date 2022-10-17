package dao.transactions;

import com.google.gson.JsonObject;

import java.sql.Connection;

public class UpdateDebit {
    private UpdateDebit() {
    } // private constructor

    public static void debit(String accountNumber, long amount, String atmId, JsonObject response) {
        // update balance

        try{
            Connection con = dao.Connection.Connection.getConnection("transactions");
            java.sql.PreparedStatement ps = con.prepareStatement("INSERT INTO debit(account_number, debit_amount, debit_date, debit_source, debit_source_id) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, accountNumber);
            ps.setLong(2, amount);
            ps.setLong(3, System.currentTimeMillis());
            ps.setString(4, "atm");
            ps.setString(5, atmId);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e + " in UpdateDebit for dao.transactions");
            response.addProperty("message", "Internal server error");
            response.addProperty("status", "500");
        }
    }
}
