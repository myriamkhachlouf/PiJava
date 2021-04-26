/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import com.sun.jdi.connect.spi.Connection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javaapplication1.entities.UserSession;
import javaapplication1.services.UsersService;
import javafx.event.ActionEvent;
import javaapplication1.utils.MaConnexion;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javaapplication1.entities.users;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AuthController implements Initializable {

   
   
    
    @FXML
    private AnchorPane pane_login;
    @FXML
    private TextField txt_mail;
    @FXML
    private PasswordField txt_password;
    @FXML
    private ComboBox type;
    @FXML
    private Button btn_login;
    @FXML
    private AnchorPane pane_signup;
    @FXML
    private TextField txt_domaine_up;
    @FXML
    private TextField txt_email_up;
    @FXML
    private TextField txt_password_up;
    @FXML
    private ComboBox type_up;
    @FXML
    private TextField txt_telephone_up;
    @FXML
    private TextField txt_adresse_up;
    @FXML
    private TextField txt_nom_up;
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    @FXML
    private Button AddUserBTN;
    private Button UpdateUserBTN;
    @FXML
    private Button btn_login1;
    
    @FXML
    public void LoginPaneShow() {
        pane_login.setVisible(true);
        pane_signup.setVisible(false);
    } 
    @FXML
    public void SignupPaneShow() {
        pane_signup.setVisible(true);
        pane_login.setVisible(false);
    }
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type_up.getItems().addAll("Candidat","Entreprise");
        type.getItems().addAll("Candidat","Entreprise","Admin");
    }    

    
    @FXML
     public void login1(ActionEvent event) throws Exception {
        UsersService US=new UsersService();
        Integer id = US.Login(txt_mail.getText(),txt_password.getText(),type.getValue().toString());
        System.out.println("javaapplication1.AuthController.login1() id " + id);
        
        if (id >0)
        {
            MaConnexion.getInstance().connectedUserID = id;
        
            btn_login.getScene().getWindow().hide();
            Parent root;
            if (id==36){
                root = FXMLLoader.load(getClass().getResource("UserInterface.fxml"));  
            }
            else{
                root = FXMLLoader.load(getClass().getResource("SimpleUserInterface.fxml")); 
                 
            }
            Stage mainstage = new Stage();
            Scene scene = new Scene(root);
            mainstage.setScene(scene);
            mainstage.show();
            UserSession.getInstance(type.getValue().toString(),txt_mail.getText());
        }
        
            
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

    @FXML
    private void forgotPass(ActionEvent event) {                 
    try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sendCode.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            
    } catch(Exception e) {
      }
 
    }

  

    
   
    
}
