/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ViewServlet", urlPatterns = {"/ViewServlet"})
public class ViewServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //out.println("<a href='manageUserAdmin.jsp'>Add New Candidate</a>");
        out.println("<br><br><a href=\"manageUserAdmin.jsp\" style=\"float: right; text-decoration: none; border: 1px solid black; padding: 15px; background-color: green; color: #FFF; font-size: 20px\">Add New Candidate</a>  \n" +
"    <br><br><br>");
        out.println("<center><h1>Registerd Candidate List</h1><center>");

        List<Emp> list = EmpDao.getAllEmployees();
        out.println("<style>\n"
                + "#customers {\n"
                + "    font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;\n"
                + "    border-collapse: collapse;\n"
                + "    width: 100%;\n"
                + "}\n"
                + "\n"
                + "#customers td, #customers th {\n"
                + "    border: 1px solid #ddd;\n"
                + "    padding: 8px;\n"
                + "}\n"
                + "\n"
                + "#customers tr:nth-child(even){background-color: #f2f2f2;}\n"
                + "\n"
                + "#customers tr:hover {background-color: #ddd;}\n"
                + "\n"
                + "#customers th {\n"
                + "    padding-top: 12px;\n"
                + "    padding-bottom: 12px;\n"
                + "    text-align: left;\n"
                + "    background-color: #4CAF50;\n"
                + "    color: white;\n"
                + "}\n"
                + "</style>");
        
        
        out.print("<table id=\"customers\" border='1' width='100%'");
        out.print("<tr><th>Id</th><th>Name</th><th>Password</th><th>Email</th>"
                + " <th>Edit</th><th>Delete</th></tr>");
        for (Emp e : list) {
            out.print("<tr><td>" + e.getId() + "</td><td>" + e.getName() + "</td><td>" + e.getEmail() + "</td>  "
                    + "  <td>" + e.getPassword() + "</td><td><a href='EditServlet?id=" + e.getId() + "'>edit</a></td>  "
                    + "  <td><a href='DeleteServlet?id=" + e.getId() + "'>delete</a></td></tr>");
        }
        out.print("</table>");
        
        out.close();
    }
}
