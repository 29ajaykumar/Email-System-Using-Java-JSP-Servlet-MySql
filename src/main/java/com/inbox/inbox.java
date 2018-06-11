/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author ajay
 */
@WebServlet(name = "inbox", urlPatterns = {"/inbox"})
public class inbox extends HttpServlet {
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

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Inbo</title>");
                out.println("</head>");
                out.println("<body>");
                if(i==0){
                    out.println("<h1><center><br>INBOX</center><br><br><br> </h1>");
                }
                
                out.println("<b>-------------------------------<br>");
                out.println("Date : " + message.getSentDate()+"<br>");
                out.println("From : " + from[0]+"<br>");
                out.println("Subject: " + message.getSubject()+"<br>");
                out.println("Content :");
                out.println(getTextFromMessage(message));
                out.println("<br>--------------------------------<br>");

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
