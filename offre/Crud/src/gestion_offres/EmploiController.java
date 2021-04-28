/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_offres;


import Entities.Emplois;
import Entities.Offres;
import Service.ServiceEmplois;
import Service.ServiceOffres;
import utils.MaConnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author 21624
 */
public class EmploiController implements Initializable {

    @FXML
    private AnchorPane filss;
    @FXML
    private Label label;
    @FXML
    private TextField tfid_emploi;
    @FXML
    private Button Btn_modifier;
    @FXML
    private TextField tfsalaire_emploi;
    @FXML
    private ComboBox<Offres> idoffre_emploi;
    @FXML
    private ComboBox<String> idtype_emploi;
    @FXML
    private Button Btn_suivant;
    @FXML
    private Button Btn_ajouter;

    /**
     * Initializes the controller class.
     */
    ServiceOffres serv;
    ServiceOffres AC= new ServiceOffres();
     
    ObservableList<String> data2 = FXCollections.observableArrayList("normal","extra");
    
    Image img=new Image("/images/icon 1.png");
    @FXML
    private Button Btn_precedent1;
    private void afficherCombo() {
      ObservableList<Offres> data = FXCollections.observableArrayList(AC.afficherOffre());
        idoffre_emploi.setItems(data);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idtype_emploi.setItems(data2);
            afficherCombo();
           
        
    }    
    

    @FXML
    private void Suivant(ActionEvent event) throws IOException {
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("/gestion_offres/metiersEmploi.fxml"));
                     filss.getChildren().setAll(pane);
    }

    @FXML
    private void ajouter_emploi(ActionEvent event) throws SQLException {
          int id = Integer.parseInt(tfid_emploi.getText());
        int offre_id = idoffre_emploi.getValue().getId();
        String type = idtype_emploi.getValue();
        int salaire = Integer.parseInt(tfsalaire_emploi.getText());
         Emplois A = new Emplois(id, offre_id, salaire, type);
        ServiceEmplois aa = new ServiceEmplois();
         if (aa.AddEmploi(A)){
             
         Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("L'ajout d'un Emploi a été effectué avec succées");
        alert.showAndWait();
        aa.afficherEmplois();
        afficherCombo();
        }else{
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("L'ajout d'un  Emploi n'a pas été effectué!");
        alert.showAndWait();
        aa.afficherEmplois();
        afficherCombo();

        }
        Notifications notificationBuilder = Notifications.create()
                .title("Emploi ajouté")
                .text("Votre Offre d'emploi a été ajouté avec succès")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
                notificationBuilder.show();
    }

    @FXML
    private void modifier_emploi(ActionEvent event) throws SQLException {
        ServiceEmplois sr = new ServiceEmplois();  
            int id = Integer.parseInt(tfid_emploi.getText());
            int id_offre = idoffre_emploi.getValue().getId();
        String type = idtype_emploi.getValue();
            int salaire =Integer.parseInt(tfsalaire_emploi.getText());

        Emplois e = new Emplois(id,id_offre,salaire,type);

        sr.ModifierEmploi(id, e);
         Notifications notificationBuilder = Notifications.create()
                .title("Emploi Modifié")
                .text("Votre Emploi a été modifié avec succès")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
                notificationBuilder.show();
           tfid_emploi.clear();
                  tfsalaire_emploi.clear();
                  
    }   

    @FXML
    private void precedentt(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gestion_offres/metiers.fxml"));
         filss.getChildren().setAll(pane);
    }

    @FXML
    private void tt(ActionEvent event) {
    }
    
}
