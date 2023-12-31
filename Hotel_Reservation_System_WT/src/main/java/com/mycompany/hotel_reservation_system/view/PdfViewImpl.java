/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.view;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.mycompany.hotel_reservation_system.dao.RoomDao;
import com.mycompany.hotel_reservation_system.pojo.Room;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractPdfView;

/**
 *
 * @author rohitpanicker
 */
public class PdfViewImpl extends AbstractPdfView{

    private String uniqueId;
    private String bookingDate;
    private String roomId;
    private String imagePath = "/Users/rohitpanicker/Desktop/webDevProject/FileUploads/";
    private String checkoutDate;
    
    
   
    RoomDao roomDao = new RoomDao();
    public PdfViewImpl() {
    }
    
    public PdfViewImpl(String uniqueId, String bookingDate, String roomId, String checkoutDate)
    {
        this.uniqueId=uniqueId;
        this.bookingDate=bookingDate;
        this.roomId=roomId;
        this.checkoutDate = checkoutDate;
        
    }

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
     
        
        
     Paragraph p1 =  new Paragraph(uniqueId);
     Paragraph p2 =  new Paragraph(bookingDate);
     Paragraph p3 = new Paragraph(checkoutDate);
     List<Room> room = roomDao.getRoomById(Integer.parseInt(roomId));
     String newImagePath = imagePath + room.get(0).getId() + ".jpg";
   //  System.out.println("New Image Path = " + newImagePath);
     String hotelName = room.get(0).getHotelName();
     String address = room.get(0).getAddress();
     Integer pincode = room.get(0).getPincode();
     String costPerDay = String.valueOf(room.get(0).getCostPerDay());
     double costPerDayDouble = Double.parseDouble(costPerDay);
     
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
       LocalDate date1 = LocalDate.parse(bookingDate, formatter);
       LocalDate date2 = LocalDate.parse(checkoutDate, formatter);
       
        
        // Calculate the difference between the two dates
        Long daysDifference = ChronoUnit.DAYS.between(date1, date2);
     
      
    Long totalbookingAmount = (long) (costPerDayDouble * daysDifference);
     
     Integer capacity = room.get(0).getCapacity();
     Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Font.BOLD);
    Font subtitleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Font.BOLD);
    Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
    Image image = Image.getInstance(newImagePath);
    image.scaleToFit(200, 200);
    // Create the report content
    Paragraph title = new Paragraph("Hotel Reservation Report", titleFont);
    title.setAlignment(Element.ALIGN_CENTER);
    
    Paragraph totalCostInfo = new Paragraph("Total Booking Amount = $"+totalbookingAmount, subtitleFont);
    Paragraph reservationInfo = new Paragraph("Reservation Information", subtitleFont);
    reservationInfo.setSpacingBefore(20);
    
    Paragraph uniqueIdParagraph = new Paragraph("Reservation ID: " + uniqueId, contentFont);
    Paragraph bookingDateParagraph = new Paragraph("Checkin Date: " + bookingDate, contentFont);
     Paragraph checkoutDateParagraph = new Paragraph("Checkout Date: " + checkoutDate, contentFont);
    
    Paragraph roomInfo = new Paragraph("Room Information", subtitleFont);
    roomInfo.setSpacingBefore(20);
    
    Paragraph hotelNameParagraph = new Paragraph("Hotel Name: " + hotelName, contentFont);
    Paragraph addressParagraph = new Paragraph("Address: " + address, contentFont);
    Paragraph pincodeParagraph = new Paragraph("Pincode: " + pincode, contentFont);
    Paragraph costPerDayParagraph = new Paragraph("Cost Per Day: " + costPerDay, contentFont);
    Paragraph capacityParagraph = new Paragraph("Capacity: " + capacity, contentFont);
    
    // Add the content to the document
    doc.add(title);
    doc.add(reservationInfo);
    doc.add(uniqueIdParagraph);
    doc.add(bookingDateParagraph);
    doc.add(checkoutDateParagraph);
    doc.add(roomInfo);
    doc.add(image);
    doc.add(hotelNameParagraph);
    doc.add(addressParagraph);
    doc.add(pincodeParagraph);
    doc.add(costPerDayParagraph);
    doc.add(capacityParagraph);
    doc.add(totalCostInfo);
    
    // Close the document
    doc.close();

    }
    
}
