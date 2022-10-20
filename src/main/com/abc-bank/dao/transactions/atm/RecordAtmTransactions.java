package dao.transactions.atm;

import java.sql.Connection;

public class RecordAtmTransactions {
    private RecordAtmTransactions() {
    } // private constructor

    public static void addAtmTransactions(String account_number, String amount, String atm_id, String type){
        try{
            Connection conn = dao.connection.Connection.getConnection("transactions");
            String query = "INSERT INTO atm (account_number, amount, atm_id, type, time) VALUES (?, ?, ?, ?, ?)";
            java.sql.PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, account_number);
            ps.setString(2, amount);
            ps.setString(3, atm_id);
            ps.setString(4, type);
            ps.setTimestamp(5, model.utilities.GetTimeStamp.getTimeStamp());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
