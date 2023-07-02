/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.dao;

import static com.mycompany.hotel_reservation_system.dao.DAO.getSession;
import com.mycompany.hotel_reservation_system.pojo.BookingDetails;
import com.mycompany.hotel_reservation_system.pojo.ManageBooking;
import com.mycompany.hotel_reservation_system.pojo.Room;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

/**
 *
 * @author rohitpanicker
 */
@Component
public class BookingDetailsDao extends DAO {

    public void saveBooking(BookingDetails booking) {
       // System.out.println("Inside bookingDao");
        try {
            begin();
            getSession().persist(booking);
            commit();
        } catch (HibernateException e) {
            rollback();
        }

    }

    public List<BookingDetails> getAllBookingByUserId(Integer id) {
      //  System.out.println("Inside getAllBookingsById");
        String hqlQuery = "SELECT H FROM BookingDetails H WHERE H.userId = :id";
        getSession().clear();
        Query<BookingDetails> query = getSession().createQuery(hqlQuery, BookingDetails.class);
        query.setParameter("id", id);

        List<BookingDetails> results = query.getResultList();

      //  System.out.println(results.size());

        return results;

    }
    
     public List<BookingDetails> getAllBookingByHotelId(Integer id) {
      //  System.out.println("Inside getAllBookingsById");
        String hqlQuery = "SELECT H FROM BookingDetails H WHERE H.hotelId = :id";
        Query<BookingDetails> query = getSession().createQuery(hqlQuery, BookingDetails.class);
        query.setParameter("id", id);

        List<BookingDetails> results = query.getResultList();

      //  System.out.println(results.size());

        return results;

    }
    
    
    
    public List<BookingDetails> getBookingByBookingId(Integer bookingId)
    {
        String hqlQuery = "Select H From BookingDetails H where H.id= :id";
        Query<BookingDetails> query = getSession().createQuery(hqlQuery, BookingDetails.class);
        query.setParameter("id", bookingId);
        
        List<BookingDetails> results = query.getResultList();
        return results;
    }

    public List<ManageBooking> processResult(List<BookingDetails> bookingList, RoomDao roomDao) {
       // System.out.println("Inside processResult");
        List<ManageBooking> manageBookingList = new ArrayList<>();
        for (BookingDetails bd : bookingList) {
            Integer hotelId = bd.getHotelId();
            Room room = roomDao.getRoomById(hotelId).get(0);
          //  System.out.println("Before manageBooking processResult");
            manageBookingList.add(new ManageBooking(bd, room));

        }

        return manageBookingList;

    }
    
    public boolean changeBookingDate(Integer bookingId, String checkInDate, String checkoutDate, RoomDao roomDao)
    {
        System.out.println("" + checkInDate + " " + checkoutDate);
        checkInDate = checkInDate.trim();
       checkoutDate = checkoutDate.trim();
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
       LocalDate date1 = LocalDate.parse(checkInDate, formatter);
       LocalDate date2 = LocalDate.parse(checkoutDate, formatter);
       long daysDifference = ChronoUnit.DAYS.between(date1, date2);
        
      List<BookingDetails> list = this.getBookingByBookingId(bookingId);
      BookingDetails old = list.get(0);
      
      Room room = roomDao.getRoomById(old.getHotelId()).get(0);
      long newCost = (long) (daysDifference * room.getCostPerDay());
      
      old.setCheckoutDate(checkoutDate);
      old.setBookingDate(checkInDate);
      old.setNumberOfDays((int)daysDifference);
      old.setTotalCost((int)newCost);
      try{
      begin();
      getSession().merge(old);
      commit();
      }catch(HibernateException e)
      {
          rollback();
      }
      
      return true;
      
    }

    public Integer cancelBooking(Integer bookingId) {

         begin();
         BookingDetails bookingDetails = getSession().get(BookingDetails.class, bookingId);
         getSession().remove(bookingDetails);
         commit();
        return 1;

    }

}
