/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.controller;

import com.mycompany.hotel_reservation_system.dao.RoomDao;
import com.mycompany.hotel_reservation_system.pojo.Room;
import com.mycompany.hotel_reservation_system.validator.HotelRoomValidator;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author rohitpanicker
 */
@Controller
public class HotelRoomController {
    
    @Autowired
    HotelRoomValidator validator;
    
    @GetMapping("/room/add.htm")
    public String showFormForaddRoomFacility(ModelMap model, Room room)
    {
       // room.setId(10);
        //room.setDescription("Testing");
        model.addAttribute("room", room); // used instead of command class
        return "addRoom";
    }
    
    
    @PostMapping("/room/add.htm")
    public String showSuccessForRoomAddition(@ModelAttribute("room") Room room, BindingResult result, SessionStatus status, RoomDao roomdao) throws IOException
    {
        String path = "/Users/rohitpanicker/Desktop/webDevProject/-Hotel-Reservation-System-Web-Tools/";
        validator.validate("room", result);
        if(result.hasErrors())
        {
            return "addRoom";
        }
        System.out.println("Outside Validate");
       
        String fileName = room.getPhoto().getOriginalFilename();
        
        File file  = new File(path, room.getId() + ".jpg");
        
        room.getPhoto().transferTo(file);
        room.setPhotoFilePath( room.getId() + ".jpg");
        
        status.setComplete();
        
        roomdao.saveRoom(room);
       
        
        return "addRoom";
    }
    
}
