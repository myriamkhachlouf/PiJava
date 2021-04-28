/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Mahmoud
 */
public class SendMail {
    public static void sendMail(String recepient,String reasons) throws Exception{ 
    System.out.println("Preparing to send email!");
        Properties properties=new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        String myAccount="khedmtech@gmail.com";
         String password="azerty123123";
         Session session=Session.getInstance(properties,new Authenticator(){
          @Override
          protected PasswordAuthentication getPasswordAuthentication(){
              return new PasswordAuthentication(myAccount,password);
          }
         });
         Message message= prepareMessage(session,myAccount,recepient,reasons);
         Transport.send(message);
         System.out.println("message sent successfully!");
    }

    private static Message prepareMessage(Session session, String myAccount, String recepient,String reasons) {
        try {
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccount));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
            message.setSubject("Warning for bad content");
            createPDF(reasons);
           // message.setText(reasons);
            Multipart emailContent = new MimeMultipart();
            //text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("The Admin has Deleted your Post. Open this PDF file for more details");
           //attachement body part
           MimeBodyPart pdfAttachement = new MimeBodyPart();
           pdfAttachement.attachFile("d:/DeletingPostDetails.pdf");
           //attach
           emailContent.addBodyPart(textBodyPart);
           emailContent.addBodyPart(pdfAttachement);
           message.setContent(emailContent);
           return message;
        } catch (Exception ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static void createPDF(String s)
    {
        System.out.println("itext PDF Creating...!");
        
        try {
            Document document=new Document();
            PdfWriter.getInstance(document, new FileOutputStream("d:/DeletingPostDetails.pdf"));
            document.open();
            Font red = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.RED);
            Chunk RedText = new Chunk("Reasons for deleting this post", red);
            Paragraph title = new Paragraph(RedText); 
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(s));
            document.close();
        } catch (Exception ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("itext PDF Created successfully!");
    }
    
}
