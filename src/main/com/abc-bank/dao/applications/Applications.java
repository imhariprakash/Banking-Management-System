package dao.applications;

import java.sql.Connection;

public class Applications {
    private Applications() {
    } // Prevents instantiation

    public static String getAppliedOn(String email) {
        try{
            Connection connection = dao.Connection.Connection.getConnection("applications");
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement("SELECT applied_on FROM applications WHERE email = ?");
            preparedStatement.setString(1, email);
            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getString("applied_on");
            }
        }catch (Exception e){
            System.out.println(e + " in getAppliedOn for dao.applications");
        }
        return null;
    }

    public static void deleteApplication(String email) {
        try{
            Connection connection = dao.Connection.Connection.getConnection("applications");
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM applications WHERE email = ?");
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e + " in deleteApplication for dao.applications");
        }
    }

    public static void updateApplication_log(String email, String applied_on, String status, String verified_by) {
        try{
            Connection connection = dao.Connection.Connection.getConnection("applications");
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement("UPDATE application_log SET status = ?, verified_by = ?, verified_on = ? WHERE email = ? AND applied_on = ?");
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, verified_by);
            preparedStatement.setString(3, java.time.LocalDate.now().toString());
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, applied_on);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e + " in updateApplication_log for dao.applications");
        }
    }
}
