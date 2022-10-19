package dao.balance;

import com.google.gson.JsonObject;

import java.sql.Connection;

public class AccountBalance {
    private AccountBalance() {
    } // private constructor

    public static long getBalance(String accountNumber, JsonObject json) {
        long balance = 0;
        try {
            Connection con = dao.connection.Connection.getConnection("customers");
            java.sql.PreparedStatement ps = con.prepareStatement("select balance from balance where account_number = ?");
            ps.setString(1, accountNumber);
            java.sql.ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                balance = rs.getLong("balance");
            } else {
                json.addProperty("message", "Account number not found");
                json.addProperty("status", "404");
            }
        }catch(Exception e){
            System.out.println(e + " in AccountBalance for dao.balance");
            json.addProperty("message", "Internal server error");
            json.addProperty("status", "500");
        }
        return balance;
    }
}
