/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.controller;

import com.mycompany.hotel_reservation_system.dao.BookingDetailsDao;
import com.mycompany.hotel_reservation_system.pojo.BookingDetails;
import com.mycompany.hotel_reservation_system.utils.Constants;
import com.mycompany.hotel_reservation_system.utils.Utils;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rohitpanicker
 */

@Controller
public class BookingController {
    
    @Autowired 
    Utils utils;
    
     @GetMapping("/room/viewBookings.htm")
    public ModelAndView viewBooking(Utils utils, HttpServletResponse response, HttpSession session, HttpServletRequest request, BookingDetailsDao bookingDao, BookingDetails booking) throws IOException
    {
      if(!utils.isLoggedIn(request))
      {
        return new ModelAndView(Constants.LOGIN_VIEW, "viewBookingResult", null);
       
      }
       Integer id = Integer.parseInt(request.getParameter("param1"));
       List<BookingDetails> list = bookingDao.getAllBookingByUserId(id);
       return new ModelAndView(Constants.VIEW_BOOKINGS, "viewBookingResult", list);
       
    }
    
}
