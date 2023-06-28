/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.dao;

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
    
}
