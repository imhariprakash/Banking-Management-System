package dao.admin;

import com.google.gson.JsonObject;

import java.sql.Connection;

public class GetReviewForm {
    private GetReviewForm() {
    } // Prevent instantiation

    public static JsonObject getReviewForm(String verified_by) {
        JsonObject reviewForm = new JsonObject();
        try{
            Connection conn = dao.connection.Connection.getConnection("applications");
            String query = "SELECT * FROM applications ORDER BY applied_on LIMIT 1";
            java.sql.PreparedStatement stmt = conn.prepareStatement(query);
            java.sql.ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                reviewForm.addProperty("aadhar", rs.getString("aadhar"));
                reviewForm.addProperty("address", rs.getString("address"));
                reviewForm.addProperty("applied_on", rs.getString("applied_on"));
                reviewForm.addProperty("cust_name", rs.getString("cust_name"));
                reviewForm.addProperty("dob", rs.getString("dob"));
                reviewForm.addProperty("email", rs.getString("email"));
                reviewForm.addProperty("father_name", rs.getString("father_name"));
                reviewForm.addProperty("notes", rs.getString("notes"));
                reviewForm.addProperty("pan", rs.getString("pan"));
                reviewForm.addProperty("phone", rs.getString("phone"));
                reviewForm.addProperty("pincode", rs.getString("pincode"));
            }
            query = "UPDATE applications SET status = 'taken', verified_by = ? WHERE email = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, verified_by);
            stmt.setString(2, reviewForm.get("email").getAsString());

            stmt.executeUpdate();
            conn.close();
            return reviewForm;
        }catch(Exception e){
            e.printStackTrace();
        }

        return reviewForm;

    }

}
