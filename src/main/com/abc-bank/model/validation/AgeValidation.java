package model.validation;

import java.sql.Date;

public class AgeValidation {
    private AgeValidation() {
    } // Prevents instantiation

    public static boolean validate(Date age){
        try {
            Date today = new Date(System.currentTimeMillis());
            long diff = today.getTime() - age.getTime();
            long diffYears = diff / (24 * 60 * 60 * 1000 * 365);
            return diffYears >= 18;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
