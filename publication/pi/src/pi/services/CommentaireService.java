/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.services;

import pi.entities.Commentaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pi.entities.Publication;
import pi.interfaces.IService;
import pi.utils.MaConnexion;
/**
 *
 * @author Mahmoud
 */
public class CommentaireService {
    Connection cnx = MaConnexion.getInstance().getCnx();
public int getUser_id(int comment_id){
    String req = "SELECT postedby_id FROM commentaire WHERE id="+comment_id;
     try {
            //Statement st = cnx.createStatement();
           PreparedStatement pst = cnx.prepareStatement(req);
           
            ResultSet rst = pst.executeQuery(req);
            
           rst.next();
            Integer id = rst.getInt("postedby_id");
            return id;
            }
        
        catch(SQLException ex){
            
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE,null,ex);
            return (-1);
        }
        
    
}
     public ArrayList<Commentaire> getPostComments(int postid) {
        
        ArrayList<Commentaire> commentaires=new ArrayList<>();
        
String req = "SELECT commentaire.id,commentaire.updated_at,commentaire.contenu,users.nom FROM commentaire INNER JOIN publication on commentaire.publication_id=publication.id" +
"        INNER JOIN users on commentaire.postedby_id=users.id\n" +
"        WHERE  commentaire.publication_id="+postid;
                
        try {
            //Statement st = cnx.createStatement();
           PreparedStatement pst = cnx.prepareStatement(req);
           
            ResultSet rst = pst.executeQuery(req);
            
            while (rst.next()){
           
                Commentaire c= new Commentaire();
                c.setId(rst.getInt("id"));
                c.setContenu(rst.getString("contenu"));
                c.setUpdated_at(rst.getTimestamp("updated_at"));
                c.setPostername(rst.getString("nom"));
                
                

                commentaires.add(c);
            }
        }
        catch(SQLException ex){
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE,null,ex);
        }
        return (commentaires);
    }
      public int add(Commentaire entity,int id) {
       try{
          
           String req="INSERT INTO commentaire(publication_id,postedby_id,contenu,created_at,updated_at)"
                + "VALUES(?,?,?,NOW(),NOW())";
           PreparedStatement pst = cnx.prepareStatement(req);
           
           pst.setInt(1,id);
           pst.setInt(2,entity.getPostedby_id());
           pst.setString(3,entity.getContenu());
           //pst.setDate(4,entity.getCreated_at());
            //pst.setDate(5,entity.getUpdated_at());
           pst.executeUpdate();
           return 1;
       }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "comment is not Added Successfuly !");
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE,null,ex);
        }
       return 0;
    }
       public int Delete(int id) {
         try{
          
           String req="DELETE FROM commentaire WHERE id=?";
           PreparedStatement pst = cnx.prepareStatement(req);
           pst.setInt(1,id);
           pst.executeUpdate();
           //JOptionPane.showMessageDialog(null, "Your Comment is Deleted Successfuly !");
           return 1;
       }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Your Comment is Not Deleted !");
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE,null,ex);
        return 0;
        }
    }
       public int Edit(String contenu,int id) {
    try {
        String req="UPDATE commentaire SET contenu=?,updated_at=NOW() WHERE id=?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setString(1,contenu);
        pst.setInt(2,id);
        pst.executeUpdate();
        //JOptionPane.showMessageDialog(null, "Your Post is Updated Successfuly");
        return 1;
        
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Your Post is not Updated ");
        Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return 0;
    }


}
