/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaapp.emailcastingproject;

import com.DB.GetCon;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ajay
 */
@WebServlet(name = "InboxServlet", urlPatterns = {"/InboxServlet"})
public class InboxServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		HttpSession session=request.getSession(false);
		String uname=(String)session.getAttribute("username");
		try {
			Connection con=GetCon.getCon();
			PreparedStatement ps=con.prepareStatement("Select * from INBOX6 where RECIEVER=?");
			ps.setString(1,uname);
			ResultSet rs=ps.executeQuery();
			
			out.print("<table align='left' width='50%' border='4' bgcolor='###FFF'>");
			out.print("<tr><th>SENDER</th><th>MESSAGE</th><th>DATE OF RECIEVING</th><th>Delete</th></tr>");
			while(rs.next()){
				int id=rs.getInt(1);
				session.setAttribute("id",id);
				
				System.out.print(id);
				out.print("<tr>");
				out.print("<td>" + rs.getString(3) + "</td>");
				out.print("<td>" + rs.getString(4) + "</td>");
				out.print("<td>" + rs.getString(5) + "</td>");
				//out.print("<td>" + rs.getString(4) + "</td>");
				//out.print("<td>" DeleteServlet.Del`"</td>");
				out.print("<td><a href='DeleteServlet' >Delete</a></td>");
			
				out.print("</tr>");
			
			}
			out.print("</table>");
			out.print("<table align='right'width='40%'>");
			out.print("<tr><td><a href='Compose.html'>COMPOSE</a></td></tr>");
			out.print("<tr><td><a href='InboxServlet'>INBOX</a></td></tr>");
			out.print("<tr><td><a href='LogoutServlet'>LOGOUT</a></td></tr>");
			//out.print("<tr><td><a href='DeleteServlet'>Delete</a></td></tr>");
			
			out.print("</table>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
