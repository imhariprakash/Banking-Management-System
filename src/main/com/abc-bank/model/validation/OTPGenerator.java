package model.validation;

import java.util.Random;

public class OTPGenerator {
    /*
    * This class generates a random 6 digit OTP
    * Using Java Math.random() method - generates a random number between 0 and 9 (inclusive) - 6 times
     */
    private OTPGenerator() {
    } // private constructor

    static int OTP(int len)
    {

        // Using numeric values
        String numbers = "0123456789";

        // Using random method
        Random rndm_method = new Random();

        int otp = 0;

        for (int i = 0; i < len; i++)
        {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            otp = otp * 10 + Integer.parseInt(String.valueOf(numbers.charAt(rndm_method.nextInt(numbers.length()))));
        }
        return(Integer.parseInt(String.valueOf(otp)));
    }
    public static int generate()
    {
        int length = 6;
        return (OTP(length));
    }
}
