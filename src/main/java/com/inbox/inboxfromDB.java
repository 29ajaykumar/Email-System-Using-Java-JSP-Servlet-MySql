/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inbox;

import com.DB.GetCon;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "inboxfromDB", urlPatterns = {"/inboxfromDB"})
public class inboxfromDB extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String userName = "dummy2263@gmail.com";
        Connection con = GetCon.getCon();
        int i = 0;
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("Select * from inbox where userName = ?");
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            RequestDispatcher rd = request.getRequestDispatcher("header.jsp");
            rd.include(request, response);
            while (rs.next()) {
                /* TODO output your page here. You may use following sample code. */
                //userpass[1] = rs.getString(3);
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>inboxfromDB</title>");
                out.println("<style>"
                        + " #customers {\n"
                        + "                font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;\n"
                        + "                border-collapse: collapse;\n"
                        + "                width: 100%;\n"
                        + "            }\n"
                        + "\n"
                        + "            #customers td, #customers th {\n"
                        + "                border: 1px solid #ddd;\n"
                        + "                padding: 8px;\n"
                        + "            }\n"
                        + "\n"
                        + "            #customers tr:nth-child(even){background-color: #f2f2f2;}\n"
                        + "\n"
                        + "            #customers tr:hover {background-color: #ddd;}\n"
                        + "\n"
                        + "            #customers th {\n"
                        + "                padding-top: 12px;\n"
                        + "                padding-bottom: 12px;\n"
                        + "                text-align: left;\n"
                        + "                background-color: #4CAF50;\n"
                        + "                color: white;\n"
                        + "            }\n"
                        + "\n"
                        + "            .accordion {\n"
                        + "                background-color: #eee;\n"
                        + "                color: #444;\n"
                        + "                cursor: pointer;\n"
                        + "                padding: 9px;\n"
                        + "                width: 100%;\n"
                        + "                border: none;\n"
                        + "                text-align: left;\n"
                        + "                outline: none;\n"
                        + "                font-size: 15px;\n"
                        + "                transition: 0.4s;\n"
                        + "            }\n"
                        + "\n"
                        + "            .active, .accordion:hover {\n"
                        + "                background-color: red; \n"
                        + "            }\n"
                        + "\n"
                        + "            .panel {\n"
                        + "                padding: 0 0px;\n"
                        + "                display: none;\n"
                        + "                background-color: white;\n"
                        + "                overflow: hidden;\n"
                        + "            }\n"
                        + "            .btn{\n"
                        + "                background-color: #eee;\n"
                        + "                color: #444;\n"
                        + "                cursor: pointer;\n"
                        + "                padding: 9px;\n"
                        + "                width: 11%;\n"
                        + "                border: none;\n"
                        + "                text-align: center;\n"
                        + "                outline: none;\n"
                        + "                font-size: 15px;\n"
                        + "                transition: 0.4s;\n"
                        + "            }\n"
                        + "            .collapsible {\n"
                        + "                background-color: #777;\n"
                        + "                color: white;\n"
                        + "                cursor: pointer;\n"
                        + "                padding: 18px;\n"
                        + "                width: 11%;\n"
                        + "                border: none;\n"
                        + "                text-align: center;\n"
                        + "                outline: none;\n"
                        + "                font-size: 15px;\n"
                        + "            }\n"
                        + "\n"
                        + "            .active, .collapsible:hover {\n"
                        + "                background-color: #9E9CD8;\n"
                        + "            }\n"
                        + "\n"
                        + "            .content {\n"
                        + "                padding: 0 18px;\n"
                        + "                display: none;\n"
                        + "                overflow: hidden;\n"
                        + "                background-color: #f1f1f1;\n"
                        + "            }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                if (i == 0) {
                    out.println("<h1><center><br><b>DBINBOX</center><br><br><br> </h1>");
                    i++;
                }
                out.println("<button class=\"accordion\"><b>From</b>" + rs.getString(2) + "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<b>Subject</b>" + rs.getString(4) + " </button>");
                out.println("<div class=\"panel\">");
                out.println("<table id=\"customers\">");
                out.println("<tr>");
                out.println("<th>Content</th>");
                out.println("<th>Date</th>");
                out.println("<th>Attachments</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>" + rs.getString(6) + "</td>");
                out.println("<td>" + rs.getString(5) + "</td>");
                out.println("<td>" + rs.getString(7) + "</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<br><br><center><button style = \" color:green\" class=\"btn\">Reply</button>");
                out.println("<button style = \" color:red\" class=\"btn\">Delete</button>");
                out.println("<button style = \" color:green\" class=\"btn\">Forward</button></center><br><br>");
                out.println("</div>");

//                out.println("");
//                out.println("<h1><br>UserID: " + rs.getString(2) + "</h1>");
//                out.println("<h1><br>From: " + rs.getString(3) + "</h1>");
//                out.println("<h1><br>Subject: " + rs.getString(4) + "</h1>");
//                out.println("<h1><br>Reciev Date: " + rs.getString(5) + "</h1>");
//                out.println("<h1><br>Message: " + rs.getString(6) + "</h1>");
//                out.println("<h1><br>Attached File: " + rs.getString(7) + "</h1>");
                out.println("</body>");
                out.println("<script>"
                        + " var acc = document.getElementsByClassName(\"accordion\");\n"
                        + "                var i;\n"
                        + "\n"
                        + "                for (i = 0; i < acc.length; i++) {\n"
                        + "                    acc[i].addEventListener(\"click\", function () {\n"
                        + "                        this.classList.toggle(\"active\");\n"
                        + "                        var panel = this.nextElementSibling;\n"
                        + "\n"
                        + "                        if (panel.style.display === \"block\") {\n"
                        + "                            panel.style.display = \"none\";\n"
                        + "                        } else {\n"
                        + "                            panel.style.display = \"block\";\n"
                        + "                        }\n"
                        + "                    });\n"
                        + "                }");
                out.println("</script>");
                out.println("<script>"
                        + "var coll = document.getElementsByClassName(\"collapsible\");\n"
                        + "                var i;\n"
                        + "\n"
                        + "                for (i = 0; i < coll.length; i++) {\n"
                        + "                    coll[i].addEventListener(\"click\", function () {\n"
                        + "                        this.classList.toggle(\"active\");\n"
                        + "                        var content = this.nextElementSibling;\n"
                        + "                        if (content.style.display === \"block\") {\n"
                        + "                            content.style.display = \"none\";\n"
                        + "                        } else {\n"
                        + "                            content.style.display = \"block\";\n"
                        + "                        }\n"
                        + "                    });\n"
                        + "                }");
                out.println("</script>");
                
                out.println("</html>");

            }
RequestDispatcher rd1 = request.getRequestDispatcher("footer.html");
                rd1.include(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(inboxfromDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
