package dao.connection;

public class Connection {
    private Connection() {
    } // Prevents instantiation

    public static java.sql.Connection getConnection(String database) {
        java.sql.Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, "root", "imhari");
        } catch (Exception e) {
            // TODO: handle exception
        }
        return con;
    }
}
