/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.controller;

import com.mycompany.hotel_reservation_system.dao.UserAccountDao;
import com.mycompany.hotel_reservation_system.pojo.Room;
import com.mycompany.hotel_reservation_system.pojo.UserAccount;
import com.mycompany.hotel_reservation_system.utils.Constants;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
        return Constants.LOGIN_VIEW;
    }
    
    
      @PostMapping("/room/login.htm")
    public String loginCheck(HttpServletRequest request,UserAccountDao userAccountDao, ModelMap model, Room room, UserAccount userAccount){
        String us = request.getParameter("userName");
        String pass = request.getParameter("password");
        boolean result = userAccountDao.checkLogin(us, pass);
        if(result)
        {    userAccount.setUserName(us);
             userAccount.setPassword(pass);
             
             model.addAttribute("room", room); 
             request.getSession().setAttribute("isLoggedIn", "true");
             request.getSession().setAttribute("userAccount",userAccount );
            return Constants.ADD_ROOM_VIEW;
        }else{
        return Constants.LOGIN_VIEW;
    }
    }
    
    
}
