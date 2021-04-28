/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Entities.users;
import Service.UsersService;
import utils.MaConnexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class NewPasswordController implements Initializable {

    private String useremail;
    @FXML
    private Label email;
    @FXML
    private TextField password;
    @FXML
    private TextField password2;
    @FXML
    private Button resetBtn;

    public String getEmail() {
        return useremail;
    }

    public void setEmail(String emailv) {
        this.useremail = emailv;
        email.setText(emailv);
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        email.setText(getEmail());
    }
    
    @FXML
    public void verifPassword() throws IOException
    {
    UsersService usersService =new UsersService();
    
        if (password.getText().equals(password2.getText()))
        {
            usersService.SetPassword(useremail,password.getText());
            Stage stage = (Stage) resetBtn.getScene().getWindow();
            // do what you have to do
            stage.close();
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/singnin.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            //MaConnexion.useremail =u.getEmail(); 

            stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
        }
    }

    
    
    
    
}
