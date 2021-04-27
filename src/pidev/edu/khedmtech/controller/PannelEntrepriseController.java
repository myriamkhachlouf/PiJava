/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.khedmtech.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author fac
 */
public class PannelEntrepriseController implements Initializable {

    @FXML
    private HBox hboxmenu;
    @FXML
    private Button accueil;
    @FXML
    private MenuItem btEvent;
    @FXML
    private MenuItem btForm;
    @FXML
    private MenuItem btEvent11;
    @FXML
    private Button forum;
    @FXML
    private HBox hboximage;
    @FXML
    private ImageView khedmtch;
    @FXML
    private ImageView login;
    @FXML
    private ImageView LogoutBox;
    @FXML
    private AnchorPane container_client;
    @FXML
    private Label iduser;
    @FXML
    private MenuButton ente;
    @FXML
    private MenuItem btEvententr;
    @FXML
    private MenuItem btrec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    private void loadUi(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException ex) {
            ex.getMessage();
        }
        AnchorPane.setBottomAnchor(root, Double.NaN);
    }

    @FXML
    private void xxxxx(ActionEvent event) {
    }

    @FXML
    private void ReclCandidat(ActionEvent event) {
    }

    @FXML
    private void ReclFormation(ActionEvent event) {
    }

    @FXML
    private void MesRecl(ActionEvent event) {
    }

    @FXML
    private void StageOffre(ActionEvent event) {
    }

    @FXML
    private void TravailOffre(ActionEvent event) {
    }

    @FXML
    private void loadEvent(ActionEvent event) {
    }

    @FXML
    private void loadFormation(ActionEvent event) {
    }

    @FXML
    private void forum(ActionEvent event) {
    }

    @FXML
    private void logDirection(MouseEvent event) {
    }

    @FXML
    private void LogoutBox(MouseEvent event) {
    }

    @FXML
    private void loadentr(ActionEvent event) {
       loadUi("entretien");
    }

    @FXML
    private void loadRec(ActionEvent event) {
    }
    
}
