/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validation;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 *
 * @author ajay
 */
@WebFilter(filterName = "ValidationFilterForRegistratio", urlPatterns = {"/validFilter"})
public class ValidationFilterForRegistratio implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = new PrintWriter(response.getWriter());
        String uname = request.getParameter("uname");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String repass = request.getParameter("repass");
        if (uname.equals("")) {
            String unameMSG = "<b>Name can't be empty";
            request.setAttribute("unameMSG", unameMSG);
            //request.getRequestDispatcher("register.jsp").include(request, response);
        }
        if (email.equals("")) {
            String emailMSG1 = "<b>Email can't be empty";
            request.setAttribute("emailMSG1",emailMSG1 );
            //request.getRequestDispatcher("register.jsp").forward(request, response);

        }
        if (!email.endsWith("@gmail.com")&& !email.equals("")){
            String emailMSG2 = "<b>Please Enter A Right Gmail";
            request.setAttribute("emailMSG2", emailMSG2);
            //request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        if (pass.equals("")) {
            String passMSG = "<b>Pasword can't be empty";
            request.setAttribute("passMSG", passMSG);
           // request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        if (!pass.equals(repass)) {
            String repassMSG = "password not matched";
            request.setAttribute("repassMSG", repassMSG);
           // request.getRequestDispatcher("register.jsp").forward(request, response);
        } 
        if((uname.equals(""))||(email.equals(""))||(!email.endsWith("@gmail.com")&& !email.equals(""))||(pass.equals(""))||(!pass.equals(repass))){
            
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        
        else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

}
