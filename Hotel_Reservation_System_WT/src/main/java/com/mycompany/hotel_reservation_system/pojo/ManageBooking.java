/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.pojo;

/**
 *
 * @author rohitpanicker
 */
public class ManageBooking {
    
    private  BookingDetails bookingDetails;
    private  Room room;

    public BookingDetails getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(BookingDetails bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    public Room getRoom() {
        return room;
    }

    public ManageBooking(BookingDetails bookingDetails, Room room) {
        this.bookingDetails = bookingDetails;
        this.room = room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ManageBooking() {
    }
    
}
