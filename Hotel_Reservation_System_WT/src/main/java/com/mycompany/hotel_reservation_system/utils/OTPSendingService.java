/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.utils;

import java.util.Random;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Component;

/**
 *
 * @author rohitpanicker
 */
@Component
public class OTPSendingService {
    
    
    public int sendOTP(String emailAddress) throws EmailException
    {
         Random random = new Random();
         int otp = random.nextInt(999999);
        Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("rohit.panicker16@vit.edu", "Welcometopune18@"));
        email.setSSLOnConnect(true);
        email.setFrom("rohit.panicker16@vit.edu");
        email.setSubject("Sign UP OTP: Hotel Reservation System");
        email.setMsg("Your OTP is =" + otp);
        email.addTo(emailAddress);
        email.send();
         return otp;
    }
}
