/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entities.Candidature;
import javaapplication1.entities.users;
import Utils.Maconnexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.mail.internet.AddressException;
/**
 *
 * @author azizs
 */
public class ServiceCandidature {
          Connection cnx;
    private List<Candidature> candidatures;
    private PreparedStatement pre;
    private Statement ste;
    public ServiceCandidature() {
cnx =Maconnexion.getInstance().getConnection();
        }  
    
   public boolean AddCandidature(Candidature e) throws SQLException  {
  
        
        String req = "INSERT INTO candidature (id,candidat_id,offre_id,date_candidature,pdf) VALUES ('"+e.getId()+"','"+e.getCandidat_id()+"','"+e.getOffre_id()+"','"+e.getDate()+"','"+e.getPdf()+"')";
         Connection cnx =null;
        Statement st = null;
         try {
            cnx = Maconnexion.getInstance().getConnection();
            st = cnx.createStatement();
            st.executeUpdate(req);
            return true;


        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally {

    if (st != null) {
        try {
            st.close();
        } catch (SQLException l) { /* Ignored */}
    }
    }    
 }
     public void supprimerCandidature(Candidature a){
       String req="delete from Candidature where id=?";
       
        try {
            
            PreparedStatement stm;
            stm=cnx.prepareStatement(req);
            stm.setInt(1,a.getId() );
            int i=stm.executeUpdate();
            System.out.println(i+ " Candidature suprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   }
      public int ModifierCandidature(int id,Candidature a) throws SQLException {
        if(chercher(id)){
        
        pre=cnx.prepareStatement("UPDATE Candidature SET offre_id=?, candidat_id = ? , date_candidature = ? , pdf = ?  WHERE id = "+id+"");
    try{     
    pre.setInt(1, a.getOffre_id());
    pre.setInt(2, a.getCandidat_id());
    pre.setString(3, a.getDate());
    pre.setString(4, a.getPdf());
   
    
    
    pre.executeUpdate();
    }
    catch (SQLException e){
      System.out.println(e.getMessage());  
    }
    return 1;}
        return 0;
    }
      public boolean chercher(int id) throws SQLException {
        String req="select * from Candidature";
        List<Integer> list = new ArrayList<>();
        
        try {
            ste=cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffres.class.getName()).log(Level.SEVERE, null, ex);
        }
        list.forEach(System.out::println);
        return list.contains(id);
    }
      
       public ObservableList<Candidature> afficherCandidature() throws SQLException {
     Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Candidature> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM Candidature";

        try {
            cnx = Maconnexion.getInstance().getConnection();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            Candidature candidature;
            while (rs.next()){
               candidature = new Candidature(rs.getInt("id"),rs.getInt("candidat_id"),rs.getInt("offre_id"),rs.getString("date_candidature"),rs.getString("pdf"));
               liste.add(candidature);
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException e) { /* Ignored /}
    }
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { / Ignored */}
    }
    }
        return liste; 
 }
       public void sendRes(Candidature A, int id,String path) throws IOException, AddressException {
      
         Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        String requette = "SELECT * FROM users Where id="+id+"";
        try {
            cnx = Maconnexion.getInstance().getConnection();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            int a;
            while (rs.next()){
              
           
                
                
                
                final String username ="khedmtech@gmail.com";
        final String password ="azerty123123";
        InternetAddress from = new InternetAddress("khedmtech@gmail.com");
        String to = rs.getString("email");

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator(){
        
           protected PasswordAuthentication getPasswordAuthentication(){
               return new PasswordAuthentication(username,password);
           }
        });
        MimeMessage msg =new MimeMessage(session);
        try{
            msg.setFrom(from);
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject("Réservation Plan "+A.getDate()+"");
            Multipart con = new MimeMultipart();
            MimeBodyPart text =new MimeBodyPart();
            text.setText("Présentez ce QRcode le jour du plan");
            MimeBodyPart img = new MimeBodyPart();
            img.attachFile(path);
            con.addBodyPart(text);
            con.addBodyPart(img);
            msg.setContent(con);
            
            Transport.send(msg);
        }catch(MessagingException e){}
            }
            
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
   
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }
     
 } 
 
    public String QR (Candidature A, int id) {
        
      try {
                    
               String  qr="";    
         Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        String requette = "SELECT * FROM users Where id="+id+"";
            cnx = Maconnexion.getInstance().getConnection();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
             while (rs.next()){
                 qr=help(A,rs.getString("nom"));
             }
             return qr;
      
      } catch (Exception e) {
            System.err.println(e);
            return "vide";
        }
        
       }
    
    
    
    
    
    
    public String help(Candidature A, String id){
         
              String filePath = "D:\\wamp64\\www\\copy java 4\\PiJava\\offre\\Crud\\src\\images\\"+A.getDate()+id+".png";
             try{
            String qrCodeData = "Votre candidature est le  "+A.getDate()+"Client "+id+"";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
             }catch (Exception e){}
             
                return filePath;
            
    } 
   
    
}
