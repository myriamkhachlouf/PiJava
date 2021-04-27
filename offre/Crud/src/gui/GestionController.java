/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class GestionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void formateur(ActionEvent event) throws IOException {
        
        Parent loader = FXMLLoader.load(getClass().getResource("formateur.fxml"));
            //  Parent root  = loader.load();
            Scene  scene = new Scene(loader);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("les formateur");
            window.setScene(scene);
            window.show();
    }

    @FXML
    private void evenement(ActionEvent event) throws IOException {
         Parent loader = FXMLLoader.load(getClass().getResource("event.fxml"));
            //  Parent root  = loader.load();
            Scene  scene = new Scene(loader);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Les evenement");
            window.setScene(scene);
            window.show();
    }

    @FXML
    private void formation(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("formation.fxml"));
            //  Parent root  = loader.load();
            Scene  scene = new Scene(loader);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Les evenement");
            window.setScene(scene);
            window.show();
    }
    
}
