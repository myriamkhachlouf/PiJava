/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Offres;
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

/**
 *
 * @author 21624
 */
public class ServiceOffres  {
private static ServiceOffres instance;
    public static ServiceOffres getInstance() {
        if(instance==null) 
            instance=new ServiceOffres();
        return instance;
    }
      Connection cnx;
    private List<Offres> offres;
    private PreparedStatement pre;
    private Statement ste;
    public ServiceOffres() {
cnx =MaConnexion.getInstance().getConnection();
        }  
    public Offres displayById(int id) {
           String req="SELECT * FROM offre where id = '" + id + "'";
           Offres p=new Offres();
           System.out.println(id);
        try {
            Statement stm = null;
        ResultSet resultat = null ;
         stm = cnx.createStatement();
            resultat=stm.executeQuery(req);
           // while(rs.next()){
            resultat.next();
                p.setId(resultat.getInt("id"));
                p.setEntreprise_id(resultat.getInt("entreprise_id"));
                p.setNom_offre(resultat.getString("nom_offre"));
                p.setImage_name(resultat.getString("image_name"));
                 p.setType(resultat.getString("type"));
                p.setNote(resultat.getInt("note"));
                
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffres.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }
   public void AddOffre(Offres a) throws SQLException  {
  
        String req ="INSERT INTO offre (id,entreprise_id,nom_offre,image_name,type,note) VALUES (?,?,?,?,?,0)";
        
        try {
            PreparedStatement stm = cnx.prepareStatement(req);
           stm.setInt(1, a.getId());
             stm.setInt(2, a.getEntreprise_id());
             stm.setString(3,a.getNom_offre());
             stm.setString(4, a.getImage_name());
             stm.setString(5,a.getType());               
             stm.executeUpdate();
             System.out.println("Offre ajouté");
                     
        } catch (SQLException ex) {
            System.out.println("probleme");
            System.out.println(ex.getMessage());
        }       
    }
    public ObservableList<Offres> afficherOffre()  {
        ObservableList<Offres> resulta = FXCollections.observableArrayList();
        Statement stm = null;
        ResultSet resultat = null ;
        String query="select * from offre ";
        try { 
            stm = cnx.createStatement();
            resultat = stm.executeQuery(query);
           
            
            
            while(resultat.next()) {
                Offres a=new Offres();
                a.setId(resultat.getInt("id"));
                a.setEntreprise_id(resultat.getInt("entreprise_id"));
                a.setNom_offre(resultat.getString("nom_offre"));
                a.setImage_name(resultat.getString("image_name"));
                a.setType(resultat.getString("type"));
                resulta.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffres.class.getName()).log(Level.SEVERE, null, ex);
        }
    return resulta;
    
    }
     public void supprimerOffre(Offres a){
       String req="delete from offre where id=?";
       
        try {
            
            PreparedStatement stm;
            stm=cnx.prepareStatement(req);
            stm.setInt(1,a.getId() );
            int i=stm.executeUpdate();
            System.out.println(i+ " offre suprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   }
      public int ModifierOffre(int id,Offres a) throws SQLException {
        if(chercher(id)){
        
        pre=cnx.prepareStatement("UPDATE offre SET entreprise_id=?, nom_offre = ? , image_name = ? , type = ?  WHERE id = "+id+"");
    try{     
    pre.setInt(1, a.getEntreprise_id());
    pre.setString(2, a.getNom_offre());
    pre.setString(3, a.getImage_name());
    pre.setString(4, a.getType());   
   
    
    
    pre.executeUpdate();
    }
    catch (SQLException e){
      System.out.println(e.getMessage());  
    }
    return 1;}
        return 0;
    }
      public boolean chercher(int id) throws SQLException {
        String req="select * from offre";
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
      public boolean rate(Offres p) throws SQLException
          {
          String qry = "UPDATE offre SET  note = '"+p.getNote()+"' WHERE nom_offre = '"+p.getNom_offre()+"';";
        Statement st=cnx.createStatement();
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffres.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
          
          
          
          }
}
