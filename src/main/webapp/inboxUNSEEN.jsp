<%-- 
    Document   : inboxUNSEEN
    Created on : Apr 29, 2018, 6:11:34 PM
    Author     : ajay
--%>

<%@page import="com.User.UserPass"%>
<%@page import="java.io.File"%>
<%@page import="javax.mail.internet.MimeBodyPart"%>
<%@page import="javax.mail.Multipart"%>
<%@page import="javax.mail.BodyPart"%>
<%@page import="javax.mail.internet.MimeMultipart"%>
<%@page import="javax.mail.MessagingException"%>
<%@page import="java.io.IOException"%>
<%@page import="javax.mail.Address"%>
<%@page import="javax.mail.search.FlagTerm"%>
<%@page import="javax.mail.Flags"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.PasswordAuthentication"%>
<%@page import="javax.mail.Folder"%>
<%@page import="javax.mail.Store"%>
<%@page import="javax.mail.Session"%>
<%@page import="java.util.Properties"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">

<jsp:include page="header.jsp"></jsp:include>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>inboxUNSEEN</title>

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
                .collapsible {
                    background-color: #777;
                    color: white;
                    cursor: pointer;
                    padding: 18px;
                    width: 11%;
                    border: none;
                    text-align: center;
                    outline: none;
                    font-size: 15px;
                }

                .active, .collapsible:hover {
                    background-color: #9E9CD8;
                }

                .content {
                    padding: 0 18px;
                    display: none;
                    overflow: hidden;
                    background-color: #f1f1f1;
                }
            </style>
        </head>



        <body>


        <%
            response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");//HTTP 1.1
            response.setHeader("Pragma", "no-cache");//HTTP 1.0
            response.setHeader("Expires", "0");// for proxy

            if (session.getAttribute("username") != null) {
                String username = (String) session.getAttribute("username");
                //out.print("<font style='color:navy'>Welcome " + username + "</font>");
                //request.setAttribute("username", username);
            } else {
                request.setAttribute("Error1", "Plz Do login First");
        %>
        <jsp:forward page="login.jsp"></jsp:forward>
        <%}
        %>










        <%
            String mess = null;
            Properties properties = null;
            Session session1 = null;
            Store store = null;
            Folder inbox = null;
            final String userName = (String) session.getAttribute("username");
           // out.print(userName);
            UserPass obj = new UserPass();
            final String password = obj.Userpass(userName);
            //out.println("<br>"+password);

