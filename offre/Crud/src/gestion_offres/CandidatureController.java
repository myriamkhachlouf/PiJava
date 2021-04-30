/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_offres;
import Entities.users;
import Entities.Candidature;
import Entities.Offres;
import Entities.Stage;
import Service.ServiceCandidature;
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
import Service.UsersService;
import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.mail.internet.AddressException;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author azizs
 */
public class CandidatureController implements Initializable {

    @FXML
    private AnchorPane filss;
    @FXML
    private Label label;
    @FXML
    private TextField tfid_candidature;
    @FXML
    private Button Btn_modifier4;
    @FXML
    private Button tfpdf;
    @FXML
    private ComboBox<Offres> idoffre_candidature;
    @FXML
    private Button Btn_ajouter4;
    @FXML
    private DatePicker date_candidature;
    ServiceOffres AC= new ServiceOffres();
  String urlInserted;
    UsersService DC= new UsersService();
    int user_id =MaConnexion.getInstance().connectedUserID;
      String role=new UsersService().getUserByID(user_id).getRoles();
    private ComboBox<users> idcandidat;
    
    private void afficherCombo() {
      ObservableList<Offres> data = FXCollections.observableArrayList(AC.afficherOffre());
        idoffre_candidature.setItems(data);
    }
     Image img=new Image("/images/icon 1.png");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         afficherCombo();
    }    

    @FXML
    private void modifier_candidature(ActionEvent event) throws SQLException {
         ServiceCandidature sr = new ServiceCandidature();
           int id = Integer.parseInt(tfid_candidature.getText());
        int offre_id = idoffre_candidature.getValue().getId();
        int candidat_id =user_id;
        String pdf = urlInserted;
        LocalDate date = date_candidature.getValue();
        String Date = date.toString();
        Candidature e = new Candidature(id,candidat_id,offre_id,Date,pdf);
        sr.ModifierCandidature(id,e);
        
           tfid_candidature.clear();
                  
                  Notifications notificationBuilder = Notifications.create()
                .title("Candidature Modifié")
                .text("Votre Candidature a été Modifié avec succès")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
                notificationBuilder.show();
    }


    @FXML
    private void ajouter_candidature(ActionEvent event) throws SQLException, IOException, AddressException {
        int id = Integer.parseInt(tfid_candidature.getText());
        int offre_id = idoffre_candidature.getValue().getId();
        int candidat_id =user_id;
        String pdf = urlInserted;
        LocalDate date = date_candidature.getValue();
        String Date = date.toString();
        Candidature A = new Candidature(id,candidat_id,offre_id,Date,pdf);
       ServiceCandidature aS = new ServiceCandidature();
        if (aS.AddCandidature(A)){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("L'ajout d'une nouvelle candidature a été effectué avec succées");
        alert.showAndWait();
        aS.afficherCandidature();
        afficherCombo();
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("L'ajout d'une nouvelle candidature n'a pas été effectué!");
        alert.showAndWait();
        aS.afficherCandidature();
        afficherCombo();

        }
           
                
        
        Notifications notificationBuilder = Notifications.create()
                .title("Candidature ajouté")
                .text("Votre Candidature a été ajouté avec succès")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
                notificationBuilder.show();
                 String qr=aS.QR(A,user_id);
                aS.sendRes(A, user_id, qr);
    }

    @FXML
    private void upload(ActionEvent event) {
         FileChooser fc = new FileChooser();
        //fc.setInitialDirectory(" ");
        fc.getExtensionFilters().addAll();
        //--for single file
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            //listView.getItems().add(selectedFile.getAbsolutePath());
            urlInserted = "file:///" + selectedFile.getAbsolutePath();
            String a="";
            for (int i=0;i<urlInserted.length();i++){
                
                
                a=a+urlInserted.charAt(i);
                if (urlInserted.charAt(i) =='\\'){
                    
                     a=a+"\\" ;
                }
            
            
            }
            
            urlInserted=a;
            
            
            tfpdf.setText(selectedFile.getAbsolutePath());
            

            //imageView1.setText(selectedFile.getName());
            /*
            Image image1 = new Image(selectedFile.toURI().toString());
            imageView1.setImage(image1);
             */
        } else {
            System.out.println("Not valid file");
        }
    }

    
}
