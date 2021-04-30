/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * FXML Controller class
 *
 * @author narje
 */
public class PannelEntrepriseController implements Initializable {
       public static int idusers = 0;

    @FXML
    private HBox hboxmenu;
    @FXML
    private Button accueil;
    @FXML
    private MenuItem btEvent;
    @FXML
    private MenuItem btForm;
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
    private MenuItem btForm2;
    @FXML
    private MenuItem info;
    @FXML
    private MenuItem btEvententr11;
    @FXML
    private MenuItem btrec11;
    @FXML
    private MenuItem btngrille11;
    @FXML
    private MenuItem pub;
    @FXML
    private MenuItem consulter;
    @FXML
    private ImageView khedmtch1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         //*idusers = SingninController.userIden;
        login.setVisible(true);
        LogoutBox.setVisible(true);
    
        //hboxout.setVisible(true);
      
           
        // TODO
    }    

    @FXML
    private void xxxxx(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/khedmtech/gui/PanelEntrepriseFXML.fxml"));
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
    private void ReclFormation(ActionEvent event) {
    }

    @FXML
    private void MesRecl(ActionEvent event) {
    }

    @FXML
    private void StageOffre(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestion_offres/Stage.fxml"));       
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
        } catch (IOException ex) {
          
        }
    }

    @FXML
    private void TravailOffre(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestion_offres/Emploi.fxml"));       
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
        } catch (IOException ex) {
          
        }
    }

    @FXML
    private void loadEvent(ActionEvent event) { 
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/event.fxml"));       
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
        } catch (IOException ex) {
          
        }
    }

    @FXML
    private void loadFormation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/formation.fxml"));       
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
        } catch (IOException ex) {
          
        }
    
    }



    @FXML
    private void logDirection(MouseEvent event) {
    }

    @FXML
    private void LogoutBox(MouseEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/singnin.fxml"));
            Parent root = loader.load();
            //SingninController sc = loader.getController();

              container_client.getScene().setRoot(root);

        } catch (IOException ex) {
           
        }
         String path="D:\\wamp64\\www\\PiJava\\offre\\Crud\\src\\Style\\outro.mp3";
            Media media =new Media(new File(path).toURI().toString());
            MediaPlayer mediaplayer = new MediaPlayer(media);
            mediaplayer.play();
    
    }
        @FXML
    private void loadentr(ActionEvent event) {
    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/test/gui/entretien.fxml"));       
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
        } catch (IOException ex) {
          
        }
    }
  
    @FXML
    private void loadrecruteur(ActionEvent event) {
     try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/test/gui/Recruteur.fxml"));       
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
        } catch (IOException ex) {
          
        }
    }

    @FXML
    private void loadGrille(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/test/gui/grille.fxml"));       
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
        } catch (IOException ex) {
          
        }
    }
    @FXML
    private void loadFormateur(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/formateur.fxml"));       
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
        } catch (IOException ex) {
          
        }
    }

    @FXML
    private void offre(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestion_offres/FXMLDocument.fxml"));       
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
        } catch (IOException ex) {
          
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

    @FXML
    private void loadpublication(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestion_publication/publicationFXML.fxml"));       
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
        } catch (IOException ex) {
          
        }
    }

    @FXML
    private void consulter(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/aficherFormateur.fxml"));       
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
        } catch (IOException ex) {
          
        }
    }

    
    }
    

