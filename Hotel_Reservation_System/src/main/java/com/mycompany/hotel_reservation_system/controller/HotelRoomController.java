/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.controller;

import com.mycompany.hotel_reservation_system.pojo.RoomPojo;
import com.mycompany.hotel_reservation_system.validator.HotelRoomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author rohitpanicker
 */
@Controller
public class HotelRoomController {
    
    @Autowired
    HotelRoomValidator validator;
    
    @GetMapping("/room/add.htm")
    public String showFormForaddRoomFacility(ModelMap model, RoomPojo room)
    {
       // room.setId(10);
        //room.setDescription("Testing");
        model.addAttribute("room", room); // used instead of command class
        return "addRoom";
    }
    
    
    @PostMapping("/room/add.htm")
    public String showSuccessForRoomAddition(@ModelAttribute("room") RoomPojo room, BindingResult result, SessionStatus status)
    {
        validator.validate("room", result);
        if(result.hasErrors())
        {
            return "addRoom";
        }
        status.setComplete();
        return "";
    }
    
}
