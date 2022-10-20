package dao.transactions;

import com.google.gson.JsonObject;
import dao.utility.GetBalanceFromAccountNumber;

public class UpdateBalance {
    private UpdateBalance() {
    } // private constructor

    public static void addBalance(String accountNumber, double amount, JsonObject response) {
        try {
            // update balance
            java.sql.Connection con = dao.connection.Connection.getConnection("customers");
            java.sql.PreparedStatement ps = con.prepareStatement("update balance set balance = balance + ? where account_number = ?");
            ps.setDouble(1, amount);
            ps.setString(2, accountNumber);
            ps.executeUpdate();
            response.addProperty("status", "200");
            response.addProperty("message", "Balance updated successfully");

            String email = dao.utility.GetEmailFromAccountNumber.getEmail(accountNumber, response);
            String subject = "ABC Bank - Balance Updated";
            String body = "Your account balance has been credited by " + amount + " Rs. Your new balance is " + GetBalanceFromAccountNumber.getBalance(accountNumber) + " Rs.";
            model.mailings.Email.send(email, subject, body);
        }catch(Exception e){
            System.out.println(e + " in UpdateBalance for dao.transactions");
            response.addProperty("message", "Internal server error");
            response.addProperty("status", "500");
        }
    }

    public static void subtractBalance(String accountNumber, double amount, JsonObject response) {
        try {
            // update balance
            java.sql.Connection con = dao.connection.Connection.getConnection("customers");
            java.sql.PreparedStatement ps = con.prepareStatement("update balance set balance = balance - ? where account_number = ?");
            ps.setDouble(1, amount);
            ps.setString(2, accountNumber);
            ps.executeUpdate();
            response.addProperty("status", "200");
            response.addProperty("message", "Balance updated successfully");

            String email = dao.utility.GetEmailFromAccountNumber.getEmail(accountNumber, response);
            String subject = "ABC Bank - Balance Updated";
            String body = "Your account balance has been debited by " + amount + " Rs. Your new balance is " + GetBalanceFromAccountNumber.getBalance(accountNumber) + " Rs.";
            model.mailings.Email.send(email, subject, body);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e + " in UpdateBalance for dao.transactions");
            response.addProperty("message", "Internal server error");
            response.addProperty("status", "500");
        }
    }
}
