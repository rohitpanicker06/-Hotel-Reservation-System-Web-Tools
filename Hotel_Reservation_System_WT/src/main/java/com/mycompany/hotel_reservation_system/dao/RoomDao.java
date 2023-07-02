/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.dao;

import static com.mycompany.hotel_reservation_system.dao.DAO.getSession;
import com.mycompany.hotel_reservation_system.pojo.BookingDetails;
import com.mycompany.hotel_reservation_system.pojo.Room;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import org.springframework.stereotype.Component;

/**
 *
 * @author rohitpanicker
 */
@Component
public class RoomDao extends DAO {
    
    public void saveRoom(Room room){
        try{
        begin();
        getSession().persist(room);
        commit();
        }catch(HibernateException e)
        {
            rollback();
        }
        
    }
    
    
    public List<Room> getAllRoom()
    {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Room> cq = cb.createQuery(Room.class);
        Root<Room> rootEntry = cq.from(Room.class);
        CriteriaQuery<Room> all = cq.select(rootEntry);
        TypedQuery<Room> allQuery = getSession().createQuery(all);
        return allQuery.getResultList();
        
        
    }
    
     public List<Room> getRoomById(Integer id)
    {
      
       String hqlQuery = "SELECT H FROM Room H WHERE H.id = :id";
       Query<Room> query = getSession().createQuery(hqlQuery, Room.class);
       query.setParameter("id", id);
      
       List<Room> results = query.getResultList();
       
      // System.out.println(results.size());
        
       return results;
        
    }
    
     public List<Room> getAllRoom(String address, String capacity)
    {
       // System.out.println("Dao address " + address);
       // System.out.println("Dao capacity " + capacity);
       String hqlQuery = "SELECT H FROM Room H WHERE H.capacity >= :capacity AND H.address LIKE CONCAT('%', :address, '%')";
       Query<Room> query = getSession().createQuery(hqlQuery, Room.class);
       query.setParameter("capacity", capacity);
       query.setParameter("address", address);
       List<Room> results = query.getResultList();
       
     //  System.out.println(results.size());
        
       return results;
        
        
    }
    
}
