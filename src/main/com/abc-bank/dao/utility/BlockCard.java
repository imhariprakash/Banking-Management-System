package dao.utility;

import java.sql.Connection;

public class BlockCard {
    private BlockCard() {
    } // private constructor

    public static void blockCard(String account_number){
        try{
            Connection conn = dao.connection.Connection.getConnection("customers");
            String query = "UPDATE cards SET status = ? WHERE account_number = ?";
            java.sql.PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "blocked");
            ps.setString(2, account_number);
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
