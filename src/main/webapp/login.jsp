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

        <div id="main">
            <div class="5grid">
                <div class="main-row">
                    <div class="4u-first">

                        <section>
                            <h2>Welcome to Mailservices!</h2>
                            <p>Hi! This is <strong>Mail Services</strong>, a free service by Google.
                                You can perform all email operation,Currently  it stores the information in database
                                it's free of cost.Let's try it..
                            </p>

                        </section>

                    </div>
                    <div class="4u">

                        <section>

                            <ul class="small-image-list">
                                <li>
                                    <a href="#"><img src="css/images/ajay.png" alt="" class="left" /></a>
                                    <span>

                                        <h3 style="color:#007897;"> Login or Signup</h3><br>
                                        <div>
                                            <%
                                            if (request.getAttribute("alreadyregister") != null) {
                                                String alreadyregister = (String) request.getAttribute("alreadyregister");
                                                out.print("<font style='color:red'>" + alreadyregister + "</font>");
                                            }
                                            if (request.getAttribute("register") != null) {
                                                String register = (String) request.getAttribute("register");
                                                out.print("<font style='color:red'>" + register + "</font>");
                                            }

                                            if (request.getAttribute("Error1") != null) {
                                                System.out.print(request.getAttribute("Error1"));
                                                String Error1 = (String) request.getAttribute("Error1");
                                                out.print("<font style='color:red'>" + Error1 + "</font>");

                                            }
                                            if (request.getAttribute("logout") != null) {
                                                System.out.print(request.getAttribute("logout"));
                                                String logout = (String) request.getAttribute("logout");
                                                out.print("<font style='color:navy'>" + logout + "</font>");
                                            }


                                            %>


                                            <form action="LoginServlet" method="post">

                                                <table style="table-layout: fixed">

                                                    <tr><td>Email id</td><td> <input type="text" name="email"></br><br></td></tr>

                                                    <tr><td>Password:</td><td> <input type="password" name="pass"></br><br></td></tr>

                                                    <tr><td></td><td><input type="submit" value="Sign in">&nbsp &nbsp &nbsp  &nbsp  &nbsp  <a href="register.jsp">Signup</a> </td>
                                                    </tr>
                                                </table>
                                            </form>

                                    </span>
                                </li>



                            </ul>
                        </section>

                    </div>
                    <div class="4u">

                        <section>
                            <h2>How to use it?</h2>
                            <div class="6u-first">
                                <ul class="link-list">
                                    <li>First create an account with gmail password</li>
                                    <li>After that, you login and enjoy google G-mail service</li>
                                    <li>In a new way.</li>
                                </ul>
                            </div>

                        </section>

                    </div>
                </div>

            </div>
        </div>
                                            
                                            admin login here <a href="adminlogin.jsp">Click</a>
    </body>

<jsp:include page="footer.html"></jsp:include>

    <!-- ********************************************************* -->

</html>