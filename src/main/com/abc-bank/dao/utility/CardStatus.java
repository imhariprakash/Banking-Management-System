package dao.utility;

import java.sql.Connection;

public class CardStatus {
    private CardStatus() {
    } // private constructor

    public static boolean isBlocked(String card_number){
        try{
            Connection conn = dao.connection.Connection.getConnection("customers");
            String query = "SELECT status FROM cards WHERE card_number = ?";
            java.sql.PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, card_number);
            java.sql.ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("status").equals("blocked");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
