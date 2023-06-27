/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.dao;

import com.mycompany.hotel_reservation_system.pojo.Room;
import org.hibernate.HibernateException;
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
    
}
