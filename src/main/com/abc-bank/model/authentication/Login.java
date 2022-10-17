package model.authentication;

import com.google.gson.JsonObject;

// servlet_api -> already tomcat has this jar - so conflict arises
public class Login {
    private Login() {
    }

    public static boolean isVerifiedUser(String username, String password, JsonObject response) {
        String passkey = dao.login.Netbanking.getPassword(username, response);
        if(password.equals(passkey)){
            return true;
        }
        return false;
    } // TODO : if true - add response with status 200 and message "Login successful"
      // TODO : if false - add response with status 401 and message "Invalid credentials" - from where its been called!
}
