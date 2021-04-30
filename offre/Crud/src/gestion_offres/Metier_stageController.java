/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_offres;

import Entities.Stage;
import Entities.Offres;
import Entities.users;
import Service.UsersService;
import utils.MaConnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author azizs
 */
public class Metier_stageController implements Initializable {

    @FXML
    private Button Btn_precedent2;
    @FXML
    private TableView<Stage> tablest;
    @FXML
    private TableColumn<Stage, Integer> idstage;
    @FXML
    private TableColumn<Stage, Integer> id_ooffre;
    @FXML
    private TableColumn<Stage, String> date_debut;
    @FXML
    private TableColumn<Stage, String> date_fin;
    @FXML
    private TableColumn<Stage, Integer> typestage;
    @FXML
    private TableColumn<Stage, String> nomencadrant;
    @FXML
    private Button Btn_supprimer2;
    @FXML
    private Button offre;
    @FXML
    private Button Emploi2;
    ObservableList<Stage> rech = FXCollections.observableArrayList();
ObservableList<Stage> stageList = FXCollections.observableArrayList();
    ObservableList<Stage> stageList2;
    @FXML
    private AnchorPane fils;
    @FXML
    private TextField search;
    @FXML
    private Button TRIASC;
    @FXML
    private Button TRIDESC;
    @FXML
    private Button postuler;
    /**
     * Initializes the controller class.
     */
    public void showstage() {
        try {
            Connection cnx = MaConnexion.getInstance().getConnection();
            String query = "SELECT * FROM stage";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Stage stages;
            while (rs.next()) {
                stages = new Stage(rs.getInt("id"), rs.getInt("offre_id"), rs.getString("date_debut"), rs.getString("date_fin"),rs.getString("type_du_stage"),rs.getString("nom_encadrant"));
                stageList.add(stages);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idstage.setCellValueFactory(new PropertyValueFactory<>("id"));
        id_ooffre.setCellValueFactory(new PropertyValueFactory<>("offre_id"));
        date_debut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        typestage.setCellValueFactory(new PropertyValueFactory<>("type_du_stage"));
        nomencadrant.setCellValueFactory(new PropertyValueFactory<>("nom_encadrant"));

        

        tablest.setItems(stageList);
    }
     public void showstage2() {
       stageList.removeAll(stageList);
        try {
            Connection cnx = MaConnexion.getInstance().getConnection();
            String query = "SELECT * FROM emploi";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Stage stages;
            while (rs.next()) {
               stages = new Stage(rs.getInt("id"), rs.getInt("offre_id"), rs.getString("date_debut"), rs.getString("date_fin"),rs.getString("type_du_stage"),rs.getString("nom_encadrant"));
               stageList.add(stages);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idstage.setCellValueFactory(new PropertyValueFactory<>("id"));
        id_ooffre.setCellValueFactory(new PropertyValueFactory<>("offre_id"));
        date_debut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        typestage.setCellValueFactory(new PropertyValueFactory<>("type_du_stage"));
        nomencadrant.setCellValueFactory(new PropertyValueFactory<>("nom_encadrant"));
        

        tablest.setItems(stageList);
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showstage();
         UsersService US=new UsersService();
    int id = MaConnexion.getInstance().connectedUserID;
            users u=US.getUserByID(id);
            if (u.getRoles().equals("[\"ROLE_CANDIDAT\"]")==true||u.getRoles().equals("[\"ROLE_ADMIN\"]")==true){
            postuler.setVisible(true);
            
            }
            else{
                postuler.setVisible(false);
            }
            
             if (u.getRoles().equals("[\"ROLE_ENTREPRISE\"]")==true||u.getRoles().equals("[\"ROLE_ADMIN\"]")==true){
             Btn_supprimer2.setVisible(true);
             Btn_precedent2.setVisible(true);
             }
            else{
                Btn_supprimer2.setVisible(false);
                Btn_precedent2.setVisible(false);
            }
    }    

    @FXML
    private void precedent2(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("/gestion_offres/Stage.fxml"));
         fils.getChildren().setAll(pane);
    }

    @FXML
    private void supprimer_stage2(ActionEvent event) {
         stageList2=tablest.getSelectionModel().getSelectedItems();
         Connection cnx = MaConnexion.getInstance().getConnection();
            int id;
            id=stageList2.get(0).getId();
            System.out.println(id);
             
        try {
            
           String query = "delete from stage WHERE id = ?";
      PreparedStatement preparedStmt = cnx.prepareStatement(query);
      preparedStmt.setInt(1, id);

      // execute the preparedstatement
      preparedStmt.execute();
       
                 
      
     
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
         showstage2();
    }

    @FXML
    private void suivantoffre2(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gestion_offres/FXMLDocument.fxml"));
         fils.getChildren().setAll(pane);
    }

    @FXML
    private void suivantEmploi2(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gestion_offres/Emploi.fxml"));
         fils.getChildren().setAll(pane);
    }

    @FXML
    private void recherche(KeyEvent event) {
         try {            rech.removeAll(rech);
            Connection cnx = MaConnexion.getInstance().getConnection();
           String x = search.getText();
                     System.out.println(x);

            String query = "SELECT * FROM stage  WHERE nom_encadrant LIKE '%"+x+"%'";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Stage stages;
            while (rs.next()) {
                stages = new Stage(rs.getInt("id"), rs.getInt("offre_id"), rs.getString("date_debut"), rs.getString("date_fin"),rs.getString("type_du_stage"),rs.getString("nom_encadrant"));
                rech.add(stages);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idstage.setCellValueFactory(new PropertyValueFactory<>("id"));
        id_ooffre.setCellValueFactory(new PropertyValueFactory<>("offre_id"));
        date_debut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        typestage.setCellValueFactory(new PropertyValueFactory<>("type_du_stage"));
        nomencadrant.setCellValueFactory(new PropertyValueFactory<>("nom_encadrant"));

        

        tablest.setItems(rech);
        
    }

    @FXML
    private void TRIASC(ActionEvent event) {
         try {stageList.removeAll(stageList);
            Connection cnx = MaConnexion.getInstance().getConnection();
            String query = "SELECT * FROM stage ORDER BY type_du_stage ASC";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Stage stages;
            while (rs.next()) {
                stages = new Stage(rs.getInt("id"), rs.getInt("offre_id"), rs.getString("date_debut"), rs.getString("date_fin"),rs.getString("type_du_stage"),rs.getString("nom_encadrant"));
                stageList.add(stages);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idstage.setCellValueFactory(new PropertyValueFactory<>("id"));
        id_ooffre.setCellValueFactory(new PropertyValueFactory<>("offre_id"));
        date_debut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        typestage.setCellValueFactory(new PropertyValueFactory<>("type_du_stage"));
        nomencadrant.setCellValueFactory(new PropertyValueFactory<>("nom_encadrant"));

        

        tablest.setItems(stageList);
    }

    @FXML
    private void TRIDESC(ActionEvent event) {
         try {
             stageList.removeAll(stageList);
            Connection cnx = MaConnexion.getInstance().getConnection();
            String query = "SELECT * FROM stage ORDER BY type_du_stage DESC";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Stage stages;
            while (rs.next()) {
                stages = new Stage(rs.getInt("id"), rs.getInt("offre_id"), rs.getString("date_debut"), rs.getString("date_fin"),rs.getString("type_du_stage"),rs.getString("nom_encadrant"));
                stageList.add(stages);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idstage.setCellValueFactory(new PropertyValueFactory<>("id"));
        id_ooffre.setCellValueFactory(new PropertyValueFactory<>("offre_id"));
        date_debut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        typestage.setCellValueFactory(new PropertyValueFactory<>("type_du_stage"));
        nomencadrant.setCellValueFactory(new PropertyValueFactory<>("nom_encadrant"));

        

        tablest.setItems(stageList);
    }

    @FXML
    private void postuler(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gestion_offres/Candidature.fxml"));
         fils.getChildren().setAll(pane);
    }

    
}
