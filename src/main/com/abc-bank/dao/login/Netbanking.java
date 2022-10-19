package dao.login;


import com.google.gson.JsonObject;

import java.sql.Connection;

public class Netbanking {
    private Netbanking() {
    } // Prevent instantiation

    public static String getPassword(String username, JsonObject response) {
        String password = null;
        try{
            Connection con = dao.connection.Connection.getConnection("customers");
            java.sql.PreparedStatement ps = con.prepareStatement("select password from netbanking where customer_id = ?");
            ps.setString(1, username);
            java.sql.ResultSet rs = ps.executeQuery();
            if(rs.next()){
                password = rs.getString("password");
            }
            con.close();
        }catch(Exception e){
            System.out.println(e + " in getPassword for Netbanking dao");
            response.addProperty("status", "500");
            response.addProperty("message", "Internal server error");
            return null;
        }
        return password;
    }

    public static void resetPassword(String username, String password, JsonObject response) {
        try{
            Connection con = dao.connection.Connection.getConnection("customers");
            java.sql.PreparedStatement ps = con.prepareStatement("update netbanking set password = ? where username = ?");
            ps.setString(1, password);
            ps.setString(2, username);
            ps.executeUpdate();
            con.close();
        }catch(Exception e){
            System.out.println(e + " in resetPassword for Netbanking dao");
            response.addProperty("status", "500");
            response.addProperty("message", "Internal server error");
        }
    }
}
