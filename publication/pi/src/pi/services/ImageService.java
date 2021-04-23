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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pi.entities.Publication;
import pi.entities.image;
import pi.utils.MaConnexion;

/**
 *
 * @author Mahmoud
 */
public class ImageService {
    Connection cnx = MaConnexion.getInstance().getCnx();
    public image getImage(int postid){
        try{
          
           String req="SELECT image.id,image.main_url,image.cover_url FROM image INNER JOIN publication ON publication.image_id=image.id WHERE publication.id="+postid;
           Statement st = cnx.createStatement();
           
          ResultSet rst = st.executeQuery(req);
            rst.next();
            image im=new image();
              im.setId(rst.getInt("image.id"));
              im.setMain_url(rst.getString("main_url"));
              im.setCover_url(rst.getString("cover_url"));
            return im;
          // JOptionPane.showMessageDialog(null, "Post Added Successfuly !");
           
       }
        catch(SQLException ex){
            
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE,null,ex);
        }
         image im2=new image();
         im2.setMain_url("under_construction_40px.png");
         im2.setCover_url("under_construction_40px.png");
       return im2;
    }    
    
public int getLastId() {
       try{
          
           String req="SELECT MAX(id) FROM image;";
           Statement st = cnx.createStatement();
           
          ResultSet rst = st.executeQuery(req);
            rst.next();
            Integer id = rst.getInt("MAX(id)");
            return id;
          // JOptionPane.showMessageDialog(null, "Post Added Successfuly !");
           
       }
        catch(SQLException ex){
            
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE,null,ex);
        }
       return -1;
    }    
 public void add(image entity) {
       try{
          
           String req="INSERT INTO image(main_url,cover_url)"
                + "VALUES(?,?)";
           PreparedStatement pst = cnx.prepareStatement(req);
           
           pst.setString(2,entity.getMain_url());
           pst.setString(1,entity.getCover_url());
           pst.executeUpdate();
          // JOptionPane.showMessageDialog(null, "Post Added Successfuly !");
                  }
        catch(SQLException ex){
            
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE,null,ex);
        }
       
    }    
}
