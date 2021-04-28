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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author narje
 */
public class PannelClientController implements Initializable {
   public static int idusers = 0;
    @FXML
    private HBox hboxmenu;
    @FXML
    private Button accueil;
    @FXML
    private MenuItem btEvent;
    @FXML
    private MenuItem btForm;
    @FXML
    private Button forum;
    @FXML
    private HBox hboximage;
    @FXML
    private ImageView khedmtch;
    @FXML
    private ImageView login;
    @FXML
    private ImageView LogoutBox;
    @FXML
    private AnchorPane container_client;
    @FXML
    private Label iduser;
    @FXML
    private MenuItem stageaff;
    @FXML
    private MenuItem info;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //*idusers = SingninController.userIden;
        login.setVisible(true);
        LogoutBox.setVisible(true);
   
        //hboxout.setVisible(true);
        khedmtch.setVisible(true);
        // TODO
    }    

    @FXML
    private void xxxxx(ActionEvent event) {
         
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/khedmtech/gui/PanelClient.fxml"));
            Parent root = loader.load();
              
            container_client.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(PannelClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void ReclCandidat(ActionEvent event) {
    }

    @FXML
    private void ReclEntreprise(ActionEvent event) {
    }

    @FXML
    private void ReclFormation(ActionEvent event) {
    }

    @FXML
    private void MesRecl(ActionEvent event) {
    }

    @FXML
    private void StageOffre(ActionEvent event) {
    }

    @FXML
    private void TravailOffre(ActionEvent event) {
    }

    @FXML
    private void loadEvent(ActionEvent event) {
    }

    @FXML
    private void loadFormation(ActionEvent event) {
    }

    @FXML
    private void forum(ActionEvent event) {
    }

    @FXML
    private void logDirection(MouseEvent event) {
    }

    @FXML
    private void LogoutBox(MouseEvent event) {
         
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/khedmtech/gui/singin.fxml"));
            Parent root = loader.load();
              
           forum.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(PannelClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void info(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login/SimpleUserInterface.fxml"));       
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
        } catch (IOException ex) {
          
        }
    }
    
}
