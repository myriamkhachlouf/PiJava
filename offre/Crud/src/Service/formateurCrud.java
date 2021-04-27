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
/**
 *
 * @author Bouhejba
 */
public class formateurCrud {
    
     public void addformateur(formateur f){
        try {
            String requete="INSERT INTO formateur(nom,prenom,statut,typecontrat,email,password) "
                    + "VALUES (?,?,?,?,?,?)";
            PreparedStatement pst =
                    new MyConnection().cn.prepareStatement(requete);
            pst.setString(1, f.getNom());
            pst.setString(2, f.getPrenom());
            pst.setString(3, f.getStatut());
          pst.setString(4, f.getTypecontrat());
          pst.setString(5, f.getEmail());
          pst.setInt(6, f.getPassword());
            pst.executeUpdate();
            System.out.println("formateur ajoutée!");
        } catch (SQLException ex) {
            Logger.getLogger(formateurCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
     public List<formateur> listertype() throws SQLException 
    {
       
      String sql = "SELECT * FROM formateur ";
        ObservableList<formateur> listType = FXCollections.observableArrayList();
        
       
        PreparedStatement pste=new MyConnection().cn.prepareStatement(sql);
        ResultSet result = pste.executeQuery();
     
        try {
            

        while (result.next()){
  
            listType.add(new formateur(result.getInt("id"),result.getString("nom"),result.getString("prenom"),result.getString("statut"),result.getString("typecontrat"),result.getString("email"),result.getInt("password")));
             
          
            
        }
      
      } catch (SQLException ex) {
           System.out.println("Mokhlq fil lister type");
              
    }
    return (listType);

    }
     
     
     public void supprimerFormateur(int id){
          try{
              String requete="DELETE FROM formateur WHERE id="+id;
              PreparedStatement pst =
                    new MyConnection().cn.prepareStatement(requete);
              pst.executeUpdate();
              System.out.println("formateur supprime");
              
              
          }catch(SQLException ex) {
            Logger.getLogger(formateurCrud.class.getName()).log(Level.SEVERE, null, ex);
              
          }
          
      }
    


public static void updateFormateur(formateur oldc, formateur newc){
        try {
                 String requete="UPDATE formateur SET nom = ?,prenom= ?, statut= ?,typecontrat= ?,email= ?,password= ? WHERE id=? ";
                 PreparedStatement pst= new MyConnection().cn.prepareStatement(requete);
            
            pst.setString(1,newc.getNom());
            pst.setString(2, newc.getPrenom());
            pst.setString(3, newc.getStatut());
            pst.setString(4, newc.getTypecontrat());
            pst.setString(5, newc.getEmail());
            pst.setInt(6, newc.getPassword());
            pst.setInt(7, oldc.getId());
             
            
            pst.executeUpdate();
             
             } catch (SQLException ex) {
                 System.err.println(ex.getMessage());
             }
}
}