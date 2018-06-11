<jsp:include page="header.jsp"></jsp:include>


<html>
<head>
<title>Send an e-mail with attachment</title>
</head>
<body>
    
      
                                <%
                                    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");//HTTP 1.1
                                    response.setHeader("Pragma","no-cache");//HTTP 1.0
                                    response.setHeader("Expires","0");// for proxy
                                    
                                    
                                    
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
                                    if (request.getAttribute("compose") != null) {
                                        String compose = (String) request.getAttribute("compose");
                                        out.print("<font style='color:navy'>" + compose + "</font>");
                                    }
                                    if (request.getAttribute("composeerr") != null) {
                                        String composeerr = (String) request.getAttribute("composeerr");
                                        out.print("<font style='color:navy'>" + composeerr + "</font>");
                                    }
                                %>


    
    
    <form action="SendMailAttachServlet" method="post" enctype="multipart/form-data">
        <table border="0" width="60%" align="center">
            <caption><h2>Send New E-mail</h2></caption>
            <tr>
                <td width="50%">Recipient address </td>
                <td><input type="text" name="recipient" size="50"/></td>
            </tr>
            <tr>
                <td>Subject </td>
                <td><input type="text" name="subject" size="50"/></td>
            </tr>
            <tr>
                <td>Content </td>
                <td><textarea rows="10" cols="39" name="content"></textarea> </td>
            </tr>
            <tr>
                <td>Attach file </td>
                <td><input type="file" name="file" multiple="" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Send"/></td>
            </tr>
        </table> 
    </form>
</body>
</html>

<jsp:include page="footer.html"></jsp:include>