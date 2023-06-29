/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.controller;

import com.mycompany.hotel_reservation_system.dao.RoomDao;
import com.mycompany.hotel_reservation_system.dao.UserAccountDao;
import com.mycompany.hotel_reservation_system.pojo.Room;
import com.mycompany.hotel_reservation_system.pojo.UserAccount;
import com.mycompany.hotel_reservation_system.utils.Constants;
import com.mycompany.hotel_reservation_system.utils.Utils;
import com.mycompany.hotel_reservation_system.validator.HotelRoomValidator;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author rohitpanicker
 */
@Controller
public class HotelRoomController {
    
    @Autowired
    HotelRoomValidator validator;
    
    @GetMapping("/room/add.htm")
    public String showFormForaddRoomFacility(HttpServletRequest request, ModelMap model, Room room, Utils utils)
    {
         
        /*if (!utils.isLoggedIn(request)) {
            return Constants.LOGIN_VIEW;
        }else{
           
        model.addAttribute("room", room); // used instead of command class
        return Constants.ADD_ROOM_VIEW;
        }*/
        
        return Constants.ADD_ROOM_VIEW;
    }
    
   
    
    
    @PostMapping("/room/add.htm")
    public String showSuccessForRoomAddition(HttpServletRequest request, @ModelAttribute("room") Room room, BindingResult result, SessionStatus status, RoomDao roomdao) throws IOException
    {
        
        String path = "/Users/rohitpanicker/Desktop/webDevProject/FileUploads/";
        validator.validate("room", result);
        if(result.hasErrors())
        {
            return Constants.ADD_ROOM_VIEW;
        }
        System.out.println("Outside Validate");
       
        String fileName = room.getPhoto().getOriginalFilename();
        
        File file  = new File(path, room.getId() + ".jpg");
        
        room.getPhoto().transferTo(file);
        room.setPhotoFilePath( room.getId() + ".jpg");
        
        status.setComplete();
        
        roomdao.saveRoom(room);
       
        
        return Constants.ADD_ROOM_Sucess;
    }
    
    
    @GetMapping("/room/searchRooms.htm")
    public String getAllHotelRooms(HttpSession session, HttpServletRequest request, RoomDao dao)
    {
           System.out.println("Session testing inside get = " +   session.getId());
        return "searchPage";
    }
    
    @PostMapping("/room/searchRooms.htm")
    public ModelAndView searchHotelRooms(HttpSession session, HttpServletRequest request, RoomDao dao)
    {
       System.out.println("Session testing inside post = " +   session.getId());
       String address = request.getParameter("address");
       String capacity = request.getParameter("capacity");
       String date = request.getParameter("date");
       List<Room> allRooms = dao.getAllRoom(address, capacity);
       System.out.println("Last Result size = " + allRooms.size());
       
       ModelAndView mv = new ModelAndView();
       mv.addObject("searchResults", allRooms);
       mv.addObject("bookingDate", date);
       mv.setViewName("searchPage");
       
       return mv;
     
    }
    
}
