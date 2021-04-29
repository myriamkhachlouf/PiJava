/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.test.gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import edu.test.entities.grille;
import edu.test.services.ServiceGrille;

import edu.test.utils.DataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author fac
 */
public class GrilleController implements Initializable {

    @FXML
    private AnchorPane Produitbtn1;
    @FXML
    private JFXTextField searchTF;
    @FXML
    private TextField txtide;
    @FXML
    private TextField txtcommentaire;
    private TextField txtetat;
    @FXML
    private TableView<grille> eventcoursTV;
      @FXML
    
    private TableColumn<grille, Integer> idet;
    @FXML
    private TableColumn<grille, String> commentairet;
    @FXML
    private TableColumn<grille, String> etatt;
     public ObservableList<grille> data = FXCollections.observableArrayList();
     ServiceGrille sec = new ServiceGrille();
    @FXML
    private TableColumn<grille, Integer> id;
    @FXML
    private TextField txtid;
    @FXML
    private JFXRadioButton radio;
    @FXML
    private JFXComboBox<String> cetat;
    ObservableList<String> data2 = FXCollections.observableArrayList("accepté","refusé");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Afficher();
       
       eventcoursTV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                edu.test.entities.grille r = (edu.test.entities.grille) eventcoursTV.getSelectionModel().getSelectedItem();
              if (eventcoursTV.getSelectionModel().getSelectedItem() != null) {
                    edu.test.entities.grille grille = (edu.test.entities.grille) eventcoursTV.getSelectionModel().getSelectedItem();
                    
                    
                    System.out.println();
                    txtid.setText(Integer.toString(grille.getIdg()));;
                    txtide.setText(Integer.toString(grille.getIde()));;
                    txtcommentaire.setText(grille.getCommentaire());
                    cetat.setItems(data2);

                   
                }
            }

        });
    }    

    
    public void Refresh() {
        data.removeAll(data);
        try {
            Connection cnx = DataBase.getInstance().getConnection();
            String query = "SELECT * FROM grille";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            grille grille;
            while (rs.next()) {
                grille = new grille(rs.getInt("Idg"),rs.getInt("Ide"), rs.getString("commentaire"), rs.getString("etat")); 
                data.add(grille);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
       id.setCellValueFactory(new PropertyValueFactory<>("Idg"));
        idet.setCellValueFactory(new PropertyValueFactory<>("Ide"));
        commentairet.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        etatt.setCellValueFactory(new PropertyValueFactory<>("etat"));

        eventcoursTV.setItems(data);
    }
    
    @FXML
    private void filter(KeyEvent event) {
        
        data.clear();
        // System.out.println("heyy yuuu");
        data.addAll(sec.readAll().stream().filter((art)
                ->  art.getCommentaire().toLowerCase().contains(searchTF.getText().toLowerCase())
                || art.getEtat().toLowerCase().contains(searchTF.getText().toLowerCase())
        ).collect(Collectors.toList()));

    }
    
     public void Afficher() {
        try {
            Connection cnx = DataBase.getInstance().getConnection();
            String query = "SELECT * FROM grille";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            grille grille;
            while (rs.next()) {
                grille = new grille(rs.getInt("Idg"),rs.getInt("Ide"),rs.getString("commentaire"), rs.getString("etat") ); 
                data.add(grille);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        id.setCellValueFactory(new PropertyValueFactory<>("Idg"));
        idet.setCellValueFactory(new PropertyValueFactory<>("Ide"));
        commentairet.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        etatt.setCellValueFactory(new PropertyValueFactory<>("etat"));

        eventcoursTV.setItems(data);;

    
    


     }
 private void loadUi(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException ex) {
ex.printStackTrace();        }
       // BorderPane.setCenter(root); 
 }

    @FXML
    private void ajouteron(ActionEvent event) {
        int ide = Integer.parseInt(txtide.getText());
         String commentaire = txtcommentaire.getText();
         String etat = cetat.getValue();
         
        grille r = new grille(ide,commentaire,etat);
        sec.ajouterpp(r);  
        Refresh();
        txtide.clear();
        txtcommentaire.clear();
        cetat.getSelectionModel().clearSelection();
        if(
                    (txtide.getText().isEmpty() || txtcommentaire.getText().isEmpty() || cetat.getValue().isEmpty()))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez remplir le formulaire");
                    alert.showAndWait();
            }

    
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        ServiceGrille sr = new ServiceGrille();
        grille t = new grille();
        
        int idg;
        idg = Integer.parseInt(txtid.getText());
        t.setIde(Integer.parseInt(txtide.getText()));
        t.setCommentaire(txtcommentaire.getText());
        t.setEtat(cetat.getValue());
        
        
        
        if (txtide.getText().isEmpty() || txtcommentaire.getText().isEmpty() || cetat.getValue().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte !");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir le formulaire");
            alert.showAndWait();
        } else {
            sr.updateRecruteur(idg, t);
        }
        Refresh();
        txtide.clear();
        txtcommentaire.clear();
        cetat.getSelectionModel().clearSelection();

    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        if (eventcoursTV.getSelectionModel().getSelectedItem() != null) {
            Alert deleteBookAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteBookAlert.setTitle("supression Grille");
            deleteBookAlert.setHeaderText(null);
            deleteBookAlert.setContentText("vous voulez supprimer cette Grille ?");
            Optional<ButtonType> optionDeleteBookAlert = deleteBookAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                grille g = eventcoursTV.getSelectionModel().getSelectedItem();
                sec.supprimer(g);
                data.clear();
                data.addAll(sec.readAll());
                
                //Alert Delete Blog :
                Alert succDeleteBookAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteBookAlert.setTitle("suppression Grille");
                succDeleteBookAlert.setHeaderText("Results:");
                succDeleteBookAlert.setContentText("Grille supprime!");
                succDeleteBookAlert.showAndWait();
                Refresh();
                
        txtide.clear();
        txtcommentaire.clear();
        cetat.getSelectionModel().clearSelection();
        

            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {
            }
        
        }
    }

    @FXML
    private void radio(ActionEvent event) {
        data.removeAll(data);

         try {
            Connection cnx = DataBase.getInstance().getConnection();
            String query = "SELECT * FROM grille WHERE etat='accepté'";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            grille grille;
            while (rs.next()) {
                grille = new grille(rs.getInt("Idg"),rs.getInt("Ide"), rs.getString("commentaire") ,rs.getString("etat")); 
                data.add(grille);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        id.setCellValueFactory(new PropertyValueFactory<>("Idg"));
        idet.setCellValueFactory(new PropertyValueFactory<>("Ide"));
        commentairet.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        etatt.setCellValueFactory(new PropertyValueFactory<>("etat"));

        eventcoursTV.setItems(data);;
    
    
    }
 
    

}


    
   

     
    