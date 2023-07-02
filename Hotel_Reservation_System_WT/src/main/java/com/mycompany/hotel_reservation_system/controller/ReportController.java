/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.controller;

import com.mycompany.hotel_reservation_system.utils.Constants;
import com.mycompany.hotel_reservation_system.view.PdfViewImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

/**
 *
 * @author rohitpanicker
 */
@Controller
public class ReportController {
    
    @GetMapping("/room/report.pdf")
    public View handleRequestGet( HttpServletRequest request, HttpServletResponse response){
       
      
        String uniqueId = request.getParameter("uniqueId");
       String hotelId = request.getParameter("roomId");
       String bookingDate = request.getParameter("bookingDate");
       String checkoutDate = request.getParameter("checkoutDate");
       
      // System.out.println( uniqueId + " " + hotelId + " " + bookingDate + " ");
        response.setHeader("Content-Disposition", "inline; filename=report.pdf");
        response.setContentType("application/pdf");
        View view = new PdfViewImpl(uniqueId, bookingDate, hotelId, checkoutDate);
        return view;
    }
    
}
