package reply;

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.search.FlagTerm;

public class ReplyEmailUNSEEN {

    public String replyEmail(String repliedText, String eno, final String username, final String password) {
        String replySuccess = "";
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
//        final String username = "dummy2263@gmail.com";
//        final String password = "dummy2263!";
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", username, password);

            //Create a Folder object and open the folder  
            Folder folder = store.getFolder("inbox");
            folder.open(Folder.READ_ONLY);

            Message messages[] = folder.search(new FlagTerm(
                    new Flags(Flags.Flag.SEEN), false));
            System.out.println("Total Message - " + messages.length);

            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(System.in));

            for (int i = 0; i < messages.length; i++) {
                Message emailMessage = messages[i];
                System.out.println();
                System.out.println("Email " + (i + 1) + " -");
                System.out.println("Subject - " + emailMessage.getSubject());
                System.out.println("From - " + emailMessage.getFrom()[0]);
            }

            Message message = folder.getMessage(Integer.parseInt(eno));
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
            Message message2 = new MimeMessage(session);
            message2 = (MimeMessage) message.reply(false);
            message2.setSubject("RE: " + message.getSubject());
            message2.setFrom(new InternetAddress(from));
            message2.setReplyTo(message.getReplyTo());

            message2.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Create your new message part  
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(repliedText);

            // Create a multi-part to combine the parts  
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Create and fill part for the forwarded content  
            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setDataHandler(message.getDataHandler());

            // Add part to multi part  
            multipart.addBodyPart(messageBodyPart);

            // Associate multi-part with message  
            message2.setContent(multipart);

            // Send message  
            Transport.send(message2);

            System.out.println("message replied successfully ....");
            replySuccess = "message replied successfully ....";
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
        return replySuccess;
    }

    public static void main(String[] args) {
//        ReplyEmailUNSEEN obj= new ReplyEmailUNSEEN();
//        obj.replyEmail("Replied Email Text. with AJAY", "1");
    }
}
