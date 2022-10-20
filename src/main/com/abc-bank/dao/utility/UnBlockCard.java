package dao.utility;

import java.sql.Connection;

public class UnBlockCard {
    private UnBlockCard() {
    } // private constructor

    public static void unBlockCard(String account_number){
        try{
            Connection conn = dao.connection.Connection.getConnection("customers");
            String query = "UPDATE cards SET status = ? WHERE account_number = ?";
            java.sql.PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "active");
            ps.setString(2, account_number);
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
