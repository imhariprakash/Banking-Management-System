package dao.transactions;

import com.google.gson.JsonObject;

public class UpdateTransactions {
    private UpdateTransactions() {
    } // private constructor

    public static void addTransaction(String accountNumber, long amount, String source_id, String source, String description, JsonObject response) {
        try {
            // update transactions - dual entry system log
            java.sql.Connection con = dao.connection.Connection.getConnection("transactions");
            java.sql.PreparedStatement ps = con.prepareStatement("insert into transactions (account_number, t_time, amount, transaction_id, transaction_source, transaction_source_id, transaction_type, description) values (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, accountNumber);
            ps.setLong(2, System.currentTimeMillis());
            ps.setLong(3, amount);
            ps.setString(4, java.util.UUID.randomUUID().toString());
            ps.setString(5, source);
            ps.setString(6, source_id);
            ps.setString(7, "deposit");
            ps.setString(8, description);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e + " in UpdateTransactions for dao.transactions");
            response.addProperty("message", "Internal server error");
            response.addProperty("status", "500");
        }
    }
}
