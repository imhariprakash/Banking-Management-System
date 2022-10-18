package dao.admin;

import com.google.gson.JsonObject;

import java.sql.Connection;

public class Login {
    public static boolean login(String username, String password, JsonObject response) {
        try{
            Connection connection = dao.Connection.Connection.getConnection("employee");
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement("SELECT password FROM admin WHERE username = ?");
            preparedStatement.setString(1, username);
            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                if(resultSet.getString("password").equals(password)) {
                    return true;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            response.addProperty("status", "500");
            response.addProperty("message", "Internal Server Error");
        }
        return false;
    }
}
