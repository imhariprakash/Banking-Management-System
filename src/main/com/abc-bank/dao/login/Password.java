package dao.login;

import com.google.gson.JsonObject;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Password {
    public static void updatePassword(String customer_id, String password, JsonObject responseJson){
        try{
            Connection connection = dao.connection.Connection.getConnection("customers");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE netbanking SET password = ? WHERE customer_id = ?");
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, customer_id);
            preparedStatement.executeUpdate();
            responseJson.addProperty("status", "200");
            responseJson.addProperty("message", "Password updated successfully");
        }catch (Exception e){
            e.printStackTrace();
            responseJson.addProperty("status", "500");
            responseJson.addProperty("message", "Internal server error");
        }
    }
}
