/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validation;

import com.javaapp.emailcastingproject.RegisterUser;
import com.javaapp.emailcastingproject.alreadyRegUser;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ajay
 */
@WebServlet(name = "validFilter", urlPatterns = {"/validFilter"})
public class validFilter extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = new PrintWriter(response.getWriter());
        String uname = request.getParameter("uname");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String repass = request.getParameter("repass");
        //out.println("<center>" + uname + " <br> " + email + "<br> " + pass + "</center>");
        boolean status_alreadyRegUser = alreadyRegUser.alreadyRegister(uname, email);
        if(status_alreadyRegUser==true){
            String alreadyregister = "You are already registered";
            request.setAttribute("alreadyregister", alreadyregister);
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.include(request, response);
        }else{
        int status = RegisterUser.register(uname, email, pass);
        if (status > 0) {
            String register = "You are Successfully registered";
            request.setAttribute("register", register);
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.include(request, response);
        } else {
            String registererror = "Sorry,Registration failed. please try later";
            request.setAttribute("registererror", registererror);
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.include(request, response);
        }
        out.close();
    }
    }
}
