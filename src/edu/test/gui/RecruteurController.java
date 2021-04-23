/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.test.gui;
import API.pdf;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.layout.BorderPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import edu.test.entities.Recruteur;
import edu.test.services.ServiceRecruteur;
import static java.awt.PageAttributes.MediaType.C;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;




/**
 * FXML Controller class
 *
 * @author Asus
 */
public class RecruteurController implements Initializable {
 
    @FXML
   private TableView<Recruteur> a;
    @FXML
    private TableColumn<Recruteur,String> nomcat;
   @FXML
    private JFXTextField searchTF;
     public ObservableList<Recruteur> data = FXCollections.observableArrayList();
    ServiceRecruteur sec = new ServiceRecruteur();
    @FXML
    private TextField txtnidcat;
    @FXML
  private TextField txtnomcat;
        ServiceRecruteur se = new ServiceRecruteur();
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         data.addAll(sec.readAll());
         System.out.println(sec.readAll());
       
        this.nomcat.setCellValueFactory(new PropertyValueFactory<>("nom"));
        this.a.setItems(data);
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
    private void supprimer(ActionEvent event) throws SQLException {
            if (a.getSelectionModel().getSelectedItem() != null) {
            Alert deleteBookAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteBookAlert.setTitle("supression Recuteur");
            deleteBookAlert.setHeaderText(null);
            deleteBookAlert.setContentText("vous voulez supprimer cette Recruteur ?");
            Optional<ButtonType> optionDeleteBookAlert = deleteBookAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                Recruteur c = a.getSelectionModel().getSelectedItem();
                sec.supprimer(c);
                data.clear();
                data.addAll(sec.readAll());
                //Alert Delete Blog :
                Alert succDeleteBookAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteBookAlert.setTitle("suppression Recruteur");
                succDeleteBookAlert.setHeaderText("Results:");
                succDeleteBookAlert.setContentText("Recruteur supprimee ! ");
                succDeleteBookAlert.showAndWait();
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {
            }
        
        }
    }

    private void ajouter(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ajout.fxml"));
            //Scene scene = new Scene(root, 1100, 650);
            Scene scene = new Scene(root);//fhemtha faza edhyka imchi hajet tefha le:p hhh
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
            System.out.println("ajout");
            } catch (IOException ex) {
               ex.getMessage();
                    System.out.println("ajout "); 
           }
        
        
    }

    @FXML
    private void filter(KeyEvent event) {    data.clear();
      
        data.addAll(sec.readAll().stream().filter((art)
                -> art.getNom().toLowerCase().contains(searchTF.getText().toLowerCase())).collect(Collectors.toList()));

    }
     @FXML
    private void changenomcatCellEvent(TableColumn.CellEditEvent edittedCell) {
        {
       Recruteur produitslected = a.getSelectionModel().getSelectedItem();
        produitslected.setNom(edittedCell.getNewValue().toString());}
    }


//    @FXML
//    private void modifier(ActionEvent event) {
//    
//     {int i=0;
//        if (a.getSelectionModel().getSelectedItem() != null) {
//            Recruteur c = a.getSelectionModel().getSelectedItem();
//            i=c.getIdcat();
//            System.out.println(i);
//            sec.modifiercat(c);
//            Alert BookAlert = new Alert(Alert.AlertType.INFORMATION);
//            BookAlert.setTitle("edit");
//            BookAlert.setHeaderText(null);
//            BookAlert.setContentText("Recruteur modifier");
//            BookAlert.showAndWait();
//        } else {
//            //Alert Select BOOK :
//            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
//            selectBookAlert.setTitle("selectioner une Recruteur ");
//            selectBookAlert.setHeaderText(null);
//            selectBookAlert.setContentText("selectioner une Recruteur");
//            selectBookAlert.showAndWait();
//        }
//    }}
    
      
 private void ExportPage(ActionEvent event) throws Exception {
           pdf.main(null);
    }
   
    
//    private void modif(ActionEvent event) {
//          {int i=0;
//        if (a.getSelectionModel().getSelectedItem() != null) {
//            Recruteur r = a.getSelectionModel().getSelectedItem();
//            i=r.getId();
//            System.out.println(i);
//            sec.updateRecruteur(r);
//            Alert BookAlert = new Alert(Alert.AlertType.INFORMATION);
//            BookAlert.setTitle("edit");
//            BookAlert.setHeaderText(null);
//            BookAlert.setContentText("produit modifier");
//            BookAlert.showAndWait();
//        } else {
//            //Alert Select BOOK :
//            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
//            selectBookAlert.setTitle("selectioner un  produit ");
//            selectBookAlert.setHeaderText(null);
//            selectBookAlert.setContentText("selectioner un produit");
//            selectBookAlert.showAndWait();
//        }
    

   
    
    }
 

    
