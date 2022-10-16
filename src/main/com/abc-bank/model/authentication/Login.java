package model.authentication;

// servlet_api -> already tomcat has this jar - so conflict arises
public class Login {
    private Login() {
    }

    public static boolean isVerifiedUser(String username, String password) {
        return false;
    }
}