//            final String userName = "dummy2263@gmail.com";// provide user name
//            final String password = "dummy2263!";// provide password
            properties = new Properties();
            properties.setProperty("mail.host", "imap.gmail.com");
            properties.setProperty("mail.port", "995");
            properties.setProperty("mail.transport.protocol", "imaps");
            session1 = Session.getInstance(properties,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password);
                }
            });
            store = session1.getStore("imaps");
            store.connect();
            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            Message messages[] = inbox.search(new FlagTerm(
                    new Flags(Flags.Flag.SEEN), false));
        %>
        <center><br><b>UnSeen Mail</b></center><br><br><br>

        <%
            System.out.println("Number of mails = " + messages.length);
            if (messages.length > 0)
                for (int i = 0; i < messages.length; i++) {
                    Message message = messages[i];
                    Address[] from = message.getFrom();
                    //request.setAttribute("emailNo", i);
                    String attachFiles = "";
                    String messageContent = "";
                    String contentType = message.getContentType();
                    if (contentType.contains("multipart")) {
                        // content may contain attachments
                        Multipart multiPart = (Multipart) message.getContent();
                        //System.out.println(multiPart);
                        int numberOfParts = multiPart.getCount();
                        for (int partCount = 0; partCount < numberOfParts; partCount++) {
                            MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                            if ("attachment".equalsIgnoreCase(part.getDisposition())) {
                                // this part is attachment
                                String fileName = part.getFileName();
                                attachFiles += fileName + ", ";
                                part.saveFile("C:\\Users\\ajay\\Desktop\\attch" + File.separator + fileName);
                            } else {
                                // this part may be the message content
                                messageContent = part.getContent().toString();
                            }
                        }

                        if (attachFiles.length() > 1) {
                            attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
                        }
                    } else if (contentType.contains("text/plain")
                            || contentType.contains("text/html")) {
                        Object content = message.getContent();
                        if (content != null) {
                            messageContent = content.toString();
                        }
                    }


        %>
        <button class="accordion"><b>From: </b><%=from[0]%> &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<b>Subject:</b><%=message.getSubject()%> </button>

        <div class="panel">
            <table id="customers">
                <tr>
                    <th>Attachments</th>
                    <th>Content</th>
                    <th>Date</th>

                </tr>
                <tr>
                    <td><%=attachFiles%></td>
                    <td><%=getTextFromMessage(message)%></td>
                    <td><%=message.getSentDate()%></td>

                    <td></td>
                </tr>
            </table>

            <br><br><center>
                <button style = " color:green" class="collapsible">Reply</button>
                <div class="content">
                    <form action="replyServletSEEN">
                        <br><b>Content</b><br>
                        <textarea rows="6" cols="100" name="messageContent">
                        </textarea>
                        <input type="hidden" name="emailNo" value="<%=i + 1%>">
                        <br><br>
                        <input type="submit" value="Reply">
                    </form> 
                </div>
                <button style = " color:red" class="collapsible">Delete</button>
                <div class="content">
                    <form action="deleteUNSEENMailServlet">

                        <input type="hidden" name="deleteemailNo" value="<%=i%>">
                        <br><br>
                        <input type="submit" value="Delete">
                    </form> 
                </div>
                <button style = " color:greenyellow" class="collapsible">Forward</button>
                <div class="content">
                    <form action="forwardServlet">
                        <br><b>Content</b><br>
                        <textarea rows="6" cols="100" name="forwardContent">
                        </textarea><br><br>
                        To:<input type="text" name="forwardemail" size="70">
                        <input type="hidden" name="forwardemailNo" value="<%=i + 1%>">
                        <br><br>
                        <input type="submit" value="Forward">
                    </form> 
                </div>



        </div>


        <%}
            else {
                out.println("<br><br><br><br><br><center style = 'color:red'>No Unseen Mail</center><br><br><br><br><br><br>");

            }

            inbox.close(true);
            store.close();
        %>



















        <%!
            private String getTextFromMessage(Message message) throws MessagingException, IOException {
                String result = "";
                if (message.isMimeType("text/plain")) {
                    result = message.getContent().toString();
                } else if (message.isMimeType("multipart/*")) {
                    MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
                    result = getTextFromMimeMultipart(mimeMultipart);
                }
                return result;
            }

            private String getTextFromMimeMultipart(
                    MimeMultipart mimeMultipart) throws MessagingException, IOException {
                String result = "";
                int count = mimeMultipart.getCount();
                for (int i = 0; i < count; i++) {
                    BodyPart bodyPart = mimeMultipart.getBodyPart(i);
                    if (bodyPart.isMimeType("text/plain")) {
                        result = result + "\n" + bodyPart.getContent();
                        break; // without break same text appears twice in my tests
                    } else if (bodyPart.isMimeType("text/html")) {
                        String html = (String) bodyPart.getContent();
                        result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
                    } else if (bodyPart.getContent() instanceof MimeMultipart) {
                        result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
                    }
                }
                return result;
            }

        %>


























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
        <script>
            var coll = document.getElementsByClassName("collapsible");
            var i;

            for (i = 0; i < coll.length; i++) {
                coll[i].addEventListener("click", function () {
                    this.classList.toggle("active");
                    var content = this.nextElementSibling;
                    if (content.style.display === "block") {
                        content.style.display = "none";
                    } else {
                        content.style.display = "block";
                    }
                });
            }
        </script>
    </body>
</html>
<jsp:include page="footer.html"></jsp:include>
