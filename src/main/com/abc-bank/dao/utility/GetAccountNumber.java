package dao.utility;

import java.sql.Connection;

public class GetAccountNumber {
    private GetAccountNumber() {
    } // private constructor

    public static String getAccountNumber(String customer_id){
        try{
            Connection con = dao.connection.Connection.getConnection("customers");
            String query = "SELECT account_number FROM accounts WHERE customer_id = ?";
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, customer_id);
            java.sql.ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString("account_number");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
