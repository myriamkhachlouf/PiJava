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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import entities.donnéeevent;
import entities.evenement;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.evenementCrud;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ModifiereventController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField logo;
    @FXML
    private TextField desc;
    @FXML
    private TextField email;
    @FXML
    private DatePicker datttt;
    
     evenement CLmod = donnéeevent.getEvent();
    evenement newCL = new evenement();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        initializeAll(); 
        
    }    
    
    
     public void initializeAll(){
        
        nom.setText(CLmod.getNom());
        desc.setText(CLmod.getDescription());
        email.setText(CLmod.getEmail());
        logo.setText(CLmod.getLogo());
        
        
    }
    

    @FXML
    private void modifierevent(ActionEvent event) {
        
        evenement c = new evenement();        
        
        c.setNom(nom.getText());
        c.setDescription(desc.getText());
        
        c.setEmail(email.getText());
        c.setLogo(logo.getText());
        c.setDate(datttt.getValue().toString());
        
        evenementCrud.updateEvent(CLmod,c);
        
        String title ="event modifié avec succés";
               TrayNotification notif=new TrayNotification();
                AnimationType Type =AnimationType.POPUP;
                
                notif.setAnimationType(Type);    
        notif.setTitle(title);
        notif.setNotificationType(NotificationType.INFORMATION);
        notif.showAndDismiss(javafx.util.Duration.millis(3000));
        
        try {
        Parent loader = FXMLLoader.load(getClass().getResource("event.fxml"));
            //  Parent root  = loader.load();
            Scene  scene = new Scene(loader);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Les Clients");
            window.setScene(scene);
            window.show();
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } 
        
        
    }
    
}
