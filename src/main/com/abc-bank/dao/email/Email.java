package dao.email;

import dao.connection.Connection;

public class Email {
    private Email() {
    } // Prevents instantiation

    public static String getEmail() {
        String email = null;
        try {
            java.sql.Connection con = Connection.getConnection("otp");
            String query = "select email from mail_credentials";
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            java.sql.ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                email = rs.getString(1);
            }
        }catch (Exception e){
            // TODO: handle exception
        }
        return email;
    }

    public static String getPassword() {
        String password = null;
        try {
            java.sql.Connection con = Connection.getConnection("otp");
            String query = "select password from mail_credentials";
            java.sql.PreparedStatement ps = con.prepareStatement(query);
            java.sql.ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                password = rs.getString(1);
            }
        }catch (Exception e){
            // TODO: handle exception
        }
        return password;
    }
}
