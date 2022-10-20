package dao.pojo;

public class Transaction {
    private String amount;
    private String description;
    private String from_account_number;
    private String t_time;
    private String to_account_number;
    private String transaction_id;
    private String transaction_source;
    private String getTransaction_source_id;
    private String transaction_type;

    public Transaction(String amount, String description, String from_account_number, String t_time, String to_account_number, String transaction_id, String transaction_source, String getTransaction_source_id, String transaction_type) {
        this.amount = amount;
        this.description = description;
        this.from_account_number = from_account_number;
        this.t_time = t_time;
        this.to_account_number = to_account_number;
        this.transaction_id = transaction_id;
        this.transaction_source = transaction_source;
        this.getTransaction_source_id = getTransaction_source_id;
        this.transaction_type = transaction_type;
    }

    public Transaction() {
        this.amount = null;
        this.description = null;
        this.from_account_number = null;
        this.t_time = null;
        this.to_account_number = null;
        this.transaction_id = null;
        this.transaction_source = null;
        this.getTransaction_source_id = null;
        this.transaction_type = null;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFrom_account_number() {
        return from_account_number;
    }

    public void setFrom_account_number(String from_account_number) {
        this.from_account_number = from_account_number;
    }

    public String getT_time() {
        return t_time;
    }

    public void setT_time(String t_time) {
        this.t_time = t_time;
    }

    public String getTo_account_number() {
        return to_account_number;
    }

    public void setTo_account_number(String to_account_number) {
        this.to_account_number = to_account_number;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getTransaction_source() {
        return transaction_source;
    }

    public void setTransaction_source(String transaction_source) {
        this.transaction_source = transaction_source;
    }

    public String getGetTransaction_source_id() {
        return getTransaction_source_id;
    }

    public void setGetTransaction_source_id(String getTransaction_source_id) {
        this.getTransaction_source_id = getTransaction_source_id;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }


}
