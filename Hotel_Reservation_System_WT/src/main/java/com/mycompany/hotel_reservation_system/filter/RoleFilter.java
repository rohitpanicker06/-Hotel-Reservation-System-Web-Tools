/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.filter;

/**
 *
 * @author rohitpanicker
 */

import com.mycompany.hotel_reservation_system.pojo.UserAccount;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/room/*")
public class RoleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

   @Override
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    // Set the desired role for the user
    String role = (String) httpRequest.getSession().getAttribute("role");
    if (role != null) {
        UserAccount userAccount = (UserAccount) httpRequest.getSession().getAttribute("userAccount");

        List<String> roles = new ArrayList<>();
        roles.add(role);

        if (userAccount != null) {
            String user = userAccount.getUserName();
            chain.doFilter(new UserRoleRequestWrapper(user, roles, httpRequest), response);
            return; // Return after calling doFilter with the wrapper
        }
    }

    chain.doFilter(httpRequest, httpResponse);
}
    @Override
    public void destroy() {
        // Cleanup code, if needed
    }
}


