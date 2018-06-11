/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unread.donatt;

/**
 *
 * @author ajay
 */
import com.DB.GetCon;
import java.io.File;
import java.io.IOException;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

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

public class DowAttAndSetInfoInDB {

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

    /**
     * Downloads new messages and saves attachments to disk if any.
     *
     * @param host
     * @param port
     * @param userName
     * @param password
     */
    public int downloadEmailAttachments(String host, String port,
            String userName, String password) throws SQLException {
        Properties properties = new Properties();
        int newMess = 0;
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
            store.connect(host,userName, password);

            // opens the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_ONLY);

            // fetches new messages from server
            Message[] arrayMessages = folderInbox.getMessages();

            for (int i = 0; i < arrayMessages.length; i++) {
                newMess++;
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
                PreparedStatement ps = con.prepareStatement(insertMessage);
                ps.setString(1, userName);
                ps.setString(2, froms);
                ps.setString(3, subject);
                ps.setString(4, sentDate);
                ps.setString(5, messageContent);
                ps.setString(6, attachFiles);

                int rs = ps.executeUpdate();

                // print out details of each message
                System.out.println("Message #" + (i + 1) + ":");
                System.out.println("\t From: " + froms);
                System.out.println("\t Subject: " + subject);
                System.out.println("\t Sent Date: " + sentDate);
                System.out.println("\t Message: " + messageContent);
                System.out.println("\t Attachments: " + attachFiles);
            }

            // disconnect
            folderInbox.close(false);
            store.close();
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider for pop3.");
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store");
        } catch (IOException ex) {
        }
        return newMess;
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
        MimeMultipart mimeMultipart)  throws MessagingException, IOException{
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
        } else if (bodyPart.getContent() instanceof MimeMultipart){
            result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
        }
    }
    return result;
}
    

    /**
     * Runs this program with Gmail POP3 server
     *
     * @param args
     */
    public static void main(String[] args) throws SQLException {
//        String host = "pop.gmail.com";
//        String port = "995";
//        String userName = "dummy2263@gmail.com";
//        String password = "dummy2263!";
//
//        String saveDirectory = "C:\\Users\\ajay\\Desktop\\attch";
//
//        DowAttAndSetInfoInDB receiver = new DowAttAndSetInfoInDB();
//        receiver.setSaveDirectory(saveDirectory);
//        receiver.downloadEmailAttachments(host, port, userName, password);

    }
}
