/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.controller;

import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author rohitpanicker
 */
@Controller
public class OTPController {

    @ResponseBody
    @GetMapping("/room/sendOtp.htm")
    public String sendOTPForSingUP(HttpServletRequest request) throws EmailException {
        System.out.println("Received otp request");
         Random random = new Random();
         String emailAddress = request.getParameter("emailAddress").trim();
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
         return "{\"success\":\"" + true + "\", \"otp\":\""+otp+"\"}";


    }

}
