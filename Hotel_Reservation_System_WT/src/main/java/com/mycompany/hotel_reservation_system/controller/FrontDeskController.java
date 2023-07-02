/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.controller;

import com.mycompany.hotel_reservation_system.dao.BookingDetailsDao;
import com.mycompany.hotel_reservation_system.dao.RoomDao;
import com.mycompany.hotel_reservation_system.pojo.BookingDetails;
import com.mycompany.hotel_reservation_system.pojo.Room;
import com.mycompany.hotel_reservation_system.utils.Constants;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.hotel_reservation_system.dao.CheckInDetailsDao;
import com.mycompany.hotel_reservation_system.pojo.CheckInDetails;
import com.mycompany.hotel_reservation_system.utils.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author rohitpanicker
 */
@Controller
public class FrontDeskController {

    @GetMapping("/room/frontdesk.htm")
    public ModelAndView getFrontDeskView(HttpServletRequest request, Utils utils, RoomDao roomDao) {
        if(!utils.isLoggedIn(request))
      {
        return new ModelAndView(Constants.LOGIN_VIEW, "viewBookingResult", null);
       
      }
        
         if(!utils.checkPermission(request, Constants.FRONTDESK_ROLE))
      {
          return new ModelAndView(Constants.ACCESS_ERROR_PAGE, "error", null);
      }
        
        List<Room> hotelRoomsList = roomDao.getAllRoom();

        return new ModelAndView(Constants.FRONT_DESK, "hotelRoomsList", hotelRoomsList);
    }

    @ResponseBody
    @GetMapping(value = "/room/getAllBookings.htm", produces = "application/json")
    public String getAllBookings(HttpServletRequest request, BookingDetailsDao bookingDao) {

        System.out.println("Got getAllBooking request");
        String hotelId = request.getParameter("hotelId");
        List<BookingDetails> bookingList = bookingDao.getAllBookingByHotelId(Integer.parseInt(hotelId));
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> jsonResults = new ArrayList<>();

        try {
            for (BookingDetails booking : bookingList) {
                // Convert POJO to JSON string
                String json = objectMapper.writeValueAsString(booking);
                jsonResults.add(json);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

// Convert the list of JSON strings to a JSON array
        String jsonArray = "[" + String.join(",", jsonResults) + "]";

// Return the JSON array
        System.out.println(jsonArray);
        return jsonArray;
    }

    
    @ResponseBody
    @PostMapping(value = "/room/checkInGuest.htm")
    public String checkInGuest(HttpServletRequest request, CheckInDetailsDao checkInDetailsDao, CheckInDetails checkInDetails) {

        String bookingID = request.getParameter("param1").trim();
        System.out.println("CheckIn Request Received = " + bookingID);
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        String timestampString = currentTimestamp.toString();
        checkInDetails.setBookingId(Integer.valueOf(bookingID));
        checkInDetails.setCheckInTime(timestampString);
        boolean result = checkInDetailsDao.saveCheckIn(checkInDetails);
        return "{\"success\":\"" + result + "\"}";

    }
    
    @ResponseBody
    @PostMapping(value = "/room/checkOutGuest.htm")
    public String checkOutGuest(HttpServletRequest request, CheckInDetailsDao checkInDetailsDao, CheckInDetails checkInDetails) {

         String bookingID = request.getParameter("param1").trim();
        System.out.println("CheckOut Request Received = " + bookingID);
       
        
        checkInDetails = checkInDetailsDao.getCheckInDetailsByBookingId(bookingID).get(0);
       
        
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        checkInDetails.setCheckOutTime(currentTimestamp.toString());

        boolean result = checkInDetailsDao.updateCheckOutTime(checkInDetails);
        return "{\"success\":\"" + result + "\"}";
        

    }
}
