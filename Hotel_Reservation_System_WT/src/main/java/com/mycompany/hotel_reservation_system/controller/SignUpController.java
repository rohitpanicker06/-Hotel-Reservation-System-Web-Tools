/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.controller;

import com.mycompany.hotel_reservation_system.dao.UserAccountDao;
import com.mycompany.hotel_reservation_system.pojo.UserAccount;
import com.mycompany.hotel_reservation_system.utils.Constants;
import com.mycompany.hotel_reservation_system.utils.OTPSendingService;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author rohitpanicker
 */
@Controller
public class SignUpController {

    @GetMapping("/room/signup.htm")
    public String showSignUpForm() {

        return Constants.SIGNUP_VIEW;
    }

    @PostMapping(value = "/room/signup.htm")
    @ResponseBody
    public String postSignUpForm(HttpServletRequest request, UserAccountDao usdao, UserAccount userAccount, OTPSendingService otpService) throws EmailException {
        
        String us = request.getParameter("userName");
        System.out.println("Received SignUp Request for userName =" + us);
        String pass = request.getParameter("password");
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        userAccount.setUserName(us);
        userAccount.setPassword(pass);
        userAccount.setRole(role);
        userAccount.setEmail(email);
        boolean result = usdao.singup(userAccount);

        return "{\"success\":\"" + result + "\", \"otp\":\""+0+"\"}";

    }

}
