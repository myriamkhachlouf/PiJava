/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.formateur;
import entities.formation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MyConnection;

/**
 *
 * @author Admin
 */
public class formationCrud {
    
     public void addformation(formation f){
        try {
            String requete="INSERT INTO formation(reference,periode,objectif,dure,capacite,rating) "
                    + "VALUES (?,?,?,?,?,?)";
            PreparedStatement pst =
                    new MyConnection().cn.prepareStatement(requete);
            pst.setInt(1, f.getReference());
            pst.setInt(2, f.getPeriode());
            pst.setString(3, f.getObjectif());
          pst.setInt(4, f.getDure());
          pst.setInt(5, f.getCapacite());
          pst.setInt(6, f.getRating());
            pst.executeUpdate();
            System.out.println("formation ajoutée!");
        } catch (SQLException ex) {
            Logger.getLogger(formationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
     public List<formation> listertype() throws SQLException 
    {
       
      String sql = "SELECT * FROM formation ";
        ObservableList<formation> listType = FXCollections.observableArrayList();
        
       
        PreparedStatement pste=new MyConnection().cn.prepareStatement(sql);
        ResultSet result = pste.executeQuery();
     
        try {
            

        while (result.next()){
  
            listType.add(new formation(result.getInt("id"),result.getInt("reference"),result.getInt("periode"),result.getString("objectif"),result.getInt("dure"),result.getInt("capacite"),result.getInt("rating"),result.getString("nom")));
             
          
            
        }
      
      } catch (SQLException ex) {
           System.out.println("Mokhlq fil lister type");
              
    }
    return (listType);

    }
     
     
     public void supprimerFormation(int id){
          try{
              String requete="DELETE FROM formation WHERE id="+id;
              PreparedStatement pst =
                    new MyConnection().cn.prepareStatement(requete);
              pst.executeUpdate();
              System.out.println("formation supprime");
              
              
          }catch(SQLException ex) {
            Logger.getLogger(formationCrud.class.getName()).log(Level.SEVERE, null, ex);
              
          }
          
      }
    


public static void updateFormation(formation oldc, formation newc){
        try {
                 String requete="UPDATE formation SET reference = ?,periode= ?, objectif= ?,dure= ?,capacite= ?,rating= ? WHERE id=? ";
                 PreparedStatement pst= new MyConnection().cn.prepareStatement(requete);
            
            pst.setInt(1,newc.getReference());
            pst.setInt(2, newc.getPeriode());
            pst.setString(3, newc.getObjectif());
            pst.setInt(4, newc.getDure());
            pst.setInt(5, newc.getCapacite());
            pst.setInt(6, newc.getRating());
            pst.setInt(7, oldc.getId());
             
            
            pst.executeUpdate();
             
             } catch (SQLException ex) {
                 System.err.println(ex.getMessage());
             }
}
    
}
