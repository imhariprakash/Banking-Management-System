package dao.transactions;

import com.google.gson.JsonObject;

public class UpdateTransactions {
    private UpdateTransactions() {
    } // private constructor

    public static void addTransaction(String from_account_number, String to_account_number, double amount, String source_id, String source, String description, String type, JsonObject response) {
        try {
            // update transactions - dual entry system log
            java.sql.Connection con = dao.connection.Connection.getConnection("transactions");
            java.sql.PreparedStatement ps = con.prepareStatement("insert into transactions (to_account_number, t_time, amount, transaction_source, transaction_source_id, transaction_type, description, from_account_number) values (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, to_account_number);
            ps.setTimestamp(2, model.utilities.GetTimeStamp.getTimeStamp());
            ps.setDouble(3, amount);
            ps.setString(4, source);
            ps.setString(5, source_id);
            ps.setString(6, type);
            ps.setString(7, description);
            ps.setString(8, from_account_number);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            response.addProperty("message", "Internal server error");
            response.addProperty("status", "500");
        }
    }
}
