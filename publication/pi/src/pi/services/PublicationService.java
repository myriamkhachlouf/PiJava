/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.services;
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
public class PublicationService implements IService<Publication> {
Connection cnx = MaConnexion.getInstance().getCnx();
    @Override
    public int add(Publication entity) {
       try{
          
           String req="INSERT INTO Publication(postedby_id,title,contenu,created_at,updated_at)"
                + "VALUES(?,?,?,NOW(),NOW())";
           PreparedStatement pst = cnx.prepareStatement(req);
           
           pst.setInt(1,entity.getPostedby_id());
           pst.setString(2,entity.getTitle());
           pst.setString(3,entity.getContenu());
           //pst.setDate(4,entity.getCreatedAt());
           //pst.setDate(5, date);
           pst.executeUpdate();
          // JOptionPane.showMessageDialog(null, "Post Added Successfuly !");
           return 1;
       }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Post is not Added Successfuly !");
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE,null,ex);
        }
       return 0;
    }

    @Override
    public ArrayList<Publication> getAll() {
        ArrayList<Publication> publications=new ArrayList<>();
        String req = "SELECT id,title,contenu,created_at,updated_at,views FROM Publication ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
            while (rst.next()){
                Publication p= new Publication();
                p.setId(rst.getInt("id"));
                p.setTitle(rst.getString("title"));
                p.setCreatedAt(rst.getTimestamp("created_at"));
                p.setUpdatedAt(rst.getTimestamp("updated_at"));
                p.setViews(rst.getInt("views"));
                p.setContenu(rst.getString("contenu"));
                 
                publications.add(p);
            }
        }
        catch(SQLException ex){
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE,null,ex);
        }
        return (publications);
    }

    @Override
    public int Edit(Publication entity) {
    try {
        String req="UPDATE Publication SET title=? , contenu=? , updated_at=NOW() WHERE id=?";
        PreparedStatement pst = cnx.prepareStatement(req);
        
        pst.setString(1,entity.getTitle());
        pst.setString(2,entity.getContenu());
        pst.setInt(3,entity.getId());
        pst.executeUpdate();
        //JOptionPane.showMessageDialog(null, "Your Post is Updated Successfuly");
        return 1;
        
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Your Post is not Updated ");
        Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return 0;
    }

    @Override
    public int Delete(int id) {
         try{
          
           String req="DELETE FROM Publication WHERE id=?";
           PreparedStatement pst = cnx.prepareStatement(req);
           pst.setInt(1,id);
           pst.executeUpdate();
           //JOptionPane.showMessageDialog(null, "Your Post is Deleted Successfuly !");
           return 1;
       }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Your Post is Not Deleted !");
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE,null,ex);
        return 0;
        }
    }

    @Override
    public Publication getPostById(int id) {
        Publication p=new Publication();
        String req = "SELECT id,title,contenu,created_at,updated_at,views FROM Publication WHERE id="+id;
        try {
          Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
            while (rst.next()){   
            p.setId(rst.getInt("id"));
                p.setTitle(rst.getString("title"));
                p.setCreatedAt(rst.getTimestamp("created_at"));
                p.setUpdatedAt(rst.getTimestamp("updated_at"));
                p.setViews(rst.getInt("views"));
                p.setContenu(rst.getString("contenu"));
                 
                return p;
            }
        }
        catch(SQLException ex){
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }
    return null;   
    }

    @Override
    public ArrayList<Publication> getFourLast() {
 ArrayList<Publication> publications=new ArrayList<>();
        String req = "SELECT id,title,contenu,created_at,updated_at,views FROM Publication ORDER BY created_at DESC LIMIT 4;";
        try {
            Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
            while (rst.next()){
                Publication p= new Publication();
                p.setId(rst.getInt("id"));
                p.setTitle(rst.getString("title"));
                p.setCreatedAt(rst.getTimestamp("created_at"));
                p.setUpdatedAt(rst.getTimestamp("updated_at"));
                p.setViews(rst.getInt("views"));
                p.setContenu(rst.getString("contenu"));
                 
                publications.add(p);
            }
        }
        catch(SQLException ex){
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE,null,ex);
        }
        return (publications);    }

    @Override
    public int getIdFromTitle(String s) {
String req = "SELECT id FROM Publication WHERE title="+"'"+s+"'";
        try {
            Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
            rst.next();
            Integer id = rst.getInt("id");
            return id;
            }
        
        catch(SQLException ex){
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE,null,ex);
        }
        return -1;
         }

    @Override
    public ArrayList<Publication> getUserPosts(int user_id) {
ArrayList<Publication> publications=new ArrayList<>();
        String req = "SELECT id,title,contenu,created_at,updated_at,views FROM Publication WHERE postedby_id="+user_id;
        try {
            Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
            while (rst.next()){
                Publication p= new Publication();
                p.setId(rst.getInt("id"));
                p.setTitle(rst.getString("title"));
                p.setCreatedAt(rst.getTimestamp("created_at"));
                p.setUpdatedAt(rst.getTimestamp("updated_at"));
                p.setViews(rst.getInt("views"));
                p.setContenu(rst.getString("contenu"));
                 
                publications.add(p);
            }
        }
        catch(SQLException ex){
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE,null,ex);
        }
        return (publications);    }

    @Override
    public String getUsernameById(int id) {
String req = "SELECT nom FROM users WHERE id="+id;
     try {
            //Statement st = cnx.createStatement();
           PreparedStatement pst = cnx.prepareStatement(req);
           
            ResultSet rst = pst.executeQuery(req);
            
           rst.next();
            String username = rst.getString("nom");
            return username;
            }
        
        catch(SQLException ex){
            
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }    }

    @Override
    public String getPosterName(int post_id) {
       String req = "select users.nom from users INNER JOIN publication on publication.postedby_id=users.id where publication.id="+post_id;
     try {
            //Statement st = cnx.createStatement();
           PreparedStatement pst = cnx.prepareStatement(req);
           
            ResultSet rst = pst.executeQuery(req);
            
           rst.next();
            String username = rst.getString("nom");
            return username;
            }
        
        catch(SQLException ex){
            
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }    
    }

    @Override
    public int getIdPoster(int post_id) {
String req = "select users.id from users INNER JOIN publication on publication.postedby_id=users.id where publication.id="+post_id;
     try {
            //Statement st = cnx.createStatement();
           PreparedStatement pst = cnx.prepareStatement(req);
           
            ResultSet rst = pst.executeQuery(req);
            
           rst.next();
            int id = rst.getInt("id");
            return id;
            }
        
        catch(SQLException ex){
            
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE,null,ex);
            return -1;
        }        }

    @Override
    public void updateviews(int post_id) {
 try {
     
        String req="UPDATE publication SET views=IFNULL(views,0)+1 WHERE id="+post_id;
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.executeUpdate();
               
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Your Post is not Updated ");
        Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
    }
   
    }    

    @Override
    public ArrayList<Publication> getSearchedPost(String s) {
ArrayList<Publication> publications=new ArrayList<>();
        String req = "SELECT id,title,contenu,created_at,updated_at,views FROM Publication WHERE CONCAT(id,title,contenu,created_at,updated_at) LIKE '%"+s+"%';";
        try {
            Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
            while (rst.next()){
                Publication p= new Publication();
                p.setId(rst.getInt("id"));
                p.setTitle(rst.getString("title"));
                p.setCreatedAt(rst.getTimestamp("created_at"));
                p.setUpdatedAt(rst.getTimestamp("updated_at"));
                p.setViews(rst.getInt("views"));
                p.setContenu(rst.getString("contenu"));
                 
                publications.add(p);
            }
        }
        catch(SQLException ex){
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE,null,ex);
        }
        return (publications);    }
    
    @Override
    public ArrayList<Publication> getPostByDay() {
        ArrayList<Publication> publications=new ArrayList<>();
        String req = "SELECT id,title,contenu,created_at,updated_at,views FROM Publication WHERE DAY(created_at)=DAY(NOW())";
        try {
            Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
            while (rst.next()){
                Publication p= new Publication();
                p.setId(rst.getInt("id"));
                p.setTitle(rst.getString("title"));
                p.setCreatedAt(rst.getTimestamp("created_at"));
                p.setUpdatedAt(rst.getTimestamp("updated_at"));
                p.setViews(rst.getInt("views"));
                p.setContenu(rst.getString("contenu"));
                 
                publications.add(p);
            }
        }
        catch(SQLException ex){
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE,null,ex);
        }
        return (publications);    
    }

    @Override
    public ArrayList<Publication> getPostByMonth() {
  ArrayList<Publication> publications=new ArrayList<>();
        String req = "SELECT id,title,contenu,created_at,updated_at,views FROM Publication WHERE MONTH(created_at)=MONTH(NOW())";
        try {
            Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
            while (rst.next()){
                Publication p= new Publication();
                p.setId(rst.getInt("id"));
                p.setTitle(rst.getString("title"));
                p.setCreatedAt(rst.getTimestamp("created_at"));
                p.setUpdatedAt(rst.getTimestamp("updated_at"));
                p.setViews(rst.getInt("views"));
                p.setContenu(rst.getString("contenu"));
                 
                publications.add(p);
            }
        }
        catch(SQLException ex){
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE,null,ex);
        }
        return (publications);        }

    @Override
    public ArrayList<Publication> getPostByYear() {
  ArrayList<Publication> publications=new ArrayList<>();
        String req = "SELECT id,title,contenu,created_at,updated_at,views FROM Publication WHERE YEAR(created_at)=YEAR(NOW())";
        try {
            Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
            while (rst.next()){
                Publication p= new Publication();
                p.setId(rst.getInt("id"));
                p.setTitle(rst.getString("title"));
                p.setCreatedAt(rst.getTimestamp("created_at"));
                p.setUpdatedAt(rst.getTimestamp("updated_at"));
                p.setViews(rst.getInt("views"));
                p.setContenu(rst.getString("contenu"));
                 
                publications.add(p);
            }
        }
        catch(SQLException ex){
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE,null,ex);
        }
        return (publications);        }

    @Override
    public String getUserEmailById(int post_id) {
String req = "select users.email from users INNER JOIN publication on publication.postedby_id=users.id where publication.id="+post_id;
     try {
            //Statement st = cnx.createStatement();
           PreparedStatement pst = cnx.prepareStatement(req);
           
            ResultSet rst = pst.executeQuery(req);
            
           rst.next();
            String email = rst.getString("email");
            return email;
            }
        
        catch(SQLException ex){
            
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }    }
    
    
    
}
