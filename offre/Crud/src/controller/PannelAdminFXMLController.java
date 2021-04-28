/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author narje
 */
public class PannelAdminFXMLController implements Initializable {

    @FXML
    private TitledPane GestionPart;
    @FXML
    private Button ListeUsers;
    @FXML
    private Button ListePartner1;
    @FXML
    private TitledPane GestionEvent;
    @FXML
    private Button btnStand;
    @FXML
    private Button btnEvent;
    @FXML
    private Button FormStat;
    @FXML
    private TitledPane GestionOffre;
    @FXML
    private Button AjoutOff;
    @FXML
    private Button ConsulterOffres;
    @FXML
    private TitledPane GestionEntre;
    @FXML
    private Button ListEntr;
    @FXML
    private Button StatEntr;
    @FXML
    private TitledPane GestionReclam;
    @FXML
    private Button ListRecl;
    @FXML
    private Button StatRecl;
    @FXML
    private TitledPane GestionPubl;
    @FXML
    private Button ListPub;
    @FXML
    private Button StatPubl;
    @FXML
    private AnchorPane container_admin;
    
    public AnchorPane getContainer_admin() {
        return container_admin;
    }

    public void setContainer_admin(AnchorPane container_admin) {
        this.container_admin = container_admin;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GestionPart.setVisible(true);
        
        GestionEvent.setVisible(true);
     
         GestionOffre.setVisible(true);

       GestionEntre.setVisible(true);
    
        GestionReclam.setVisible(true);
       
     GestionPubl.setVisible(true);
    
        
        container_admin.setVisible(true);
      
        // TODO
    }    

    @FXML
    private void accueilAdmin(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/PannelAdmin.fxml"));       
            Parent root = loader.load();
            container_admin.getChildren().setAll(root);
        } catch (IOException ex) {
          
        }
    }
 
    @FXML
    private void Logout(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/singnin.fxml"));
            Parent root = loader.load();
            //SingninController sc = loader.getController();

              container_admin.getScene().setRoot(root);

        } catch (IOException ex) {
           
        }
    
    }

    @FXML
    private void Listeusers(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login/UserInterface.fxml"));       
            Parent root = loader.load();
            container_admin.getChildren().setAll(root);
        } catch (IOException ex) {
          
        }
    }

    @FXML
    private void ListePartner(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login/Statistics.fxml"));       
            Parent root = loader.load();
            container_admin.getChildren().setAll(root);
        } catch (IOException ex) {
          
        }
    }

    @FXML
    private void gest_form(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/formateur.fxml"));       
            Parent root = loader.load();
            container_admin.getChildren().setAll(root);
        } catch (IOException ex) {
          
        }
    }

    @FXML
    private void gest_event(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/event.fxml"));       
            Parent root = loader.load();
            container_admin.getChildren().setAll(root);
        } catch (IOException ex) {
          
        }
    }

    @FXML
    private void StatForm(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/formation.fxml"));       
            Parent root = loader.load();
            container_admin.getChildren().setAll(root);
        } catch (IOException ex) {
          
        }
    }

    @FXML
    private void AjoutOffre(ActionEvent event) { 
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestion_offres/FXMLDocument.fxml"));       
            Parent root = loader.load();
            container_admin.getChildren().setAll(root);
        } catch (IOException ex) {
          
        }
        
    }

    @FXML
    private void ConsulterOffres(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestion_offres/metiers.fxml"));       
            Parent root = loader.load();
            container_admin.getChildren().setAll(root);
        } catch (IOException ex) {
          
        }
        
    }

    @FXML
    private void ListeEntre(ActionEvent event) {
    }

    @FXML
    private void StatEntre(ActionEvent event) {
    }

    @FXML
    private void ListRecl(ActionEvent event) {
    }

    @FXML
    private void StatRecl(ActionEvent event) {
    }

    @FXML
    private void ListPub(ActionEvent event) {
    }

    @FXML
    private void StatPubli(ActionEvent event) {
    }
    
}
