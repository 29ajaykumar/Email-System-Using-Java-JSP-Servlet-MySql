<jsp:include page="header.jsp"></jsp:include>
    <div id="main">
        <div class="5grid">
            <div class="main-row">
                <div class="4u-first">

                    <section>
                        <h2>Welcome to Mailservices!</h2>
                        <p>Hi! This is <strong>Mail Services</strong>, a free service by Google.
                            You can perform all email operation,Currently  it stores the information in database
                            
                        </p>

                    </section>

                </div>
                <div class="4u">

                    <section>

                        <ul class="small-image-list">

                            <li>
                                <div>
                                <%
                                    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");//HTTP 1.1
                                    response.setHeader("Pragma","no-cache");//HTTP 1.0
                                    response.setHeader("Expires","0");// for proxy
                                    
                                    
                                  

                                    if (session.getAttribute("username") != null) {
                                        String username = (String) session.getAttribute("username");
                                        out.print("<font style='color:navy'>Welcome " + username + "</font>");
                                        request.setAttribute("username", username);
                                    } else {
                                        request.setAttribute("Error1", "Plz Do login First");
                                %>
                                <jsp:forward page="login.jsp"></jsp:forward>
                                <%}
                                %></div>
                                
                                <%
                                  if (session.getAttribute("resultMessage") != null) {
                                        String resultMessage = (String) session.getAttribute("resultMessage");
                                        out.print("<font style='color:navy'> Ajay You" + resultMessage + "</font>");
                                    }
                                    
                                    
                                
                                %>

                        </li>			

                        <li>
                        <marquee direction="up">
                            <font style="color: navy;"><h5>Do any kind of operation related to <br>
                                Mail Services Such as Compose mail,<br>
                                Inbox,And if you have Any Query just<br>
                                Contact Us ..by clicking above links ..</h5></font></marquee></li>
                        </span>




                    </ul>
                </section>

            </div>
            <div class="4u">

                <section>
                    <h2>Gmail has been a service of Google</h2>
                    <div class="6u-first">
                        <ul class="link-list">
                            <li></li>
                            <li></li>

                        </ul>
                    </div>

                </section>

            </div>
        </div>

    </div>
</div>

<jsp:include page="footer.html"></jsp:include>