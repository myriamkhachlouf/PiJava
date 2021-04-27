/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.test.gui;

import edu.test.utils.DataBase;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
/**
 * FXML Controller class
 *
 * @author Asus
 */
public class BarChartController implements Initializable {
private BarChart<?, ?> chart;

    @FXML
    private PieChart Piechart;
       ObservableList< PieChart.Data> piechartdata;
  ArrayList< String> p = new ArrayList< String>();
    ArrayList< Integer> c = new ArrayList< Integer>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          loadData();
//          piechart.setData(piechartdata);
//      

    }  
    public void loadData() {

        String query = "select COUNT(*) as count ,nom from Recruteur GROUP BY domaine "; //ORDER BY P asc

        piechartdata = FXCollections.observableArrayList();

        Connection con = DataBase.getInstance().getConnection();

        try {

            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {

                piechartdata.add(new PieChart.Data(rs.getString("nom"), rs.getInt("count")));
                p.add(rs.getString("nom"));
                c.add(rs.getInt("count"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    }    
    

