/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_offres;

import Entities.Offres;
import Service.ServiceOffres;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author azizs
 */
public class InfoOffreController implements Initializable {

    @FXML
    private Label nom_offre;
    @FXML
    private ImageView imagelol;
    @FXML
    private Rating rating;
    @FXML
    private Label nom_offre1;
    @FXML
    private Button Btn_precedent;
    @FXML
    private AnchorPane fils;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        }    
    public void show1(int id){
        ServiceOffres pda = ServiceOffres.getInstance();
       Offres p1 = new Offres();
        p1=pda.displayById(id);
        nom_offre.setText(p1.getNom_offre());
        nom_offre1.setText(p1.getType());
       rating.setRating(p1.getNote());
        System.out.print(p1.getId());
      Image image = new Image(p1.getImage_name());
           
      
           imagelol.setImage(image);
        
    }

    @FXML
    private void precedent(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gestion_offres/metiers.fxml"));
         fils.getChildren().setAll(pane);
    }
}
