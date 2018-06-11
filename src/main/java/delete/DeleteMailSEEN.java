/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delete;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Flags.Flag;
import javax.mail.search.FlagTerm;

public class DeleteMailSEEN {

    Properties properties = null;
    private Session session = null;
    private Store store = null;
    private Folder inbox = null;
    private Message messages[];
//    private String userName = "dummy2263@gmail.com";// provide user name
//    private String password = "dummy2263!";// provide password

    public DeleteMailSEEN() {
    }

    public void getMails(int emailNo,String username,String passward) {
        getConnection(username,passward);
        readMails();
        if (null != messages && messages.length > 0) {
            System.out.println("trying to delete  first mail...");
            deleteMessage(messages[emailNo]);
            closeSession();
        }
    }

    public void getConnection(final String username, final String passward) {
        properties = new Properties();
        properties.setProperty("mail.host", "imap.gmail.com");
        properties.setProperty("mail.port", "993");
        properties.setProperty("mail.transport.protocol", "imaps");
        session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, passward);
            }
        });
        try {
            store = session.getStore("imaps");
            store.connect();
            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void readMails() {
        try {
            if (null != inbox) {
                messages = inbox.search(new FlagTerm(new Flags(Flag.SEEN), true));
                System.out.println("Number of mails = " + messages.length);
                for (int i = 0; i < messages.length; i++) {
                    Message message = messages[i];
                    Address[] from = message.getFrom();
                    System.out.println("-------------------------------");
                    System.out.println("Date : " + message.getSentDate());
                    System.out.println("From : " + from[0]);
                    System.out.println("Subject: " + message.getSubject());
                    System.out.println("Content :");
                    processMessageBody(message);
                    System.out.println("--------------------------------");
                }
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void closeSession() {
        if (null != inbox && null != store) {
            try {
                inbox.close(true);
                store.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteMessage(Message message) {
        try {
            message.setFlag(Flags.Flag.DELETED, true);
            System.out.println("deleted mail");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void processMessageBody(Message message) {
        try {
            Object content = message.getContent();
            // check for string
            // then check for multipart
            if (content instanceof String) {
                System.out.println(content);
            } else if (content instanceof Multipart) {
                Multipart multiPart = (Multipart) content;
                procesMultiPart(multiPart);
            } else if (content instanceof InputStream) {
                InputStream inStream = (InputStream) content;
                int ch;
                while ((ch = inStream.read()) != -1) {
                    System.out.write(ch);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void procesMultiPart(Multipart content) {
        try {
            int multiPartCount = content.getCount();
            for (int i = 0; i < multiPartCount; i++) {
                BodyPart bodyPart = content.getBodyPart(i);
                Object o;
                o = bodyPart.getContent();
                if (o instanceof String) {
                    System.out.println(o);
                } else if (o instanceof Multipart) {
                    procesMultiPart((Multipart) o);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        DeleteMailSample sample = new DeleteMailSample();
//        int emailno=1;
//        sample.getMails(emailno);
    }
}
