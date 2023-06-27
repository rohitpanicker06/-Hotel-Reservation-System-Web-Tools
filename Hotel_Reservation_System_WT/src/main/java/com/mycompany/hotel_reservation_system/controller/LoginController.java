/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.controller;

import com.mycompany.hotel_reservation_system.dao.UserAccountDao;
import com.mycompany.hotel_reservation_system.pojo.UserAccount;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author rohitpanicker
 */

@Controller
public class LoginController {
    
    @GetMapping("/login.htm")
    public String loginForm(){
        return "login-form";
    }
    
      @PostMapping("/login.htm")
    public String loginCheck(HttpServletRequest request,UserAccountDao userAccountDao){
        String us = request.getParameter("userName");
        String pass = request.getParameter("password");
        boolean result = userAccountDao.checkLogin(us, pass);
        if(result)
        {
            return "addRoom";
        }
        return "login-form";
    }
    
    
}
