/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class StatistiqueFormationController implements Initializable {

    @FXML
    private BarChart<String, Integer> barChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
    }    

    @FXML
    private void retour(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("formation.fxml"));
            Scene scene = new Scene(loader);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // AdminPageController apc= loader.getController();
            window.setTitle("Affichage page");
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(StatistiqueFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
    

    private void load() {
        
        String sql = "SELECT objectif,capacite FROM formation";
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        try {
            PreparedStatement pste = new MyConnection().cn.prepareStatement(sql);
            ResultSet rs = pste.executeQuery();
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));
            }
            barChart.getData().add(series);
        } catch (Exception e) {

        }
        
    }
    
}
