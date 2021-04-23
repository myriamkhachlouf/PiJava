/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_offres;

import Utils.Maconnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author azizs
 */
public class Stat_offreController implements Initializable {

    @FXML
    private PieChart piechart;
    ObservableList< PieChart.Data> piechartdata;
    ArrayList< String> p = new ArrayList< String>();
    ArrayList< Integer> c = new ArrayList< Integer>();
    @FXML
    private Button prec;
    @FXML
    private AnchorPane fils;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData() ; 
        piechart.setData(piechartdata);

        
        // TODO
    }   
    
    
    public void loadData() {

        String query = "select COUNT(*) as count ,entreprise_id from offre GROUP BY entreprise_id "; //ORDER BY P asc

        piechartdata = FXCollections.observableArrayList();

        Connection con = Maconnexion.getInstance().getConnection();

        try {

            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {

                piechartdata.add(new PieChart.Data(rs.getString("entreprise_id"), rs.getInt("count")));
                p.add(rs.getString("entreprise_id"));
                c.add(rs.getInt("count"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void prec(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gestion_offres/metiers.fxml"));
         fils.getChildren().setAll(pane);
    }
    
}
