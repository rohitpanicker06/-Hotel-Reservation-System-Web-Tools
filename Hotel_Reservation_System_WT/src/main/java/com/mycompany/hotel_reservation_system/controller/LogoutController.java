/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rohitpanicker
 */

@RestController
public class LogoutController {
    
    
    @PostMapping("/room/logout.htm")
    public String logout(HttpServletRequest request)
    {
        request.getSession().invalidate();
        return String.valueOf(true);
    }
    
}
