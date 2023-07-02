/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.dao;

import static com.mycompany.hotel_reservation_system.dao.DAO.getSession;
import com.mycompany.hotel_reservation_system.pojo.BookingDetails;
import com.mycompany.hotel_reservation_system.pojo.CheckInDetails;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

/**
 *
 * @author rohitpanicker
 */

@Component
public class CheckInDetailsDao extends DAO {
    
    
    public boolean saveCheckIn(CheckInDetails checkInDetails) {
        System.out.println("Inside bookingDao");
        try {
            begin();
            getSession().clear();
            getSession().persist(checkInDetails);
            commit();
            return true;
        } catch (HibernateException e) {
            rollback();
            return false;
        }

    }
    
    
    public List<CheckInDetails> getCheckInDetailsByBookingId(String bookingId)
    {
        String hql = "Select H from CheckInDetails H where H.bookingId = :bookingId";
        Query<CheckInDetails> query = getSession().createQuery(hql, CheckInDetails.class);
        query.setParameter("bookingId", bookingId);
        
         List<CheckInDetails> results = query.getResultList();
         
         return results;
        
    }
    
    public boolean updateCheckOutTime(CheckInDetails checkInDetails)
    {
        try{
            
            begin();
            getSession().merge(checkInDetails);
            commit();
            return true;
        }catch(HibernateException e)
        {
            rollback();
            return false;
        }
    }
    
}
