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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rohitpanicker
 */
@RestController
public class ManageBookingRestController {

    @PostMapping("/room/manageBookings.htm")
    public String cancelBooking(HttpServletRequest request, BookingDetailsDao bookingDao, RoomDao roomDao) {

        String bookingId = request.getParameter("bookingId");
        System.out.println("Delete Booking request recieved for usedId" + bookingId);
        Integer deletedCount = bookingDao.cancelBooking(Integer.valueOf(bookingId));
        
        return "{\"deletedCount\":\"" + deletedCount + "\"}";

    }
    
    @PostMapping("/room/changeBookingDate.htm")
    public void  changeBookingDate(HttpServletRequest request, BookingDetailsDao bookingDao, RoomDao roomDao) {

        System.out.println("received changeBookingDate request");
        String bookingId = request.getParameter("bookingId");
        String checkInDate = request.getParameter("checkinDate");
        String checkOutDate = request.getParameter("checkoutDate");
        System.out.println("BookingId= " + bookingId);
        boolean updated = bookingDao.changeBookingDate(Integer.valueOf(bookingId), checkInDate, checkOutDate, roomDao);
        
       // return "{\"deletedCount\":\"" + deletedCount + "\"}";

    }

}
