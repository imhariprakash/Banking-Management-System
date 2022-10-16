package model.register;

public class AlreadyRegistered {
    private AlreadyRegistered() {
    } // Prevents instantiation

    public static boolean isAlreadyRegistered(String email){
        try {
            return dao.register.AlreadyRegistered.isAlreadyRegistered(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
