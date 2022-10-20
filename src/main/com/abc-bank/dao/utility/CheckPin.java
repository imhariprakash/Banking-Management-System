package dao.utility;

public class CheckPin {
    private CheckPin() {
    } // private constructor

    public static boolean checkPin(String card_number, String pin, com.google.gson.JsonObject jsonResponse){
        try{

            java.sql.Connection conn = dao.connection.Connection.getConnection("customers");
            String query = "SELECT pin FROM cards WHERE card_number = ?";
            java.sql.PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, card_number);
            java.sql.ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if(rs.getString("pin").equals(pin)){
                    jsonResponse.addProperty("message", "success");
                    jsonResponse.addProperty("status", 200);
                    return true;
                }else{
                    jsonResponse.addProperty("message", "Invalid card number or pin");
                    jsonResponse.addProperty("status", 400);
                    return false;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        jsonResponse.addProperty("message", "Invalid credentials - check card number and pin");
        jsonResponse.addProperty("status", 400);
        return false;
    }
}
