/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.teknikindustries.bulksms.SMS;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import entities.formateur;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.formateurCrud;
import tools.MyConnection;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import edu.test.utils.mailxd;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * FXML Controller class
 *
 * @author Bouhejba
 */
public class FormateurController implements Initializable {

    @FXML
    private TextField tnom;
    @FXML
    private TextField tprenom;
    @FXML
    private TextField tstatut;
    @FXML
    private TextField temail;
    @FXML
    private TextField typecontrat;
    @FXML
    private TextField tpassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
    }  
       

    @FXML
    private void ajouterformateur(ActionEvent event) throws IOException, Exception {
        
    if ((tnom.getText().equals(""))&&(tprenom.getText().equals(""))&&(tstatut.getText().equals(""))&&(temail.getText().equals(""))&&(typecontrat.getText().equals(""))&&(tpassword.getText().equals("")))
            {
               Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Vérifier les champs");
         alert.setHeaderText("Look, verifier les chapms");
         alert.setContentText("Ooops, ");

             alert.showAndWait();
             String title ="verifier les champs ";
               TrayNotification notif=new TrayNotification();
                AnimationType Type =AnimationType.FADE;
              notif.setAnimationType(Type);    
        notif.setTitle(title);
        notif.setNotificationType(NotificationType.WARNING);
        notif.showAndDismiss(javafx.util.Duration.millis(3000)); 
            }else{
Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog");
alert.setHeaderText("veuillez vous vraiment ajouter ce fromateurt");
alert.setContentText("vous étes sure ?");
               String title ="Formateur ajouter avec succés";
               TrayNotification notif=new TrayNotification();
                AnimationType Type =AnimationType.POPUP;
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get()== ButtonType.OK){
        String pnom= tnom.getText();
        String prenom= tprenom.getText();
        String pstatut= tstatut.getText();
        String pcontrat= typecontrat.getText();
        String pemail= temail.getText();
        int ppassword = Integer.parseInt(tpassword.getText());
        formateur tp=new formateur(11,pnom,prenom,pstatut,pcontrat,pemail,ppassword);
        formateurCrud tc=new formateurCrud();
        tc.addformateur(tp);
        SMS sms =new SMS();
        //sms.SendSMS("arbi098", "Mohamed55590231", "test test weldek behi nhebek baba ", "+21622597255", "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
        sms.SendSMS("arbi1", "Myriam52659321", "test test weldek behi nhebek baba ", "+21622597255", "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
        
       // sms.SendSMS("arbi10", "Myriam52659321", "test test weldek behi nhebek baba ", "+21622597255", "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
            
            System.out.println("test sms");    
        System.out.println("test sms");
           
        notif.setAnimationType(Type);    
        notif.setTitle(title);
        notif.setNotificationType(NotificationType.SUCCESS);
        notif.showAndDismiss(javafx.util.Duration.millis(3000)); 
     
        
        
   
       
         try {
			MyConnection obj_DBConnection = new MyConnection();
            Connection connection = obj_DBConnection.getConnexion();
            String query = "select * from formateur";
            Statement stmt = null;
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
            	FormateurController.generate_qr(rs.getString("id"),rs.getString("nom"));
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
        Parent loader = FXMLLoader.load(getClass().getResource("PannelEntrepriseFXML.fxml"));
            //  Parent root  = loader.load();
            Scene  scene = new Scene(loader);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Les Types De Sport");
            window.setScene(scene);
            window.show();
            
            }else
        {Parent loader = FXMLLoader.load(getClass().getResource("PannelEntrepriseFXML.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // AdminPageController apc= loader.getController();
        window.setTitle("Affichage page");
        window.setScene(scene);
        window.show();
     
        }
                 
    
    }
    }
    @FXML
    private void consulter(ActionEvent event) throws IOException {
         Parent loader = FXMLLoader.load(getClass().getResource("aficherFormateur.fxml"));
            //  Parent root  = loader.load();
            Scene  scene = new Scene(loader);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Les Types");
            window.setScene(scene);
            window.show();
        
    }

    
   

    
    
 
    public static void generate_qr(String image_name,String qrCodeData) {
        try {
            String filePath = "D:\\wamp64\\www\\PiJava\\offre\\Crud\\src\\qr arbi"+image_name+".png";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                    .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("gestion.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // AdminPageController apc= loader.getController();
        window.setTitle("Affichage page");
        window.setScene(scene);
        window.show();
        
    }
    
    
    
    
    
    
    
}
