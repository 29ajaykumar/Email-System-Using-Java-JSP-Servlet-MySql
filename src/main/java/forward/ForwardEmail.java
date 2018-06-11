package forward;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class ForwardEmail {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        final String username = "dummy2263@gmail.com";
        final String password = "dummy2263!";
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            //Message message = new MimeMessage(session);
            //message.setFrom(new InternetAddress("dineshonjava@gmail.com"));
            //message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("admin@dineshonjava.com"));
            // Get a Store object and connect to the current host   
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", username, password);

            //Create a Folder object and open the folder  
            Folder folder = store.getFolder("inbox");
            folder.open(Folder.READ_ONLY);

            Message message = folder.getMessage(1);
            // Get all the information from the message  
            String from = InternetAddress.toString(message.getFrom());
            if (from != null) {
                System.out.println("From: " + from);
            }
            String replyTo = InternetAddress.toString(message.getReplyTo());
            if (replyTo != null) {
                System.out.println("Reply-to: " + replyTo);
            }
            String to = InternetAddress.toString(message.getRecipients(Message.RecipientType.TO));
            if (to != null) {
                System.out.println("To: " + to);
            }

            String subject = message.getSubject();
            if (subject != null) {
                System.out.println("Subject: " + subject);
            }
            Date sent = message.getSentDate();
            if (sent != null) {
                System.out.println("Sent: " + sent);
            }
            System.out.println(message.getContent());

            // compose the message to forward  
            Message emailMessage
                    = folder.getMessage(Integer.parseInt("1"));

            Message mimeMessage = new MimeMessage(session);
            mimeMessage = (MimeMessage) emailMessage.reply(false);
            mimeMessage.setFrom(new InternetAddress(username));
            mimeMessage.setSubject("Fwd: " + mimeMessage.getSubject());
            mimeMessage.setText("This is forward");
            mimeMessage.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("ajaykumar.bhumca2015@gmail.com"));

            Transport.send(mimeMessage);

            System.out.println("message forwarded ....");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
