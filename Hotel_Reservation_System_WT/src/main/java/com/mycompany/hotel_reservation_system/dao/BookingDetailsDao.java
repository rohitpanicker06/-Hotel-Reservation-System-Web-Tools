/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.dao;

import static com.mycompany.hotel_reservation_system.dao.DAO.getSession;
import com.mycompany.hotel_reservation_system.pojo.BookingDetails;
import com.mycompany.hotel_reservation_system.pojo.Room;
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
    
    
   public void saveBooking(BookingDetails booking){
       System.out.println("Inside bookingDao");
        try{
        begin();
        getSession().persist(booking);
        commit();
        }catch(HibernateException e)
        {
            rollback();
        }
        
    }
   
    public List<BookingDetails> getAllBookingByUserId(Integer id)
    {
       System.out.println("Inside getAllBookingsById");
       String hqlQuery = "SELECT H FROM BookingDetails H WHERE H.id == :id";
       Query<BookingDetails> query = getSession().createQuery(hqlQuery, BookingDetails.class);
       query.setParameter("id", id);
      
       List<BookingDetails> results = query.getResultList();
       
       System.out.println(results.size());
        
       return results;
        
        
    }
    
    
}
