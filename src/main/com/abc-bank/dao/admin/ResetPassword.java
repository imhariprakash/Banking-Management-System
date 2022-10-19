package dao.admin;

import com.google.gson.JsonObject;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ResetPassword {
    private ResetPassword() {
    } // Prevents instantiation

    public static void reset(String username, String password, JsonObject json) {
        try {
            Connection connection = dao.connection.Connection.getConnection("employee");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE admin SET password = ? WHERE customer_id = ?");
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
            json.addProperty("status", "200");
            json.addProperty("message", "Password reset successfully");
        } catch (Exception e) {
            json.addProperty("status", "500");
            json.addProperty("message", "Internal server error");
        }
    }
}
