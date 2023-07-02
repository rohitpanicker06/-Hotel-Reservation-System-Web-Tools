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
           if(session!=null && session.getAttribute("isLoggedIn") == null)
           {
               System.out.println("IsLoggedIn=false");
               return false;
           }else if( Boolean.parseBoolean((String) session.getAttribute("isLoggedIn")) == true )
           {
               System.out.println("IsLoggedIn=true");
               return true;
           }
          
           
           return false;
        
    }
    
    
    public boolean checkPermission (HttpServletRequest request, String role)
    {
        
        
        boolean result = request.isUserInRole(role) || request.isUserInRole("admin");
     
        return result;
        
    }
    
}
