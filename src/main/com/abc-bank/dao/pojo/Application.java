package dao.pojo;

public class Application {
    private String cust_name;
    private String email;
    private String phone;
    private String applied_on;
    private String status;

    public Application(String cust_name, String email, String phone, String applied_on, String status) {
        this.cust_name = cust_name;
        this.email = email;
        this.phone = phone;
        this.applied_on = applied_on;
        this.status = status;
    }

    public Application() {
    } // default constructor


    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getApplied_on() {
        return applied_on;
    }

    public void setApplied_on(String applied_on) {
        this.applied_on = applied_on;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
