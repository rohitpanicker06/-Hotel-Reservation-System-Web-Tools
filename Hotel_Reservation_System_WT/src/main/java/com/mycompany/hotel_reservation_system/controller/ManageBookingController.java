/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.controller;

import com.mycompany.hotel_reservation_system.dao.BookingDetailsDao;
import com.mycompany.hotel_reservation_system.dao.RoomDao;
import com.mycompany.hotel_reservation_system.pojo.BookingDetails;
import com.mycompany.hotel_reservation_system.pojo.ManageBooking;
import com.mycompany.hotel_reservation_system.utils.Constants;
import com.mycompany.hotel_reservation_system.utils.Utils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rohitpanicker
 */
@Controller
public class ManageBookingController {
    
    
    @Autowired
    Utils utils;
            
    @GetMapping("/room/manageBookings.htm")
    public ModelAndView manageBooking(HttpServletRequest request, BookingDetailsDao bookingDao, RoomDao roomDao)
    {  
        if(!utils.isLoggedIn(request))
      {
        return new ModelAndView(Constants.LOGIN_VIEW, "viewBookingResult", null);
       
      }
        
         if(!utils.checkPermission(request, Constants.USER_ROLE))
      {
          return new ModelAndView(Constants.ACCESS_ERROR_PAGE, "error", null);
      }
        
        String userId = request.getParameter("userId");
        System.out.println("Manage Booking request recieved for usedId" + userId);
        List<BookingDetails> bookingDetails = bookingDao.getAllBookingByUserId(Integer.valueOf(userId));
        List<ManageBooking> manageList = bookingDao.processResult(bookingDetails, roomDao);
      //  System.out.println(" new dates"+ bookingDetails.get(0).getBookingDate() + " " + bookingDetails.get(0).getCheckoutDate()+" ");
        return new ModelAndView(Constants.VIEW_BOOKINGS, "manageBookings", manageList);
              
        
    }
    
    
     
}
