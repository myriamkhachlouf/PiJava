/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.formateur;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MyConnection;
import entities.evenement;
/**
 *
 * @author Admin
 */
public class evenementCrud {
    
    public void addevenement(evenement e){
        try {
            String requete="INSERT INTO evenement(nom,description,email,logo,date) "
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement pst =
                    new MyConnection().cn.prepareStatement(requete);
            pst.setString(1, e.getNom());
            pst.setString(2, e.getDescription());
            pst.setString(3, e.getEmail());
          pst.setString(4, e.getLogo());
          pst.setString(5, e.getDate());
         
            pst.executeUpdate();
            System.out.println("evenement ajoutée!");
        } catch (SQLException ex) {
            Logger.getLogger(formateurCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
     public List<evenement> listerevent() throws SQLException 
    {
       
      String sql = "SELECT * FROM evenement ";
        ObservableList<evenement> listevent = FXCollections.observableArrayList();
        
       
        PreparedStatement pste=new MyConnection().cn.prepareStatement(sql);
        ResultSet result = pste.executeQuery();
     
        try {
            

        while (result.next()){
  
            listevent.add(new evenement(result.getInt("id"),result.getString("nom"),result.getString("description"),result.getString("email"),result.getString("logo"),result.getString("date")));
             
          
            
        }
      
      } catch (SQLException ex) {
           System.out.println("Mokhlq fil lister type");
              
    }
    return (listevent);

    }
     
     
     public void supprimerEvenement(int id){
          try{
              String requete="DELETE FROM evenement WHERE id="+id;
              PreparedStatement pst =
                    new MyConnection().cn.prepareStatement(requete);
              pst.executeUpdate();
              System.out.println("evenement supprimer");
              
              
          }catch(SQLException ex) {
            Logger.getLogger(formateurCrud.class.getName()).log(Level.SEVERE, null, ex);
              
          }
          
      }
     
     public static void updateEvent(evenement oldc, evenement newc){
        try {
                 String requete="UPDATE evenement SET nom = ?,description = ?,email = ?,logo = ?,date = ? WHERE id = ?";
                 PreparedStatement pst= new MyConnection().cn.prepareStatement(requete);
            
            pst.setString(1,newc.getNom());
            pst.setString(2, newc.getDescription());
            pst.setString(3, newc.getEmail());
            pst.setString(4, newc.getLogo());
            pst.setString(5, newc.getDate());
            pst.setInt(6, oldc.getId());
                pst.executeUpdate();
             
             } catch (SQLException ex) {
                 System.err.println(ex.getMessage());
             }



}


    
}
