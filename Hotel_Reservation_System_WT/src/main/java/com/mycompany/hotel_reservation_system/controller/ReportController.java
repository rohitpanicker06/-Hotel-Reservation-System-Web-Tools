/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.controller;

import com.mycompany.hotel_reservation_system.view.PdfViewImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.View;

/**
 *
 * @author rohitpanicker
 */
@Controller
public class ReportController {
    
    @GetMapping("/report.pdf")
    public View handleRequestGet(){
        System.out.println("Inside report.pdf");
        View view = new PdfViewImpl();
        return view;
    }
    
}
