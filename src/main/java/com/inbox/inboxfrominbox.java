package com.inbox;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import reply.ReplyEmailUNSEEN;

/**
 *
 * @author ajay
 */
@WebServlet(name = "inboxfrominbox", urlPatterns = {"/inboxfrominbox"})
public class inboxfrominbox extends HttpServlet {

    public String mess = null;
    Properties properties = null;
    private Session session = null;
    private Store store = null;
    private Folder inbox = null;
    private String userName = "dummy2263@gmail.com";// provide user name
    private String password = "dummy2263!";// provide password

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        properties = new Properties();
        properties.setProperty("mail.host", "imap.gmail.com");
        properties.setProperty("mail.port", "995");
        properties.setProperty("mail.transport.protocol", "imaps");
        session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });
        try {
            store = session.getStore("imaps");
            store.connect();
            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            Message messages[] = inbox.search(new FlagTerm(
                    new Flags(Flags.Flag.SEEN), false));
            ;
            System.out.println("Number of mails = " + messages.length);
            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                Address[] from = message.getFrom();

                
//                System.out.println("-------------------------------");
//                System.out.println("Date : " + message.getSentDate());
//                System.out.println("From : " + from[0]);
//                System.out.println("Subject: " + message.getSubject());
//                System.out.println("Content :");
//                System.out.println(getTextFromMessage(message));
//                System.out.println("--------------------------------");
//

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>inboxfromInbox</title>");
                out.println("<style>"
                        + "            #customers {\n"
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
                        + "                background-color: blue; \n"
                        + "            }\n"
                        + "\n"
                        + "            .panel {\n"
                        + "                padding: 0 0px;\n"
                        + "                display: none;\n"
                        + "                background-color: white;\n"
                        + "                overflow: hidden;"
                        + "}\n"
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
                        + "            }");
                out.println("</style>");

                out.println("</head>");
                out.println("<body>");
                if (i == 0) {
                    out.println("<h1><center><br><b>DBINBOX</center><br><br><br> </h1>");

                }
                out.println("<button class=\"accordion\"><b>From</b>" + from[0] + "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<b>Subject</b>" + message.getSubject() + " </button>");
                out.println("<div class=\"panel\">");
                out.println("<table id=\"customers\">");
                out.println("<tr>");
                out.println("<th>Content</th>");
                out.println("<th>Date</th>");
                out.println("<th>Attachments</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>" + getTextFromMessage(message) + "</td>");
                out.println("<td>" + message.getSentDate() + "</td>");
                //out.println("<td>" + attachFiles + "</td>");
                out.println("<td></td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<br><br><center><button style = \" color:green\" class=\"btn\">Reply</button>");
                out.println("<button style = \" color:red\" class=\"btn\">Delete</button>");
                out.println("<button style = \" color:green\" class=\"btn\">Forward</button></center><br><br>");
                out.println("</div>");
                out.println("<script>"
                        + "var acc = document.getElementsByClassName(\"accordion\");\n"
                        + "            var i;\n"
                        + "            for (i = 0; i < acc.length; i++) {\n"
                        + "                acc[i].addEventListener(\"click\", function () {\n"
                        + "                    this.classList.toggle(\"active\");\n"
                        + "                    var panel = this.nextElementSibling;\n"
                        + "                    if (panel.style.display === \"block\") {\n"
                        + "                        panel.style.display = \"none\";\n"
                        + "                    } else {\n"
                        + "                        panel.style.display = \"block\";\n"
                        + "                    }\n"
                        + "                });\n"
                        + "            }");
                out.println("</script>");
                out.println("</body>");
                out.println("</html>");

            }

            inbox.close(true);
            store.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

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
