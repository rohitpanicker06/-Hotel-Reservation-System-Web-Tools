/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.validator;

import com.mycompany.hotel_reservation_system.pojo.RoomPojo;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author rohitpanicker
 */

@Component
public class HotelRoomValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return RoomPojo.class.isAssignableFrom(clazz);
        
    }

    @Override
    public void validate(Object target, Errors errors) {
           
       // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "empty-description", "Description cannot be empty");
        


    }
    
}
