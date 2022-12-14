package dao.transactions;

import com.google.gson.JsonObject;

import java.sql.Connection;

public class UpdateDebit {
    private UpdateDebit() {
    } // private constructor

    public static void debit(String accountNumber, double amount, String atmId, JsonObject response) {
        // update balance

        try{
            Connection con = dao.connection.Connection.getConnection("transactions");
            java.sql.PreparedStatement ps = con.prepareStatement("INSERT INTO debit(account_number, debit_amount, debit_time, debit_source, debit_source_id) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, accountNumber);
            ps.setDouble(2, amount);
            ps.setTimestamp(3, model.utilities.GetTimeStamp.getTimeStamp());
            ps.setString(4, "atm");
            ps.setString(5, atmId);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e + " in UpdateDebit for dao.transactions");
            response.addProperty("message", "Internal server error");
            response.addProperty("status", "500");
        }
    }
}
