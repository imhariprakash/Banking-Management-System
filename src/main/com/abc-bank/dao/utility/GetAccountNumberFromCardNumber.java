package dao.utility;

import com.google.gson.JsonObject;

import java.sql.Connection;

public class GetAccountNumberFromCardNumber {
    private GetAccountNumberFromCardNumber() {
    } // private constructor

    public static String getAccountNumber(String cardNumber, JsonObject jsonResponse) {
        try{
            Connection conn = dao.connection.Connection.getConnection("customers");
            String query = "SELECT account_number FROM cards WHERE card_number = ?";
            java.sql.PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, cardNumber);
            java.sql.ResultSet rs = ps.executeQuery();
            if(rs.next()){
                jsonResponse.addProperty("status", "200");
                jsonResponse.addProperty("message", "success");
                return rs.getString("account_number");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        jsonResponse.addProperty("status", "400");
        jsonResponse.addProperty("message", "Invalid credentials - verify card number and pin");
        return null;
    }
}
