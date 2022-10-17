package dao.transactions.rollbacks;

public class AtmDeposit {
    private AtmDeposit() {
    } // private constructor

    public static void update(String accountNumber, long amount) {
        try {
            java.sql.Connection con = dao.Connection.Connection.getConnection("transactions");
            java.sql.PreparedStatement ps = con.prepareStatement("INSERT INTO atm_deposit_rollbacks (account_number, amount, t_time) VALUES (?, ?, ?)");
            ps.setString(1, accountNumber);
            ps.setLong(2, amount);
            ps.setLong(3, System.currentTimeMillis());
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e + " in Rollbacks.AtmDeposit for dao.transactions");
        }
    }
}
