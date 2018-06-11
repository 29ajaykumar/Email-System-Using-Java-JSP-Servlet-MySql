<%-- 
    Document   : register
    Created on : Apr 19, 2018, 10:29:51 PM
    Author     : ajay
--%>

<!DOCTYPE HTML>


<html>
    <head>
        <title>Mail Services</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <!--5grid--><script src="css/5grid/viewport.js"></script><!--[if lt IE 9]><script src="css/5grid/ie.js"></script><![endif]--><link rel="stylesheet" href="css/5grid/core.css" />
        <link rel="stylesheet" href="css/style.css" />
        <!--[if IE 9]><link rel="stylesheet" href="css/style-ie9.css" /><![endif]-->
    </head>
    <body>
        <!-- ********************************************************* -->
        <div id="header-wrapper">
            <div class="5grid">
                <div class="12u-first">

                    <header id="header">
                        <h1><a href="#">Mail services</a></h1>

                    </header>

                </div>
            </div>
        </div>

        <br><br><br><br>
        <%
            if (request.getAttribute("registererror") != null) {
                String registererror = (String) request.getAttribute("registererror");
                out.print("<font style='color:red'>" + registererror + "</font>");
            }%>




        <form method="post" action="validFilter">
            <center>
                <table border="1" width="50%" cellpadding="5">
                    <thead>
                        <tr>
                            <th colspan="3">Register Here</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Full Name</td>
                            <td><input type="text" placeholder="Enter Name" name="uname" size="30"  value="<%
                                String uname = (String) request.getParameter("uname");
                                if (uname != null) {
                                    out.print(uname);
                                }

                                       %>" /></td>
                            <td><%                                String unameMSG = (String) request.getAttribute("unameMSG");
                                if (unameMSG != null) {
                                    out.print(unameMSG);
                                }
                                %>
                            </td>
                        </tr>
                        <tr>
                            <td>Email</td>
                            <td><input type="text" placeholder="Enter Email ID" name="email" size="30" value="<%
                                String email = (String) request.getParameter("email");
                                if (email != null) {
                                    out.print(email);
                                }

                                       %>" /></td>
                            <td><%                                String emailMSG1 = (String) request.getAttribute("emailMSG1");
                                String emailMSG2 = (String) request.getAttribute("emailMSG2");
                                if (emailMSG1 != null) {
                                    out.print(emailMSG1);
                                }
                                if (emailMSG2 != null) {
                                    out.print(emailMSG2);
                                }
                                %>


                        </tr>
                        <tr>
                            <td>Password</td>
                            <td><input type="password"  placeholder="Enter your gmail password" name="pass" size="30" value="<%
                                String pass = (String) request.getParameter("pass");
                                if (pass != null) {
                                    out.print(pass);
                                }

                                       %>" /></td>
                            <td><%                                String passMSG = (String) request.getAttribute("passMSG");
                                if (passMSG != null) {
                                    out.print(passMSG);
                                }

                                %>

                            </td>
                        </tr>
                        <tr>
                            <td>Repeat Password</td>
                            <td><input type="password" placeholder="repeat password" name="repass" size="30" value="" /></td>
                            <td><%                               String repassMSG = (String) request.getAttribute("repassMSG");
                                if (repassMSG != null) {
                                    out.print(repassMSG);
                                }
                                %>

                            </td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Register" /></td>
                            <td><input type="reset" value="Reset" /></td>
                        </tr>
                        <tr>
                            <td colspan="2">Already Registered!! <a href="login.jsp">Login Here</a></td>
                        </tr>
                    </tbody>
                </table>
            </center>
        </form>
        <br><br><br><br><br><br><br>
        <div id="footer">	
            <div id="copyright">
                <br>	<h4>The Intranet Mailing is applicable to this fast growing world where every qualified 
                    person is in urgent need of job,<br> they join places, working at odd times. In these circumstances the Intranet Mailing proves its worth, 
                    if the organization has an <br>Intranet Mailing facility available to all its employees then each 
                    employee can register himself and send mails to any other registered employee ..</h4>

                <h5>Made By Ajay Kumar <a href="https://www.linkedin.com/in/ajay-kumar-613b60131/">about Ajay</a>.</h5>

            </div>

        </div>
        <br>

    </body>

    <!-- ********************************************************* -->

</html>