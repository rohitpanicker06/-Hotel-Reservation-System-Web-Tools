/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.validator;

import com.mycompany.hotel_reservation_system.pojo.Room;
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
        return Room.class.isAssignableFrom(clazz);
        
    }

    @Override
    public void validate(Object target, Errors errors) {
           
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id cannot be empty", "Id cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "empty-description", "Description cannot be empty");
        
       /* Room room = (Room) target;
        if(room.getPhoto().getSize() == 0 )
        {
            errors.rejectValue("photo", "empty-photo", "File is empty");
        }*/

    }
    
}
