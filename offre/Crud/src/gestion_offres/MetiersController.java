/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_offres;

import Entities.Offres;
import Service.ServiceOffres;
import Utils.Maconnexion;
import java.io.IOException;
import static java.lang.Math.round;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author 21624
 */
public class MetiersController implements Initializable {
    @FXML
    private AnchorPane fils;
    @FXML
    private Button Btn_precedent;
    @FXML
    private TableView<Offres> table;
    @FXML
    private TableColumn<Offres, Integer> idoffre;
    @FXML
    private TableColumn<Offres, Integer> idpromooffre;
    @FXML
    private TableColumn<Offres, Integer> nomoffre;
    @FXML
    private TableColumn<Offres, Integer> imageoffre;
    @FXML
    private TableColumn<Offres, Integer> typeoffre;
    /**
     * Initializes the controller class.
     * 
     */ObservableList<Offres> rech = FXCollections.observableArrayList();
     ObservableList<Offres> offreList = FXCollections.observableArrayList();
    ObservableList<Offres> offreList2;
    @FXML
    private Button Btn_supprimer;
    @FXML
    private Button Stage;
    @FXML
    private Button Emploi;
    @FXML
    private Rating rating;
    @FXML
    private Button Btn_rate;
    @FXML
    private Button Btn_showrating;
    @FXML
    private TextField search;
    @FXML
    private Button TRIASC;
    @FXML
    private Button TRIDESC;
    @FXML
    private Button Btn_stat;
        
