/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class StatisticsController implements Initializable {

    @FXML
    private PieChart pieChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
        new PieChart.Data("Informatique",2),
        new PieChart.Data("Mecanique",2),
        new PieChart.Data("Cuisine",2)
        );
        pieChart= new PieChart(pieData);
        Group root=new Group(pieChart);
        Scene scene=new Scene(root,600,400);
        Stage primaryStage = null;
        primaryStage.setTitle("Pie chart");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // TODO
    }    
    
}
