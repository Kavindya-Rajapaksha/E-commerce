package com.ecommerce.ecommerce_multivendor.utills;

import java.util.Random;

//Generate random OTP (convert it to string and send)

public class OtpUtil {
    public static String generateOtp(){
        int otpLength = 6;
        Random random = new Random();
        StringBuilder otp = new StringBuilder(otpLength);
        for(int i=0; i<otpLength;i++){
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }
}
