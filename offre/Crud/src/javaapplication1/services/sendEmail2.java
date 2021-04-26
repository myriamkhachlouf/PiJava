/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1.services;

/**
 *
 * @author Mohamed
 */

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class sendEmail2 {
    
    
   public static void sendToken(String sendTo, int token) {
      // Recipient's email ID needs to be mentioned.
      String to = sendTo;

      // Sender's email ID needs to be mentioned
      String from = "khedmtech@gmail.com";
      final String username = "khedmtech@gmail.com";//change accordingly
      final String password = "azerty123123";//change accordingly

      // Assuming you are sending email through relay.jangosmtp.net
      String host = "smtp.gmail.com";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "25");

      
      // Get the Session object.
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
	   }
         });

      try 
      {
	   // Create a default MimeMessage object.
	   Message message = new MimeMessage(session);
	
	   // Set From: header field of the header.
	   message.setFrom(new InternetAddress(from));
	
	   // Set To: header field of the header.
	   message.setRecipients(Message.RecipientType.TO,
               InternetAddress.parse(to));
	
	   // Set Subject: header field
	   message.setSubject("Testing Subject");
	
	   // Now set the actual message
	   message.setText("Your code is " + token);

	   // Send message
	   Transport.send(message);

	   System.out.println("Sent message successfully....");

      } 
      catch (MessagingException e) 
      {
          System.out.println("javaapplication1.services.sendEmail2.sendToken()" + e.getMessage() + e.getCause());
         throw new RuntimeException(e);
      }
   }
}


