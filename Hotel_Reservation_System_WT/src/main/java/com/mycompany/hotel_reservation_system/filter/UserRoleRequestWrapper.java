/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.filter;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
 
/**
 *
 * @author rohitpanicker
 */
 
public class UserRoleRequestWrapper extends HttpServletRequestWrapper {
 
 
  String user;
  List<String> roles = null;
  HttpServletRequest realRequest;
   
  public UserRoleRequestWrapper(String user, List<String> roles, HttpServletRequest request) {
    super(request);
    this.user = user;
    this.roles = roles;
    this.realRequest = request;
//    if (this.roles == null) {
//      this.roles = new ArrayList<String>();
//    }
  }
 
  @Override
  public boolean isUserInRole(String role) {
    if (roles == null) {
      return this.realRequest.isUserInRole(role);
    }
    return roles.contains(role);
  }
 
  @Override
  public Principal getUserPrincipal() {
    if (this.user == null) {
      return realRequest.getUserPrincipal();
    }
     
    // make an anonymous implementation to just return our user
    return new Principal() {
      @Override
      public String getName() {     
        return user;
      }
    };
  }
}
