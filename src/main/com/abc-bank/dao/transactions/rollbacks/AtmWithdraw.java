package dao.transactions.rollbacks;

import com.google.gson.JsonObject;

import java.sql.Connection;

public class AtmWithdraw {
    private AtmWithdraw() {
    } // private constructor

    public static void update(String accountNumber, long amount, JsonObject response) {
        try{
            Connection con = dao.Connection.Connection.getConnection("transactions");
            java.sql.PreparedStatement ps = con.prepareStatement("INSERT INTO atm_withdraw_rollbacks (account_number, amount, t_time) VALUES (?, ?, ?)");
            ps.setString(1, accountNumber);
            ps.setLong(2, amount);
            ps.setLong(3, System.currentTimeMillis());
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e + " in Rollbacks.AtmWithdraw for dao.transactions");
            response.addProperty("message", "Internal server error");
            response.addProperty("status", "500");
        }
    }
}
