/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import Entities.users;
import Service.UsersService;
import Service.sendEmail2;
import utils.MaConnexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class SendCodeController implements Initializable {
    
    @FXML
    private TextField Email_txt;
    @FXML
    private TextField Code_txt;
    @FXML
    private Button SendCodeBtn;
    @FXML
    private Button verifyCodeBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sendCode(ActionEvent event) {
        Random rand = new Random(); 
        UsersService us = new UsersService() ;
        float float_random=rand.nextFloat();
        String Email=Email_txt.getText();
        users u=us.getUserByEmail(Email);
        if (u!=null)
        {
            sendEmail2.sendToken(Email, (int) (float_random*100000));
            int code=(int) (float_random*100000);
            u.setReset_token(String.valueOf(code));
            us.Save(u);
            JOptionPane.showMessageDialog(null, "Check your email");
        }
        else{
            JOptionPane.showMessageDialog(null, "Mail doesn't exist");
        }
    }

    @FXML
    private void verifyCode(ActionEvent event) throws IOException {
        UsersService us = new UsersService() ;
        String Email=Email_txt.getText();
        users u=us.getUserByEmail(Email);
        
        if (u.getReset_token().equals(Code_txt.getText())){
            Stage stage = (Stage) verifyCodeBtn.getScene().getWindow();
            // do what you have to do
            stage.close();
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewPassword.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            //MaConnexion.useremail =u.getEmail(); 
            
            stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            NewPasswordController controller = fxmlLoader.<NewPasswordController>getController();
            controller.setEmail(u.getEmail());

        } else {
            JOptionPane.showMessageDialog(null, "Code incorrect");
        }
    }
    
}