     public void showaliment() {
        try {
            Connection cnx = Maconnexion.getInstance().getConnection();
            String query = "SELECT * FROM offre";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Offres offres;
            while (rs.next()) {
                offres = new Offres(rs.getInt("id"), rs.getInt("entreprise_id"), rs.getString("nom_offre"), rs.getString("image_name"), rs.getString("type"), rs.getInt("note"));
                offreList.add(offres);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idoffre.setCellValueFactory(new PropertyValueFactory<>("id"));
        idpromooffre.setCellValueFactory(new PropertyValueFactory<>("entreprise_id"));
        nomoffre.setCellValueFactory(new PropertyValueFactory<>("nom_offre"));
        imageoffre.setCellValueFactory(new PropertyValueFactory<>("image_name"));
        typeoffre.setCellValueFactory(new PropertyValueFactory<>("type"));
        

        table.setItems(offreList);
    }
     public void showaliment2() {
       offreList.removeAll(offreList);
         try {
            Connection cnx = Maconnexion.getInstance().getConnection();
            String query = "SELECT * FROM offre";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Offres offres;
            while (rs.next()) {
                offres = new Offres(rs.getInt("id"), rs.getInt("entreprise_id"), rs.getString("nom_offre"), rs.getString("image_name"), rs.getString("type"), rs.getInt("note"));
                offreList.add(offres);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idoffre.setCellValueFactory(new PropertyValueFactory<>("id"));
        idpromooffre.setCellValueFactory(new PropertyValueFactory<>("entreprise_id"));
        nomoffre.setCellValueFactory(new PropertyValueFactory<>("nom_offre"));
        imageoffre.setCellValueFactory(new PropertyValueFactory<>("image_name"));
        typeoffre.setCellValueFactory(new PropertyValueFactory<>("type"));
        

        table.setItems(offreList);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showaliment();
    }   
    @FXML
    private void precedent(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gestion_offres/FXMLDocument.fxml"));
         fils.getChildren().setAll(pane);
    }

    @FXML
    private void supprimer_offre(ActionEvent event) {
         offreList2=table.getSelectionModel().getSelectedItems();
         Connection cnx = Maconnexion.getInstance().getConnection();
            int id;
            id=offreList2.get(0).getId();
            System.out.println(id);
             
        try {
            
           String query = "delete from offre WHERE id = ?";
      PreparedStatement preparedStmt = cnx.prepareStatement(query);
      preparedStmt.setInt(1, id);

      // execute the preparedstatement
      preparedStmt.execute();
       
                 
      
     
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
         showaliment2();
    }

    @FXML
    private void suivantEmploi(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gestion_offres/Emploi.fxml"));
         fils.getChildren().setAll(pane);
    }

    @FXML
    private void suivantStage(ActionEvent event) throws IOException {
    
 AnchorPane pane = FXMLLoader.load(getClass().getResource("/gestion_offres/Stage.fxml"));
         fils.getChildren().setAll(pane);
    }

    @FXML
    private void rate(ActionEvent event) throws SQLException {
        ServiceOffres pda = ServiceOffres.getInstance();
        Offres p1 = table.getSelectionModel().getSelectedItem();
        p1.setNote((int) round((p1.getNote()+rating.getRating())/2));
       
       pda.rate(p1);
        
    }

    @FXML
    private void showrating(ActionEvent event) {
        Offres p1 = table.getSelectionModel().getSelectedItem();
     try {  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("infoOffre.fxml"));
            Parent root = (Parent) loader.load();
            InfoOffreController secController=loader.getController();
            secController.show1(p1.getId());
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void recherche(KeyEvent event) {
        try {
            rech.removeAll(rech);
            Connection cnx = Maconnexion.getInstance().getConnection();
           String x = search.getText();
            String query = "SELECT * FROM offre  WHERE nom_offre LIKE '%"+x+"%'";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Offres offres;
            while (rs.next()) {
                offres = new Offres(rs.getInt("id"), rs.getInt("entreprise_id"), rs.getString("nom_offre"), rs.getString("image_name"), rs.getString("type"), rs.getInt("note"));
                rech.add(offres);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idoffre.setCellValueFactory(new PropertyValueFactory<>("id"));
        idpromooffre.setCellValueFactory(new PropertyValueFactory<>("entreprise_id"));
        nomoffre.setCellValueFactory(new PropertyValueFactory<>("nom_offre"));
        imageoffre.setCellValueFactory(new PropertyValueFactory<>("image_name"));
        typeoffre.setCellValueFactory(new PropertyValueFactory<>("type"));
        

        table.setItems(rech);
    }

    @FXML
    private void TRIASC(ActionEvent event) {
        try {offreList.removeAll(offreList);
            Connection cnx = Maconnexion.getInstance().getConnection();
            String query = "SELECT * FROM offre ORDER BY nom_offre ASC";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Offres offres;
            while (rs.next()) {
                offres = new Offres(rs.getInt("id"), rs.getInt("entreprise_id"), rs.getString("nom_offre"), rs.getString("image_name"), rs.getString("type"), rs.getInt("note"));
                offreList.add(offres);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idoffre.setCellValueFactory(new PropertyValueFactory<>("id"));
        idpromooffre.setCellValueFactory(new PropertyValueFactory<>("entreprise_id"));
        nomoffre.setCellValueFactory(new PropertyValueFactory<>("nom_offre"));
        imageoffre.setCellValueFactory(new PropertyValueFactory<>("image_name"));
        typeoffre.setCellValueFactory(new PropertyValueFactory<>("type"));
        

        table.setItems(offreList);
    }

    @FXML
    private void TRIDESC(ActionEvent event) {
        try {offreList.removeAll(offreList);
            Connection cnx = Maconnexion.getInstance().getConnection();
            String query = "SELECT * FROM offre ORDER BY nom_offre DESC";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Offres offres;
            while (rs.next()) {
                offres = new Offres(rs.getInt("id"), rs.getInt("entreprise_id"), rs.getString("nom_offre"), rs.getString("image_name"), rs.getString("type"), rs.getInt("note"));
                offreList.add(offres);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idoffre.setCellValueFactory(new PropertyValueFactory<>("id"));
        idpromooffre.setCellValueFactory(new PropertyValueFactory<>("entreprise_id"));
        nomoffre.setCellValueFactory(new PropertyValueFactory<>("nom_offre"));
        imageoffre.setCellValueFactory(new PropertyValueFactory<>("image_name"));
        typeoffre.setCellValueFactory(new PropertyValueFactory<>("type"));
        

        table.setItems(offreList);
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gestion_offres/stat_offre.fxml"));
         fils.getChildren().setAll(pane);
    }
  
    }
    

