<%@page import="com.inbox.inboxfromDB"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.DB.GetCon"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>


        <style>
            #customers {
                font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                width: 100%;
            }

            #customers td, #customers th {
                border: 1px solid #ddd;
                padding: 8px;
            }

            #customers tr:nth-child(even){background-color: #f2f2f2;}

            #customers tr:hover {background-color: #ddd;}

            #customers th {
                padding-top: 12px;
                padding-bottom: 12px;
                text-align: left;
                background-color: #4CAF50;
                color: white;
            }

            .accordion {
                background-color: #eee;
                color: #444;
                cursor: pointer;
                padding: 9px;
                width: 100%;
                border: none;
                text-align: left;
                outline: none;
                font-size: 15px;
                transition: 0.4s;
            }

            .active, .accordion:hover {
                background-color: red; 
            }

            .panel {
                padding: 0 0px;
                display: none;
                background-color: white;
                overflow: hidden;
            }
            .btn{
                background-color: #eee;
                color: #444;
                cursor: pointer;
                padding: 9px;
                width: 11%;
                border: none;
                text-align: center;
                outline: none;
                font-size: 15px;
                transition: 0.4s;
            }
        </style>
        

    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <%
            response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");//HTTP 1.1
            response.setHeader("Pragma", "no-cache");//HTTP 1.0
            response.setHeader("Expires", "0");// for proxy

            if (session.getAttribute("username") != null) {
                String username = (String) session.getAttribute("username");
                //out.print("<font style='color:navy'>Welcome " + username + "</font>");
            } else {
                request.setAttribute("Error1", "Plz Do login First");
        %>
        <jsp:forward page="login.jsp"></jsp:forward>
        <%}
        %>







        <%
            String userName = "dummy2263@gmail.com";
            Connection con = GetCon.getCon();
            int i = 0;
            PreparedStatement ps;
            try {
                ps = con.prepareStatement("Select * from inbox where userName = ?");
                ps.setString(1, userName);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    if (i == 0) {%>
    <center><br><b>DBINBOX</center><br><br><br>                 
        <% i++;
            }%>
    <button class="accordion"><b>From: </b><%=rs.getString(3)%> &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<b>Subject:</b><%= rs.getString(4)%> </button>
    <div class="panel">
        <table id="customers">
            <tr>
                <th>Content</th>
                <th>Date</th>
                <th>Attachments</th>
            </tr>
            <tr>
                <td><%=rs.getString(6)%></td>
                <td><%=rs.getString(5)%></td>
                <td><%=rs.getString(7)%></td>
            </tr>
        </table>
        <br><br><center><button style = " color:green" class="btn">Reply</button>
            <button style = " color:red" class="btn">Delete</button>
            <button style = " color:green" class="btn">Forward</button></center><br><br>
    </div>
    <% }

        } catch (SQLException ex) {
            Logger.getLogger(inboxfromDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    %>












    <br><br><br><br><br>
    <h1><center> Unread Messages</center></h1>
    <br><br><br><br><br><br>
    <jsp:include page="footer.html"></jsp:include>
    
            <script>
            var acc = document.getElementsByClassName("accordion");
            var i;

            for (i = 0; i < acc.length; i++) {
                acc[i].addEventListener("click", function () {
                    this.classList.toggle("active");
                    var panel = this.nextElementSibling;
                    if (panel.style.display === "block") {
                        panel.style.display = "none";
                    } else {
                        panel.style.display = "block";
                    }
                });
            }
        </script>
</body>
</html>
