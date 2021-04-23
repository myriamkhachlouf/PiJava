/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_offres;

import Entities.Emplois;
import Entities.Offres;
import Utils.Maconnexion;
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
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author 21624
 */
public class MetiersEmploiController implements Initializable {

    @FXML
    private AnchorPane fils1;
    @FXML
    private Button Btn_precedent;
    @FXML
    private TableView<Emplois> table;
    @FXML
    private TableColumn<Emplois, Integer> idemploi;
    @FXML
    private TableColumn<Emplois, Integer> idoffreemploi;
    @FXML
    private TableColumn<Emplois, Integer> salaireemploi;
    @FXML
    private TableColumn<Emplois, Integer> typeemploi;
    @FXML
    private Button Btn_supprimeremploi;

    /**
     * Initializes the controller class.
     */ ObservableList<Emplois> rech = FXCollections.observableArrayList();
      ObservableList<Emplois> emploiList = FXCollections.observableArrayList();
    ObservableList<Emplois> emploiList2;
    @FXML
    private TextField search;
    @FXML
    private Button TRIASC;
    @FXML
    private Button TRIDESC;
    @FXML
    private Button Btn_supprimeremploi1;
        
     public void showemploi() {
        try {
            Connection cnx = Maconnexion.getInstance().getConnection();
            String query = "SELECT * FROM emploi";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Emplois emplois;
            while (rs.next()) {
                emplois = new Emplois(rs.getInt("id"), rs.getInt("offre_id"), rs.getInt("salaire"), rs.getString("type_contrat"));
                emploiList.add(emplois);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idemploi.setCellValueFactory(new PropertyValueFactory<>("id"));
        idoffreemploi.setCellValueFactory(new PropertyValueFactory<>("offre_id"));
        salaireemploi.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        typeemploi.setCellValueFactory(new PropertyValueFactory<>("type_contrat"));

        

        table.setItems(emploiList);
    }
     public void showemploi2() {
       emploiList.removeAll(emploiList);
        try {
            Connection cnx = Maconnexion.getInstance().getConnection();
            String query = "SELECT * FROM emploi";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Emplois emplois;
            while (rs.next()) {
                emplois = new Emplois(rs.getInt("id"), rs.getInt("offre_id"), rs.getInt("salaire"), rs.getString("type_contrat"));
                emploiList.add(emplois);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idemploi.setCellValueFactory(new PropertyValueFactory<>("id"));
        idoffreemploi.setCellValueFactory(new PropertyValueFactory<>("offre_id"));
        salaireemploi.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        typeemploi.setCellValueFactory(new PropertyValueFactory<>("type_contrat"));

        

        table.setItems(emploiList);
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showemploi();
    }    

    @FXML
    private void precedent(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gestion_offres/Emploi.fxml"));
         fils1.getChildren().setAll(pane);
    }

    @FXML
    private void supprimer_emploi(ActionEvent event) {
         emploiList2=table.getSelectionModel().getSelectedItems();
         Connection cnx = Maconnexion.getInstance().getConnection();
            int id;
            id=emploiList2.get(0).getIdemploi();
            System.out.println(id);
             
        try {
            
           String query = "delete from emploi WHERE id = ?";
      PreparedStatement preparedStmt = cnx.prepareStatement(query);
      preparedStmt.setInt(1, id);

      // execute the preparedstatement
      preparedStmt.execute();
       
                 
      
     
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
         showemploi2();
    }

    @FXML
    private void recherche(KeyEvent event) throws SQLException {
         
        try {
            rech.removeAll(rech);
         Connection cnx = Maconnexion.getInstance().getConnection();
         String x = search.getText();
                     System.out.println(x);

            String query = "SELECT * FROM emploi  WHERE type_contrat LIKE '%"+x+"%'";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
            Emplois emplois = new Emplois(rs.getInt("id"), rs.getInt("offre_id"), rs.getInt("salaire"), rs.getString("type_contrat"));
            rech.add(emplois);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idemploi.setCellValueFactory(new PropertyValueFactory<>("id"));
        idoffreemploi.setCellValueFactory(new PropertyValueFactory<>("offre_id"));
        salaireemploi.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        typeemploi.setCellValueFactory(new PropertyValueFactory<>("type_contrat"));

        

        table.setItems(rech);
        
    }

    @FXML
    private void TRIASC(ActionEvent event) {
        try {
            emploiList.removeAll(emploiList);
         Connection cnx = Maconnexion.getInstance().getConnection();
         String x = search.getText();
                     System.out.println(x);

            String query = "SELECT * FROM emploi ORDER BY salaire ASC";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
            Emplois emplois = new Emplois(rs.getInt("id"), rs.getInt("offre_id"), rs.getInt("salaire"), rs.getString("type_contrat"));
            emploiList.add(emplois);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idemploi.setCellValueFactory(new PropertyValueFactory<>("id"));
        idoffreemploi.setCellValueFactory(new PropertyValueFactory<>("offre_id"));
        salaireemploi.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        typeemploi.setCellValueFactory(new PropertyValueFactory<>("type_contrat"));

        

        table.setItems(emploiList);
    }

    @FXML
    private void TRIDESC(ActionEvent event) {
        try {
            emploiList.removeAll(emploiList);
         Connection cnx = Maconnexion.getInstance().getConnection();
         String x = search.getText();
                     System.out.println(x);

            String query = "SELECT * FROM emploi ORDER BY salaire DESC";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
            Emplois emplois = new Emplois(rs.getInt("id"), rs.getInt("offre_id"), rs.getInt("salaire"), rs.getString("type_contrat"));
            emploiList.add(emplois);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idemploi.setCellValueFactory(new PropertyValueFactory<>("id"));
        idoffreemploi.setCellValueFactory(new PropertyValueFactory<>("offre_id"));
        salaireemploi.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        typeemploi.setCellValueFactory(new PropertyValueFactory<>("type_contrat"));

        

        table.setItems(emploiList);
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gestion_offres/Stat_emploi.fxml"));
         fils1.getChildren().setAll(pane);
    }


   

   

   
    
}
