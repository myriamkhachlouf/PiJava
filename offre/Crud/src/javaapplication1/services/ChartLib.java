/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication1.entities.users;
import javaapplication1.utils.MaConnexion;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 *
 * @author Mohamed
 */


 
public class ChartLib  {
    
    /*
    select domaine, count(*) as count from users
group by domaine
    */
    final static String austria = "Austria";
    final static String brazil = "Brazil";
    final static String france = "France";
    final static String italy = "Italy";
    final static String usa = "USA";
 
    public void demo(Stage stage) {
        stage.setTitle("Statistiques générales");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Utilisateurs par Domaine");
        xAxis.setLabel("Domaine");       
        yAxis.setLabel("Nombre");
        
        String sqlQuery = "select domaine, count(*) as count from users group by domaine";
        //get Data
        
        
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("nombre d'utilisateurs");       
        
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection cnx = MaConnexion.getInstance().getCnx();
        
        try {
            Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(sqlQuery);
            while (rst.next()){
                series1.getData().add(new XYChart.Data(rst.getString("domaine"), rst.getInt("count")));
            }
        }
        catch(SQLException ex){
            Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE,null,ex);
        }
       // return (users); 
        
        
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();
    }
 
}
