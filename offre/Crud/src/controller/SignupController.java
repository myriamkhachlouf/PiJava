/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;

import java.util.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
//import org.controlsfx.control.Notifications;
import Entities.users;

import Service.UsersService;
import utils.MaConnexion;


/**
 * FXML Controller class
 *
 * @author narj
 */
public class SignupController implements Initializable {

    
    
    
    
    @FXML
    private AnchorPane pane_signup;
    @FXML
    private TextField txt_domaine_up;
    @FXML
    private TextField txt_email_up;
    @FXML
    private TextField txt_password_up;
    @FXML
    private ComboBox type_up=null;
    @FXML
    private TextField txt_telephone_up;
    @FXML
    private TextField txt_adresse_up;
    @FXML
    private TextField txt_nom_up;
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    private Button UpdateUserBTN;
    @FXML
    private Button AddUserBTN;
    @FXML
    private Label labelemail;
    
  /*  public void LoginPaneShow() {
        pane_login.setVisible(true);
        pane_signup.setVisible(false);
    } 
    public void SignupPaneShow() {
        pane_signup.setVisible(true);
        pane_login.setVisible(false);
    }*/
    @FXML
    private Label labelusername;
    @FXML
    private Label labelNum;
    @FXML
    private ImageView emailTick;
    @FXML
    private ImageView usernameTick;
    @FXML
    private ImageView mdpTick;
    @FXML
    private ImageView mdp2Tick;
    @FXML
    private ImageView numeroTick;
    @FXML
    private ImageView dateTick;
    @FXML
    private Label labelcmdp;
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      type_up.getItems().addAll("[\"ROLE_CANDIDAT\"]","[\"ROLE_ENTREPRISE\"]");  
     //   type.getItems().addAll("[\"ROLE_CANDIDAT\"]","[\"ROLE_ENTREPRISE\"]","[\"ROLE_ADMIN\"]");
    }    

    
   
        
            
    

   

    @FXML
    private void addusers(ActionEvent event) {
        users u= new users();
        u.setNom(txt_nom_up.getText());
        u.setEmail(txt_email_up.getText());
        u.setPassword(txt_password_up.getText());
        u.setTelephone(txt_telephone_up.getText());
        u.setAdresse(txt_adresse_up.getText());
        u.setDomaine(txt_domaine_up.getText());
        u.setRoles(type_up.getValue().toString());        
        UsersService US=new UsersService();
        US.add(u);
        JOptionPane.showMessageDialog(null,"user saved");
         
        /*Notifications notificationBuilder = Notifications.create()
                .title("User saved")
                .text("User saved1")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(ActionEvent<event>(){
                @Override
                 void handle( event){
                    System.out.println("hi");
                }
    })
     ;
        notificationBuilder.showConfirm();
          */
        
                
    }
    private void UpdateUser(ActionEvent event) {
        UpdateUserBTN.setOnAction(e->{
            users u=new users();
            u.setNom(txt_nom_up.getText());
            u.setEmail(txt_email_up.getText());
            u.setPassword(txt_password_up.getText());
            u.setTelephone(txt_telephone_up.getText());
            u.setAdresse(txt_adresse_up.getText());
            u.setDomaine(txt_domaine_up.getText());
            u.setRoles(type_up.getValue().toString());
            
        });
    }

    

   


}
