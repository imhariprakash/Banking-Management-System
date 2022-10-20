package dao.transactions;

import com.google.gson.JsonObject;

import java.sql.Connection;

public class UpdateCredit {
    private UpdateCredit() {
    } // private constructor

    public static void updateCredit(String accountNumber, double amount, String source, String sourceId, JsonObject response) {
        try {
            Connection con = dao.connection.Connection.getConnection("transactions");
            java.sql.PreparedStatement ps = con.prepareStatement("insert into credit (account_number, credit_amount, credit_time, credit_source, credit_source_id) values (?, ?, ?, ?, ?)");
            ps.setString(1, accountNumber);
            ps.setDouble(2, amount);
            ps.setTimestamp(3, model.utilities.GetTimeStamp.getTimeStamp());
            ps.setString(4, source);
            ps.setString(5, sourceId);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e + " in UpdateCredit for dao.transactions");
            response.addProperty("message", "Internal server error");
            response.addProperty("status", "500");
        }
    }

}
