package dao.transactions;

import com.google.gson.JsonObject;

public class UpdateBalance {
    private UpdateBalance() {
    } // private constructor

    public static void addBalance(String accountNumber, long amount, JsonObject response) {
        try {
            // update balance
            java.sql.Connection con = dao.connection.Connection.getConnection("customers");
            java.sql.PreparedStatement ps = con.prepareStatement("update balance set balance = balance + ? where account_number = ?");
            ps.setLong(1, amount);
            ps.setString(2, accountNumber);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e + " in UpdateBalance for dao.transactions");
            response.addProperty("message", "Internal server error");
            response.addProperty("status", "500");
        }
    }

    public static void subtractBalance(String accountNumber, long amount, JsonObject response) {
        try {
            // update balance
            java.sql.Connection con = dao.connection.Connection.getConnection("customers");
            java.sql.PreparedStatement ps = con.prepareStatement("update balance set balance = balance - ? where account_number = ?");
            ps.setLong(1, amount);
            ps.setString(2, accountNumber);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e + " in UpdateBalance for dao.transactions");
            response.addProperty("message", "Internal server error");
            response.addProperty("status", "500");
        }
    }
}
