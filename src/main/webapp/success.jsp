<%-- 
    Document   : success
    Created on : May 6, 2018, 6:43:54 PM
    Author     : ajay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">

<jsp:include page="header.jsp"></jsp:include>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <%
            response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");//HTTP 1.1
            response.setHeader("Pragma", "no-cache");//HTTP 1.0
            response.setHeader("Expires", "0");// for proxy

            if (session.getAttribute("username") != null) {
                String username = (String) session.getAttribute("username");
               // out.print("<font style='color:navy'>Welcome " + username + "</font>");
               // request.setAttribute("username", username);
            } else {
                request.setAttribute("Error1", "Plz Do login First");
        %>
        <jsp:forward page="login.jsp"></jsp:forward>
        <%}
        %>
        
        
        <%
        String resultMessage = request.getAttribute("resultMessage").toString();
        
        %>
        
       <center style='color:green'> <h1><br><br><br><br><br><br>Mail Send SuccessFully</h1></center><br><br><br><br><br><br>
        
        
    </body>
</html>
<jsp:include page="footer.html"></jsp:include>