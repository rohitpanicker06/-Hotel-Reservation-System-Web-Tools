/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

/**
 *
 * @author rohitpanicker
 */

@Component
public class Utils {
    
    public boolean isLoggedIn(HttpServletRequest request)
    {
        
         HttpSession session = request.getSession(false);
         
        // Get the current session if it exists
           if(session.getAttribute("isLoggedIn") == null)
           {
               return false;
           }else if( Boolean.parseBoolean((String) session.getAttribute("isLoggedIn")) == true )
           {
               return true;
           }
          
           
           return false;
        
    }
    
}
