package dao.utility;

import java.sql.Connection;

public class GetBalance {
    private GetBalance() {
    } // private constructor

    public static String getBalance(String customer_id){
        try{
            String account_number = GetAccountNumber.getAccountNumber(customer_id);

            Connection conn = dao.connection.Connection.getConnection("customers");
            String query = "SELECT balance FROM balance WHERE account_number = ?";
            java.sql.PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, account_number);
            java.sql.ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("balance");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
