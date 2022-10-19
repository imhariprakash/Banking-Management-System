package dao.transactions.rollbacks;

import java.sql.Connection;
import java.sql.Timestamp;
import model.utilities.GetTimeStamp;

public class NetbankingTransaction {
    private NetbankingTransaction() {
    } // private constructor


    public static void rollbackFrom(String from_account, long amount) {
        try{
            Connection con = dao.connection.Connection.getConnection("transactions");
            java.sql.PreparedStatement ps = con.prepareStatement("insert into netbanking_withdraw_rollbacks (account_number, amount, t_time) values (?, ?, ?)");
            ps.setString(1, from_account);
            ps.setLong(2, amount);
            ps.setTimestamp(3, GetTimeStamp.getTimeStamp());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void rollbackTo(String to_account, long amount) {
        try{
            Connection con = dao.connection.Connection.getConnection("transactions");
            java.sql.PreparedStatement ps = con.prepareStatement("insert into netbanking_deposit_rollbacks (account_number, amount, t_time) values (?, ?, ?)");
            ps.setString(1, to_account);
            ps.setLong(2, amount);
            ps.setTimestamp(3, GetTimeStamp.getTimeStamp());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
