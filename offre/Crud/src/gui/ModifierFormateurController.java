/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import entities.formateur;
import entities.donnéeformateur;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import service.formateurCrud;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ModifierFormateurController implements Initializable {

    @FXML
    private TextField md;
    @FXML
    private TextField mnom;
    @FXML
    private TextField mml;
    @FXML
    private TextField mprenom;
    @FXML
    private TextField mstatut;
    @FXML
    private TextField mcon;
    @FXML
    private Button update;
    
    formateur CLmod = donnéeformateur.getFormateur();
    formateur newCL = new formateur();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        mnom.setText(CLmod.getNom());
        mprenom.setText(CLmod.getPrenom());
        mstatut.setText(CLmod.getStatut());
       
        mcon.setText(CLmod.getTypecontrat());
         mml.setText(CLmod.getEmail());
        md.setText(String.valueOf(CLmod.getPassword()));
        
        
        
    }    

    @FXML
    private void update(ActionEvent event) {
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog");
alert.setHeaderText("veuillez vous vraiment modifier ce formateur");
alert.setContentText("vous étes sure ?");
               String title =" formateur modifier avec succés";
               TrayNotification notif=new TrayNotification();
                AnimationType Type =AnimationType.POPUP;
            formateur t = new formateur();        

    t.setNom(mnom.getText());
    t.setPrenom(mprenom.getText());
    t.setStatut(mstatut.getText());
    
    t.setTypecontrat(mcon.getText());
    t.setEmail(mml.getText());

    t.setPassword(Integer.parseInt(md.getText()));

        
        formateurCrud.updateFormateur(CLmod,t);
         Optional<ButtonType> result = alert.showAndWait();
     notif.setAnimationType(Type);    
        notif.setTitle(title);
        notif.setNotificationType(NotificationType.SUCCESS);
        notif.showAndDismiss(javafx.util.Duration.millis(3000)); 
        try {
        Parent loader = FXMLLoader.load(getClass().getResource("aficherFormateur.fxml"));
            //  Parent root  = loader.load();
            Scene  scene = new Scene(loader);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Les formateurs");
            window.setScene(scene);
            window.show();
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    
}
