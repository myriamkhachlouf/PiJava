/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1.services;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author Mohamed
 */
public class SendEmail {
        public void sendToken(String recepient, String resetToken) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "khedmtech@gmail.com";
        //Your gmail password
        String password = "azerty123123";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
            System.out.println("javaapplication1.services.SendEmail.sendToken() 1");
        Message message = prepareMessage(session, myAccountEmail, recepient, resetToken);
            System.out.println("javaapplication1.services.SendEmail.sendToken() 2");

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String token) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("My First Email from Java App");
            String htmlCode = "<h1> WE LOVE JAVA </h1> <br/> <h2><b>Next Line </b></h2> Your Token is " + token;
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            //Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("javaapplication1.services.SendEmail.prepareMessage()");
        }
        return null;
    }
}
