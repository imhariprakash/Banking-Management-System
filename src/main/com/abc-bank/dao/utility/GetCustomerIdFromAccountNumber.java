package dao.utility;

public class GetCustomerIdFromAccountNumber {
    private GetCustomerIdFromAccountNumber() {
    } // private constructor

    public static String getCustomerId(String account_number) {
        try{
            java.sql.Connection connection = dao.connection.Connection.getConnection("customers");
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement("SELECT customer_id FROM accounts WHERE account_number = ?");
            preparedStatement.setString(1, account_number);
            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getString("customer_id");
            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
