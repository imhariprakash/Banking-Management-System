package dao.utility;

import java.sql.Connection;

public class GetCustomerName {
    private GetCustomerName() {
    } // private constructor

    public static String getCustomerName(String customer_id){
        try{
            Connection conn = dao.connection.Connection.getConnection("customers");
            String query = "SELECT cust_name FROM customers WHERE customer_id = ?";
            java.sql.PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, customer_id);
            java.sql.ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString("cust_name");
            }
        }catch(Exception e){
            System.out.println(e + " in GetCustomerName");
        }
        return null;
    }
}
