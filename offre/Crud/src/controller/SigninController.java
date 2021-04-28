/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.jdi.connect.spi.Connection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Entities.users;
import Entities.UserSession;
import Service.UsersService;
import utils.MaConnexion;


/**
 * FXML Controller class
 *
 * @author narj
 */
public class SigninController implements Initializable {

    @FXML
    private AnchorPane pane_login;
    @FXML
    private TextField txt_mail;
    @FXML
    private PasswordField txt_password;
    @FXML
    private ComboBox type=null;
    @FXML
    private Button btn_login;
    @FXML
    private Button inscri;
    @FXML
    private Button btn_login1;
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //type_up.getItems().addAll("[\"ROLE_CANDIDAT\"]","[\"ROLE_ENTREPRISE\"]");  
        type.getItems().addAll("[\"ROLE_CANDIDAT\"]","[\"ROLE_ENTREPRISE\"]","[\"ROLE_ADMIN\"]");
    }    

    @FXML
     public void login1(ActionEvent event) throws Exception {

         UsersService US=new UsersService();
        Integer id = US.Login(txt_mail.getText(),txt_password.getText(),type.getValue().toString());
        System.out.println("javaapplication1.AuthController.login1() id " + id);
  //      Notifications.create().title("Wronge Pin").text("Your pin is exceeding limit or your pin is consists\n" + "of invalid characters")
//	.hideAfter(Duration.seconds(5)).showError();
        
        if (id >0)
        {
  /*              Notifications notificationBuilder = Notifications.create();
        
                notificationBuilder.title("saved");
                notificationBuilder.text("saved");
                notificationBuilder.graphic(null);
                notificationBuilder.hideAfter(Duration.seconds(5));
                notificationBuilder.position(Pos.TOP_RIGHT);
                notificationBuilder.showConfirm();
            /* Notifications.create().title("saved")
                    .text("saved")
                    .showWarning();
            */

            MaConnexion.getInstance().connectedUserID = id;
        
            btn_login.getScene().getWindow().hide();
            Parent root;
            users u=US.getUserByID(id);
            
            if (u.getRoles().equals("[\"ROLE_ADMIN\"]")==true){
         //notificationBuilder.showConfirm();
                root = FXMLLoader.load(getClass().getResource("/gui/PannelAdmin.fxml"));  
            }
            else if (u.getRoles().equals("[\"ROLE_CANDIDAT\"]")==true){
                root = FXMLLoader.load(getClass().getResource("/gui/PannelClient.fxml"));  
            }
            else{
                root = FXMLLoader.load(getClass().getResource("/gui/PannelEntrepriseFXML.fxml")); 
                 
            }
            Stage mainstage = new Stage();
            Scene scene = new Scene(root);
            mainstage.setScene(scene);
            mainstage.show();
            UserSession.getInstance(type.getValue().toString(),txt_mail.getText());
        }

                
            
    }

    @FXML
    private void forgotPass(ActionEvent event) {                 
        try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login/sendCode.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));  
                stage.show();

        } catch(Exception e) {
          }
 
    }

    @FXML
    private void inscription(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/signup.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            
    } catch(Exception e) {
      }
    }

    

}
