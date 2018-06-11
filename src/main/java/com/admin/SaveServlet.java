/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin;

import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
@WebServlet(name = "SaveServlet", urlPatterns = {"/SaveServlet"})  
public class SaveServlet extends HttpServlet {  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
         throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
          
        String name=request.getParameter("name");  
        String password=request.getParameter("password");  
        String email=request.getParameter("email");  
          
        Emp e=new Emp();  
        e.setName(name);  
        e.setPassword(password);  
        e.setEmail(email);    
          
        int status=EmpDao.save(e);  
        if(status>0){  
            out.print("<p><b style='color:green'>Record saved successfully!</p>");  
            request.getRequestDispatcher("/manageUserAdmin.jsp").include(request, response);  
        }else{  
            out.println("<b style='color:red'>Sorry! unable to save record");  
            request.getRequestDispatcher("/manageUserAdmin.jsp").include(request, response); 
        }  
          
        out.close();  
    }  
  
}
