/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unread.donatt;

import com.DB.GetCon;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
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
@WebServlet(name = "unreadedmail", urlPatterns = {"/unreadedmail"})
public class unreadedmail extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String host = "pop.gmail.com";
        String port = "995";
        String userName = "dummy2263@gmail.com";
        String password = "dummy2263!";
        String saveDirectory = "C:\\Users\\ajay\\Desktop\\attch";
        setSaveDirectory(saveDirectory);

        Properties properties = new Properties();

        // server setting
        properties.put("mail.pop3.host", host);
        properties.put("mail.pop3.port", port);

        // SSL setting
        properties.setProperty("mail.pop3.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.pop3.socketFactory.fallback", "false");
        properties.setProperty("mail.pop3.socketFactory.port",
                String.valueOf(port));
        properties.put("mail.store.protocol", "pop3");

        properties.put("mail.pop3.starttls.enable", "true");
        Session session = Session.getDefaultInstance(properties);

        try {
            // connects to the message store
            Store store = session.getStore("pop3s");
            store.connect(host, userName, password);

            // opens the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_ONLY);

            // fetches new messages from server
            Message[] arrayMessages = folderInbox.getMessages();
            RequestDispatcher rd = request.getRequestDispatcher("header.jsp");
            rd.include(request, response);
            if(arrayMessages.length>0){
            
            for (int i = 0; i < arrayMessages.length; i++) {

                Message message = arrayMessages[i];
                Address[] fromAddress = message.getFrom();
                String froms = fromAddress[0].toString();
                String subject = message.getSubject();
                String sentDate = message.getSentDate().toString();

                String contentType = message.getContentType();
                String messageContent = "";

                // store attachment file name, separated by comma
                String attachFiles = "";

                if (contentType.contains("multipart")) {
                    // content may contain attachments
                    Multipart multiPart = (Multipart) message.getContent();
                    //System.out.println(multiPart);
                    int numberOfParts = multiPart.getCount();
                    for (int partCount = 0; partCount < numberOfParts; partCount++) {
                        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                            // this part is attachment
                            String fileName = part.getFileName();
                            attachFiles += fileName + ", ";
                            part.saveFile(saveDirectory + File.separator + fileName);
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
                messageContent = getTextFromMessage(message);
                String insertMessage = "INSERT INTO inbox"
                        + "(userName, froms, subject, sentDate, messageContent,attachFiles) VALUES"
                        + "(?,?,?,?,?,?)";
                try {
                    PreparedStatement ps = con.prepareStatement(insertMessage);
                    ps.setString(1, userName);
                    ps.setString(2, froms);
                    ps.setString(3, subject);
                    ps.setString(4, sentDate);
                    ps.setString(5, messageContent);
                    ps.setString(6, attachFiles);

                    int rs = ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(unreadedmail.class.getName()).log(Level.SEVERE, null, ex);
                }
                // print out details of each message
//                System.out.println("Message #" + (i + 1) + ":");
//                System.out.println("\t From: " + froms);
//                System.out.println("\t Subject: " + subject);
//                System.out.println("\t Sent Date: " + sentDate);
//                System.out.println("\t Message: " + messageContent);
//                System.out.println("\t Attachments: " + attachFiles);
//           

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
                out.println("<button class=\"accordion\"><b>From</b>" + froms + "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<b>Subject</b>" + subject + " </button>");
                out.println("<div class=\"panel\">");
                out.println("<table id=\"customers\">");
                out.println("<tr>");
                out.println("<th>Content</th>");
                out.println("<th>Date</th>");
                out.println("<th>Attachments</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>" + messageContent + "</td>");
                out.println("<td>" + sentDate + "</td>");
                out.println("<td>" + attachFiles + "</td>");
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
            }else{
                out.println("<br><br><br><br><br><center style = 'color:red'>No new Mail</center><br><br><br><br><br><br>");
            }
            RequestDispatcher rd1 = request.getRequestDispatcher("footer.html");
            rd1.include(request, response);
            // disconnect
            folderInbox.close(false);
            store.close();
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider for pop3.");
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store");
        } catch (IOException ex) {
        }

    }
    private String saveDirectory;
    Connection con = GetCon.getCon();

    /**
     * Sets the directory where attached files will be stored.
     *
     * @param dir absolute path of the directory
     */
    public void setSaveDirectory(String dir) {
        this.saveDirectory = dir;
    }

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

}
