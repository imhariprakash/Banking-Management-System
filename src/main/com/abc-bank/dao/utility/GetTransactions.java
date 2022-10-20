package dao.utility;

public class GetTransactions {
    private GetTransactions() {
    } // private constructor


    public static java.util.ArrayList<dao.pojo.Transaction> getTransactions(String account_number){
        java.util.ArrayList<dao.pojo.Transaction> transactions = new java.util.ArrayList<>();
        try{

            java.sql.Connection conn = dao.connection.Connection.getConnection("transactions");
            String query = "SELECT * FROM transactions WHERE from_account_number = ? OR to_account_number = ? ORDER BY t_time DESC";
            java.sql.PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, account_number);
            ps.setString(2, account_number);
            java.sql.ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                transactions.add(new dao.pojo.Transaction());
                int index = transactions.size() - 1;
                transactions.get(index).setAmount(rs.getString("amount"));
                transactions.get(index).setDescription(rs.getString("description"));
                transactions.get(index).setFrom_account_number(rs.getString("from_account_number"));
                transactions.get(index).setT_time(rs.getString("t_time"));
                transactions.get(index).setTo_account_number(rs.getString("to_account_number"));
                transactions.get(index).setTransaction_id(rs.getString("transaction_id"));
                transactions.get(index).setTransaction_source(rs.getString("transaction_source"));
                transactions.get(index).setGetTransaction_source_id(rs.getString("transaction_source_id"));
                transactions.get(index).setTransaction_type(rs.getString("transaction_type"));
            }

            return transactions;

        }catch (Exception e){
            e.printStackTrace();
        }
        return transactions;
    }
}
