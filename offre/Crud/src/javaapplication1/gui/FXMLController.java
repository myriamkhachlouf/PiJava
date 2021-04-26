/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javaapplication1.services.UsersService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class FXMLController implements Initializable {

    @FXML
    private Button AFFICHAGE;
    @FXML
    private Label AFFICHAGEBIDOU;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SHO(ActionEvent event) {
        new UsersService().getAll().stream().forEach(u->{
        AFFICHAGEBIDOU.setText(AFFICHAGEBIDOU.getText()+"\n"+u.toString());
        });
    }
    
}
