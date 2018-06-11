<jsp:include page="header.jsp"></jsp:include>

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

<div id="main">
    <div class="5grid">
        <div class="main-row">
            <div class="4u-first">

                <section>
                    <h2>Welcome to Mailservices!</h2>
                    <p>Hi! This is <strong>Mail Services</strong>, a free service by Google for Mailing system.
                        You can perform all email operation,Currently  it stores the information in database
                        it's free of cost.Let's try it..
                    </p>

                </section>

            </div>
            <div class="4u">

                <section>

                    <ul class="small-image-list">

                        <li>
                            <div>
                                <table>
                                    <tr><td><font style="color: navy;">Contact us:</font></td></tr>
                                    <tr><td><br/> </td></tr>
                                    <tr><td>Cont. Person: </td><td>Ajay Kumar</td></tr>
                                    <tr><td>Contact No: </td><td>8736009982</td></tr>
                                    <tr><td>Email id: </td><td>ajaykumar.bhumca2015@gmail.com</td></tr>
                                    <tr><td>Organisation: </td><td> BHU/MCA</td></tr>

                                    <tr><td><br/> </td></tr>
                                    <tr><td><br/> </td></tr>
                                    <tr><td><br/> </td></tr>

                                </table>
                            </div>

                        </li>			

                        <li>

                            </span>




                    </ul>
                </section>

            </div>
            <div class="4u">

                <section>
                    <h2>What is java mail API?</h2>
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