package dao.transactions.rollbacks;

import java.sql.Connection;

public class NetbankingTransaction {
    private NetbankingTransaction() {
    } // private constructor


    public static void rollbackFrom(String from_account, long amount) {
        try{
            Connection con = dao.connection.Connection.getConnection("transactions");
            java.sql.PreparedStatement ps = con.prepareStatement("insert into netbanking_withdraw_rollback (account_number, amount, t_time) values (?, ?, ?)");
            ps.setString(1, from_account);
            ps.setLong(2, amount);
            ps.setLong(3, System.currentTimeMillis());
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e + " in NetbankingTransaction for dao.transactions.Rollbacks");
        }
    }

    public static void rollbackTo(String to_account, long amount) {
        try{
            Connection con = dao.connection.Connection.getConnection("transactions");
            java.sql.PreparedStatement ps = con.prepareStatement("insert into netbanking_deposit_rollback (account_number, amount, t_time) values (?, ?, ?)");
            ps.setString(1, to_account);
            ps.setLong(2, amount);
            ps.setLong(3, System.currentTimeMillis());
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e + "in NetbankingTransaction for dao.transactions.Rollback");
        }
    }
}
