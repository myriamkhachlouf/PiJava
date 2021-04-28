/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_offres;

import utils.MaConnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author azizs
 */
public class Stat_emploiController implements Initializable {

    @FXML
    private AnchorPane fils;
    @FXML
    private PieChart piechart;
    @FXML
    private Button prec;
    ObservableList< PieChart.Data> piechartdata;
    ArrayList< String> p = new ArrayList< String>();
    ArrayList< Integer> c = new ArrayList< Integer>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData() ; 
        piechart.setData(piechartdata);
    }    

    public void loadData() {

        String query = "SELECT nom_offre,offre_id , COUNT(*) as count FROM emploi INNER JOIN offre ON emploi.offre_id = offre.id group by nom_offre  "; //ORDER BY P asc

        piechartdata = FXCollections.observableArrayList();

        Connection con = MaConnexion.getInstance().getConnection();

        try {

            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {

                piechartdata.add(new PieChart.Data(rs.getString("nom_offre"), rs.getInt("count")));
                p.add(rs.getString("nom_offre"));
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
