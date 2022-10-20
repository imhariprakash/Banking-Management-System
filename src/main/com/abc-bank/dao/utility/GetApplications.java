package dao.utility;

import dao.pojo.Application;

import java.sql.Connection;
import java.util.ArrayList;

public class GetApplications {
    private GetApplications() {
    } // private constructor

    public static ArrayList<Application> getApplications(){
        try{
            Connection conn = dao.connection.Connection.getConnection("applications");
            String query = "SELECT * FROM applications";
            java.sql.PreparedStatement ps = conn.prepareStatement(query);
            java.sql.ResultSet rs = ps.executeQuery();
            ArrayList<Application> applications = new ArrayList<>();
            while (rs.next()) {
                Application application = new Application();
                application.setCust_name(rs.getString("cust_name"));
                application.setEmail(rs.getString("email"));
                application.setPhone(rs.getString("phone"));
                application.setApplied_on(rs.getDate("applied_on").toString());
                application.setStatus(rs.getString("status"));
                applications.add(application);
            }
            return applications;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
