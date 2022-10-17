package dao.transactions;

import com.google.gson.JsonObject;

import java.sql.Connection;

public class UpdateCredit {
    private UpdateCredit() {
    } // private constructor

    public static void updateCredit(String accountNumber, long amount, String source, String sourceId, JsonObject response) {
        try {
            Connection con = dao.Connection.Connection.getConnection("transactions");
            java.sql.PreparedStatement ps = con.prepareStatement("insert into credit (account_number, credit_amount, credit_date, credit_source, credit_source_id) values (?, ?, ?, ?, ?)");
            ps.setString(1, accountNumber);
            ps.setLong(2, amount);
            ps.setLong(3, System.currentTimeMillis());
            ps.setString(4, source);
            ps.setString(5, sourceId);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e + " in UpdateCredit for dao.transactions");
            response.addProperty("message", "Internal server error");
            response.addProperty("status", "500");
        }
    }

}
