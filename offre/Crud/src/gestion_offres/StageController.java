/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_offres;


import Entities.Offres;
import Entities.Stage;
import Service.ServiceEmplois;
import Service.ServiceOffres;
import Service.ServiceStage;
import utils.MaConnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
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
public class StageController implements Initializable {

    @FXML
    private AnchorPane filss;
    @FXML
    private Label label;
    @FXML
    private TextField tfid_stage;
    @FXML
    private Button Btn_modifier;
    @FXML
    private TextField tfnom_encadrant;
    @FXML
    private ComboBox<Offres> idoffre_stage;
    @FXML
    private ComboBox<String> idtype_stage;
    @FXML
    private Button Btn_suivant3;
    @FXML
    private Button Btn_ajouter;
    @FXML
    DatePicker date_debut,date_fin;
    Image img=new Image("/images/icon 1.png");
     ServiceOffres AC= new ServiceOffres();
    private void afficherCombo() {
      ObservableList<Offres> data = FXCollections.observableArrayList(AC.afficherOffre());
        idoffre_stage.setItems(data);
    }
    ObservableList<String> data2 = FXCollections.observableArrayList("Ouvrier","Technicien","PFE");
    @FXML
    private Button Btn_precedent1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
         idtype_stage.setItems(data2);
         afficherCombo();
    }    

  

    @FXML
    private void ajouter_stage(ActionEvent event) throws SQLException {
       
       
       int id = Integer.parseInt(tfid_stage.getText());
        int offre_id = idoffre_stage.getValue().getId();
        String Nom = tfnom_encadrant.getText();
        String type = idtype_stage.getValue();
        LocalDate date = date_debut.getValue();
        String Date_debut = date.toString();
        LocalDate date1 = date_fin.getValue();
        String Date_fin = date1.toString();

        Stage A = new Stage(id,offre_id,Date_debut,Date_fin,type,Nom);
       ServiceStage aS = new ServiceStage();
        if (aS.AddStage(A)){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("L'ajout du Stage a été effectué avec succées");
        alert.showAndWait();
        aS.afficherStage();
        afficherCombo();
        }else{
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("L'ajout du Stage n'a pas été effectué!");
        alert.showAndWait();
        aS.afficherStage();
        afficherCombo();

        }
           
                
        
        Notifications notificationBuilder = Notifications.create()
                .title("Stage ajouté")
                .text("Votre Offre de stage a été ajouté avec succès")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
                notificationBuilder.show();
    }

    @FXML
    private void modifier_stage(ActionEvent event) throws SQLException {
        ServiceStage sr = new ServiceStage();
            int id = Integer.parseInt(tfid_stage.getText());
            int id_offre = idoffre_stage.getValue().getId();
        String Nom = tfnom_encadrant.getText();
        String type = idtype_stage.getValue();
        LocalDate date = date_debut.getValue();
        String Date_debut = date.toString();
        LocalDate date1 = date_fin.getValue();
        String Date_fin = date1.toString();

        Stage e = new Stage(id,id_offre,Date_debut,Date_fin,type,Nom);

        sr.ModifierStage(id, e);
         Notifications notificationBuilder = Notifications.create()
                .title("Stage Modifié")
                .text("Votre Stage a été modifié avec succès")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
                notificationBuilder.show();
           tfid_stage.clear();
                  tfnom_encadrant.clear();
                  
    }
      @FXML
    private void Suivant3(ActionEvent event) throws IOException {
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("/gestion_offres/metier_stage.fxml"));
                     filss.getChildren().setAll(pane);
    }

    @FXML
    private void precedentt(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gestion_offres/metiers.fxml"));
         filss.getChildren().setAll(pane);
    }
    
}
