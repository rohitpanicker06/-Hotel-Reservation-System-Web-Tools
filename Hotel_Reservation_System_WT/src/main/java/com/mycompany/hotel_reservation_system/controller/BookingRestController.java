/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.controller;

import com.mycompany.hotel_reservation_system.dao.BookingDetailsDao;
import com.mycompany.hotel_reservation_system.pojo.BookingDetails;
import com.mycompany.hotel_reservation_system.pojo.UserAccount;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rohitpanicker
 */
@RestController
public class BookingRestController {
    
    
    @PostMapping("/room/bookRoom.htm")
    public String bookHotel(HttpSession session, HttpServletRequest request, BookingDetailsDao bookingDao, BookingDetails booking)
    {
        System.out.println("Received bokoing request");
        String bookingDate = request.getParameter("param1");
        String hotelID = request.getParameter("param2");
        String checkoutDate = request.getParameter("param3");
        String numberOfDays = request.getParameter("param4");
        String costPerDay = request.getParameter("param5");
       //  System.out.println("Received booking request for bookingDate " + bookingDate + " and hotelId= " +hotelID);
        // System.out.println("Received booking request for checkoutDate " + checkoutDate + " and numberDays= " +numberOfDays);
        UserAccount userAccount = (UserAccount) session.getAttribute("userAccount");
        Integer userId = userAccount.getId();
       // System.out.println("Received booking request for bookingDate " + bookingDate + " and hotelId= " +hotelID 
        //+ "  userId = " + userId );
        
        booking.setHotelId(Integer.valueOf(hotelID));
        Integer uniqueId = Integer.parseInt(getRandomNumberString());
        booking.setId(Integer.parseInt(String.valueOf(uniqueId)));
        booking.setUserId(userId);
        booking.setBookingDate(bookingDate);
        booking.setCheckoutDate(checkoutDate);
        booking.setNumberOfDays(Integer.valueOf(numberOfDays));
        booking.setTotalCost(Integer.valueOf(numberOfDays)*Integer.valueOf(costPerDay));
        
        bookingDao.saveBooking(booking);
        String result ="true";
        String jsonResult = "{\"added\":\"" + result + "\", \"uniqueId\":\""+ uniqueId + "\"}";
         return jsonResult;
        
    }
    
    public static String getRandomNumberString() {
    
    Random rnd = new Random();
    int number = rnd.nextInt(999999);
    return String.format("%06d", number);
}
    
    
    
    
    
}
