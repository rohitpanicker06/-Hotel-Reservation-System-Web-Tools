/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.dao;

import static com.mycompany.hotel_reservation_system.dao.DAO.getSession;
import com.mycompany.hotel_reservation_system.pojo.UserAccount;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

/**
 *
 * @author rohitpanicker
 */

@Component
public class UserAccountDao extends DAO {
    
    
    public boolean checkLogin(String us, String pass)
    {
        Query q = getSession().createQuery("FROM UserAccount Where userName= :userName AND password= :password", UserAccount.class);
        q.setParameter("userName", us);
        q.setParameter("password", pass);
        
        UserAccount account = (UserAccount) q.uniqueResult();
        
        if(account!=null)
        {
            return true;
        }
        return false;
    }
    
    
    public boolean singup(UserAccount userAccount)
    {
         try{
        begin();
        getSession().persist(userAccount);
        commit();
        return true;
         }catch(HibernateException e)
         {
             return false;
         }
        
    }
    
    
    
}
