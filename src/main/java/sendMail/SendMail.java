package sendMail;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class SendMail {
	public static void Send(String to, String msg) throws IOException {
		  final String from = "dariusgligor22@gmail.com"; // from address. As this is using Gmail SMTP.
		  final String password = "LPX525qlr"; // password for from mail address. 
		 
		  Properties prop = new Properties();
		  prop.put("mail.smtp.host", "smtp.gmail.com");
		  prop.put("mail.smtp.port", "465");
		  prop.put("mail.smtp.auth", "true");
		  prop.put("mail.smtp.socketFactory.port", "465");
		  prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		 
		  Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
		   protected PasswordAuthentication getPasswordAuthentication() {
		    return new PasswordAuthentication(from, password);
		   }
		  });
		 
		  try {
		 
		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(from));
		   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		   message.setSubject("Message from Java Simplifying Tech");
		    
		   MimeBodyPart mimeBodyPart = new MimeBodyPart();
		   mimeBodyPart.setContent(msg, "text/html");
		     
		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(mimeBodyPart);
		    
		   MimeBodyPart attachmentBodyPart = new MimeBodyPart();
		   attachmentBodyPart.attachFile(new File("C://Users//dariu//Downloads//crs.xls"));
		   multipart.addBodyPart(attachmentBodyPart);
		   message.setContent(multipart);
		 
		   Transport.send(message);
		 
		   System.out.println("Mail successfully sent..");
		 
		  } catch (MessagingException e) {
		   e.printStackTrace();
		  }
		 }
	public static void Send(String to, String msg,String filePath) throws IOException {
		  final String from = "dariusgligor22@gmail.com"; // from address. As this is using Gmail SMTP.
		  final String password = "LPX525qlr"; // password for from mail address. 
		 
		  Properties prop = new Properties();
		  prop.put("mail.smtp.host", "smtp.gmail.com");
		  prop.put("mail.smtp.port", "465");
		  prop.put("mail.smtp.auth", "true");
		  prop.put("mail.smtp.socketFactory.port", "465");
		  prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		 
		  Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
		   protected PasswordAuthentication getPasswordAuthentication() {
		    return new PasswordAuthentication(from, password);
		   }
		  });
		 
		  try {
		 
		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(from));
		   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		   message.setSubject("Message from Java Simplifying Tech");
		    
		   MimeBodyPart mimeBodyPart = new MimeBodyPart();
		   mimeBodyPart.setContent(msg, "text/html");
		     
		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(mimeBodyPart);
		    
		   MimeBodyPart attachmentBodyPart = new MimeBodyPart();
		   attachmentBodyPart.attachFile(new File(filePath));
		   multipart.addBodyPart(attachmentBodyPart);
		   message.setContent(multipart);
		 
		   Transport.send(message);
		 
		   System.out.println("Mail successfully sent..");
		 
		  } catch (MessagingException e) {
		   e.printStackTrace();
		  }
		 }
		}