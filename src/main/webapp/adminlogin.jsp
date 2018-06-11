<%-- 
    Document   : adminlogin
    Created on : May 6, 2018, 1:53:14 PM
    Author     : ajay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <!--5grid--><script src="css/5grid/viewport.js"></script><!--[if lt IE 9]><script src="css/5grid/ie.js"></script><![endif]--><link rel="stylesheet" href="css/5grid/core.css" />
        <link rel="stylesheet" href="css/style.css" />
    </head>
    <body>
        <center><br><br><br><br>
            <h3 style="color:#007897;"> Admin Login</h3><br><br><br>
            <form action="AdminLoginServlet" method="post">

                <table style="table-layout: fixed">

                    <tr><td>Email id</td><td> <input type="text" name="email"></br><br></td></tr>

                    <tr><td>Password:</td><td> <input type="password" name="pass"><br><br></td></tr>

                    <tr><td></td><td><input type="submit" value="Sign in"></td>
                    </tr>
                </table>
            </form>
        </center>


    </body>
</html>
