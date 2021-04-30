/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.donnéeformateur;
import entities.formateur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import entities.donnéeformation;
import entities.formation;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import service.formateurCrud;
import service.formationCrud;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ModifierformationController implements Initializable {

    @FXML
    private TextField mref;
    @FXML
    private TextField mperiode;
    @FXML
    private TextField mobj;
    @FXML
    private TextField mdure;
    @FXML
    private TextField mcapacite;
    @FXML
    private TextField mrating;
     formation CLmod = donnéeformation.getFormation();
    formation newCL = new formation();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mref.setText(String.valueOf(CLmod.getReference()));
        mperiode.setText(String.valueOf(CLmod.getPeriode()));
        mobj.setText(CLmod.getObjectif());
        mdure.setText(String.valueOf(CLmod.getDure()));
        mcapacite.setText(String.valueOf(CLmod.getCapacite()));
        mrating.setText(String.valueOf(CLmod.getRating()));
    }    

    @FXML
    private void valider(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog");
alert.setHeaderText("veuillez vous vraiment modifier cettz formation");
alert.setContentText("vous étes sure ?");
               String title =" formation modifier avec succés";
               TrayNotification notif=new TrayNotification();
                AnimationType Type =AnimationType.POPUP;
            formation t = new formation();        

            t.setReference(Integer.parseInt(mref.getText()));
            t.setPeriode(Integer.parseInt(mperiode.getText()));
            t.setObjectif(mobj.getText());
            t.setDure(Integer.parseInt(mdure.getText()));
            t.setCapacite(Integer.parseInt(mcapacite.getText()));
            
            t.setRating(Integer.parseInt(mrating.getText()));
    
        formationCrud.updateFormation(CLmod,t);
         Optional<ButtonType> result = alert.showAndWait();
     notif.setAnimationType(Type);    
        notif.setTitle(title);
        notif.setNotificationType(NotificationType.SUCCESS);
        notif.showAndDismiss(javafx.util.Duration.millis(3000)); 
        try {
        Parent loader = FXMLLoader.load(getClass().getResource("PannelEntrepriseFXML.fxml"));
            //  Parent root  = loader.load();
            Scene  scene = new Scene(loader);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Les formations");
            window.setScene(scene);
            window.show();
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        
    }
        
    }
    

