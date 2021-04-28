/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Emplois;
import Entities.Stage;
import utils.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;

/**
 *
 * @author 21624
 */
public class ServiceStage  {
      Connection cnx;
    private List<Stage> stages;
    private PreparedStatement pre;
    private Statement ste;
    DatePicker date;
    public ServiceStage() {
cnx =MaConnexion.getInstance().getConnection();
        }  
    
   public boolean AddStage(Stage e) throws SQLException  {
  
        String req = "INSERT INTO stage (id,offre_id,date_debut,date_fin,type_du_stage,nom_encadrant) VALUES ('"+e.getId()+"','"+e.getOffre_id()+"','"+e.getDate_debut()+"','"+e.getDate_fin()+"','"+e.getType_du_stage()+"','"+e.getNom_encadrant()+"')";
         Connection cnx =null;
        Statement st = null;
         try {
            cnx = MaConnexion.getInstance().getConnection();
            st = cnx.createStatement();
            st.executeUpdate(req);
            return true;


        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally {

    if (st != null) {
        try {
            st.close();
        } catch (SQLException l) { /* Ignored */}
    }
    }    
    }
    public ObservableList<Stage> afficherStage() throws SQLException {
     Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Stage> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM stage";

        try {
            cnx = MaConnexion.getInstance().getConnection();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            Stage Stages;
            while (rs.next()){
               Stages = new Stage(rs.getInt("id"),rs.getInt("offre_id"),rs.getString("date_debut"),rs.getString("date_fin"),rs.getString("type_du_stage"),rs.getString("nom_encadrant"));
               liste.add(Stages);
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException e) { /* Ignored /}
    }
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { / Ignored */}
    }
    }
        return liste; 
 }
     public void supprimerStage(Stage a){
       String req="delete from stage where id=?";
       
        try {
            
            PreparedStatement stm;
            stm=cnx.prepareStatement(req);
            stm.setInt(1,a.getId() );
            int i=stm.executeUpdate();
            System.out.println(i+ " stage suprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   }
      public int ModifierStage(int id,Stage a) throws SQLException {
        if(chercher(id)){
        
        pre=cnx.prepareStatement("UPDATE stage SET offre_id=?, date_debut = ? , date_fin = ? , type_du_stage = ? , nom_encadrant = ?  WHERE id = "+id+"");
    try{     
    pre.setInt(1, a.getOffre_id());
    pre.setString(2, a.getDate_debut());
    pre.setString(3, a.getDate_fin());
    pre.setString(4, a.getType_du_stage());
    pre.setString(5, a.getNom_encadrant());   

   
    
    
    pre.executeUpdate();
    }
    catch (SQLException e){
      System.out.println(e.getMessage());  
    }
    return 1;}
        return 0;
    }
      public boolean chercher(int id) throws SQLException {
        String req="select * from stage";
        List<Integer> list = new ArrayList<>();
        
        try {
            ste=cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffres.class.getName()).log(Level.SEVERE, null, ex);
        }
        list.forEach(System.out::println);
        return list.contains(id);
    }
}
