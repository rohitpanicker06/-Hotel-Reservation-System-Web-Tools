/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.controller;

import com.mycompany.hotel_reservation_system.dao.UserAccountDao;
import com.mycompany.hotel_reservation_system.pojo.UserAccount;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rohitpanicker
 */
@RestController
public class LoginRestController {
    
    @PostMapping(value = "/room/checkLoginRest.htm", produces="application/json")
    public String checkLogin( HttpSession session, HttpServletRequest request, HttpServletResponse response, UserAccountDao dao, UserAccount userAccount)
    {
       
       session.setAttribute("testSession", "sessionTest");
       String userName = request.getParameter("param1");
       String password = request.getParameter("param2");
       String role = request.getParameter("param3");
       
        System.out.println("Received checkLoginRequest for " + userName);
       UserAccount returnUserAccount = dao.checkLogin(userName, password, userAccount, role);
       if(returnUserAccount != null)
       {
           session.setAttribute("isLoggedIn", "true");
           request.getSession().setAttribute("userAccount", returnUserAccount);
           session.setAttribute("role", role);
           UserAccount test = (UserAccount) request.getSession().getAttribute("userAccount");
           
           return "{\"checkLoginResult\":\"true\",\"role\":\""+role+"\"}";
       }
       
        return "{\"checkLoginResult\":\"false\",\"role\":\""+role+"\"}";
       
        
    }
}
