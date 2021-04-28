/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import Entities.users;
import Service.ChartLib;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import Service.PDFLib;
import Service.UsersService;

/* *
 * @author Mohamed
 */
public class FXMain extends Application  {
    
    @Override
    public void start(Stage stage) throws IOException, Exception {
        
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Auth.fxml"));
        System.out.println("Application launched successfully");
        
        
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        
                
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void createuserListReport () {
         
    }
    
}
