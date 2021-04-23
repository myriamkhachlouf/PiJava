/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mahmoud
 */
public class RoleFXMLController implements Initializable {

    @FXML
    private Button Entreprise;
    @FXML
    private Button candidat;
    @FXML
    private Button admin;
     public static String role;
    public static int user_id;

    public String getRole() {
        return role;
    }
    public int getUser_id() {
        return user_id;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void gotoentreprise(ActionEvent event) throws IOException {
        role="entreprise";
       user_id=5; 
       FXMLLoader loader = new FXMLLoader(getClass().getResource("RoleFXML.fxml"));
       Parent root =  loader.load();
       RoleFXMLController roleController=loader.getController(); 
       roleController.setRole(role);
       roleController.setUser_id(user_id);
       
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("publicationFXML.fxml"));
       Parent root2 = (Parent) loader2.load();
       PublicationFXMLController pubController=loader2.getController();  
       pubController.setRole(role);
       pubController.setUser_id(user_id);
       
       Stage stage =new Stage();
        Scene scene = new Scene(root2);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void setRole(String role) {
        RoleFXMLController.role = role;
    }

    public void setUser_id(int user_id) {
        RoleFXMLController.user_id = user_id;
    }

    @FXML
    private void gotocandidat(ActionEvent event) throws IOException {
        role="candidat";
       user_id=1; 
       FXMLLoader loader = new FXMLLoader(getClass().getResource("RoleFXML.fxml"));
       Parent root =  loader.load();
       RoleFXMLController roleController=loader.getController(); 
       roleController.setRole(role);
       roleController.setUser_id(user_id);
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("BlogHomeFXML.fxml"));
       Parent root1 = (Parent) loader1.load();
       Stage stage =new Stage();
        Scene scene = new Scene(root1);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    private void gotoadmin(ActionEvent event) throws IOException {
        role="admin";
       user_id=6; 
       FXMLLoader loader = new FXMLLoader(getClass().getResource("RoleFXML.fxml"));
       Parent root =  loader.load();
       RoleFXMLController roleController=loader.getController(); 
       roleController.setRole(role);
       roleController.setUser_id(user_id);
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("publicationFXML.fxml"));
       Parent root2 = (Parent) loader2.load();
       PublicationFXMLController pubController=loader2.getController();
       pubController.setRole(role);
       pubController.setUser_id(user_id);
       Stage stage =new Stage();
        Scene scene = new Scene(root2);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    
}
