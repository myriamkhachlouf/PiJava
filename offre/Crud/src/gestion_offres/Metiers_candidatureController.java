/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_offres;

import Entities.Candidature;
import Entities.Offres;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import utils.MaConnexion;

/**
 * FXML Controller class
 *
 * @author azizs
 */
public class Metiers_candidatureController implements Initializable {

    @FXML
    private TableView<Candidature> table;
    @FXML
    private TableColumn<Candidature, Integer> idoffre;
    @FXML
    private TableColumn<Candidature, Integer> idpromooffre;
    @FXML
    private TableColumn<Candidature, String> nomoffre;
    @FXML
    private TableColumn<Candidature, String> imageoffre;
    @FXML
    private TableColumn<Candidature, String> typeoffre;
    @FXML
    private Button Btn_supprimer;
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    ObservableList<Candidature> offreList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showaliment();
    }    
    ObservableList<Candidature> rech = FXCollections.observableArrayList();
 public void showaliment() {
        try {
            Connection cnx = MaConnexion.getInstance().getConnection();
            String query = "SELECT * FROM candidature";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Candidature candidature;
            while (rs.next()) {
                candidature = new Candidature (rs.getInt("id"), rs.getInt("candidat_id"), rs.getInt("offre_id"), rs.getString("date_candidature"), rs.getString("pdf"));
                offreList.add(candidature);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idoffre.setCellValueFactory(new PropertyValueFactory<>("id"));
        idpromooffre.setCellValueFactory(new PropertyValueFactory<>("candidat_id"));
        nomoffre.setCellValueFactory(new PropertyValueFactory<>("offre_id"));
        imageoffre.setCellValueFactory(new PropertyValueFactory<>("date_candidature"));
        typeoffre.setCellValueFactory(new PropertyValueFactory<>("pdf"));
        

        table.setItems(offreList);
    }
     public void showaliment2() {
       offreList.removeAll(offreList);
          try {
            Connection cnx = MaConnexion.getInstance().getConnection();
            String query = "SELECT * FROM candidature";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Candidature candidature;
            while (rs.next()) {
                candidature = new Candidature (rs.getInt("id"), rs.getInt("candidat_id"), rs.getInt("offre_id"), rs.getString("date_candidature"), rs.getString("pdf"));
                offreList.add(candidature);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idoffre.setCellValueFactory(new PropertyValueFactory<>("id"));
        idpromooffre.setCellValueFactory(new PropertyValueFactory<>("candidat_id"));
        nomoffre.setCellValueFactory(new PropertyValueFactory<>("offre_id"));
        imageoffre.setCellValueFactory(new PropertyValueFactory<>("date_candidature"));
        typeoffre.setCellValueFactory(new PropertyValueFactory<>("pdf"));
        

        table.setItems(offreList);
    }
     ObservableList<Candidature> offreList2;
    @FXML
    private void supprimer_offre(ActionEvent event) {
        offreList2=table.getSelectionModel().getSelectedItems();
         Connection cnx = MaConnexion.getInstance().getConnection();
            int id;
            id=offreList2.get(0).getId();
            System.out.println(id);
             
        try {
            
           String query = "delete from candidature WHERE id = ?";
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
    private void recherche(KeyEvent event) {
        try {
            rech.removeAll(rech);
            Connection cnx = MaConnexion.getInstance().getConnection();
           String x = search.getText();
            String query = "SELECT * FROM candidature  WHERE pdf LIKE '%"+x+"%'";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Candidature candidature;
            while (rs.next()) {
                candidature = new Candidature (rs.getInt("id"), rs.getInt("candidat_id"), rs.getInt("offre_id"), rs.getString("date_candidature"), rs.getString("pdf"));
                offreList.add(candidature);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idoffre.setCellValueFactory(new PropertyValueFactory<>("id"));
        idpromooffre.setCellValueFactory(new PropertyValueFactory<>("candidat_id"));
        nomoffre.setCellValueFactory(new PropertyValueFactory<>("offre_id"));
        imageoffre.setCellValueFactory(new PropertyValueFactory<>("date_candidature"));
        typeoffre.setCellValueFactory(new PropertyValueFactory<>("pdf"));
        

        table.setItems(rech);
    }

    private void TRIASC(ActionEvent event) {
        try {offreList.removeAll(offreList);
            Connection cnx = MaConnexion.getInstance().getConnection();
            String query = "SELECT * FROM candidature ORDER BY offre_id ASC";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Candidature candidature;
            while (rs.next()) {
                candidature = new Candidature (rs.getInt("id"), rs.getInt("candidat_id"), rs.getInt("offre_id"), rs.getString("date_candidature"), rs.getString("pdf"));
                offreList.add(candidature);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idoffre.setCellValueFactory(new PropertyValueFactory<>("id"));
        idpromooffre.setCellValueFactory(new PropertyValueFactory<>("candidat_id"));
        nomoffre.setCellValueFactory(new PropertyValueFactory<>("offre_id"));
        imageoffre.setCellValueFactory(new PropertyValueFactory<>("date_candidature"));
        typeoffre.setCellValueFactory(new PropertyValueFactory<>("pdf"));
        

        table.setItems(rech);
    }

    private void TRIDESC(ActionEvent event) {
        try 
      {
          offreList.removeAll(offreList);
            Connection cnx = MaConnexion.getInstance().getConnection();
            String query = "SELECT * FROM candidature ORDER BY offre_id DESC";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
             Candidature candidature;
            while (rs.next()) {
                candidature = new Candidature (rs.getInt("id"), rs.getInt("candidat_id"), rs.getInt("offre_id"), rs.getString("date_candidature"), rs.getString("pdf"));
                offreList.add(candidature);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idoffre.setCellValueFactory(new PropertyValueFactory<>("id"));
        idpromooffre.setCellValueFactory(new PropertyValueFactory<>("candidat_id"));
        nomoffre.setCellValueFactory(new PropertyValueFactory<>("offre_id"));
        imageoffre.setCellValueFactory(new PropertyValueFactory<>("date_candidature"));
        typeoffre.setCellValueFactory(new PropertyValueFactory<>("pdf"));
        

        table.setItems(rech);
    }
    
}
