package dao.utility;

import com.google.gson.JsonObject;

import java.sql.Connection;

public class GetEmail {
    private GetEmail() {
    } // private constructor

    public static String getEmail(String customer_id, JsonObject responseJson) {
        try{
            Connection connection = dao.Connection.Connection.getConnection("customers");
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement("SELECT email FROM customers WHERE customer_id = ?");
            preparedStatement.setString(1, customer_id);
            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getString("email");
            }else{
                responseJson.addProperty("status", "400");
                responseJson.addProperty("message", "Invalid customer_id");
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            responseJson.addProperty("status", "400");
            responseJson.addProperty("message", "Invalid Request");
            return null;
        }
    }
}
