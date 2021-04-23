/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entities.Candidature;
import Entities.Stage;
import Utils.Maconnexion;
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
 * @author azizs
 */
public class ServiceCandidature {
          Connection cnx;
    private List<Candidature> candidatures;
    private PreparedStatement pre;
    private Statement ste;
    public ServiceCandidature() {
cnx =Maconnexion.getInstance().getConnection();
        }  
    
   public boolean AddCandidature(Candidature e) throws SQLException  {
  
        
        String req = "INSERT INTO candidature (id,candidat_id,offre_id,date_candidature,pdf) VALUES ('"+e.getId()+"','"+e.getCandidat_id()+"','"+e.getOffre_id()+"','"+e.getDate()+"','"+e.getPdf()+"')";
         Connection cnx =null;
        Statement st = null;
         try {
            cnx = Maconnexion.getInstance().getConnection();
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
     public void supprimerCandidature(Candidature a){
       String req="delete from Candidature where id=?";
       
        try {
            
            PreparedStatement stm;
            stm=cnx.prepareStatement(req);
            stm.setInt(1,a.getId() );
            int i=stm.executeUpdate();
            System.out.println(i+ " Candidature suprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   }
      public int ModifierCandidature(int id,Candidature a) throws SQLException {
        if(chercher(id)){
        
        pre=cnx.prepareStatement("UPDATE Candidature SET offre_id=?, candidat_id = ? , date_candidature = ? , pdf = ?  WHERE id = "+id+"");
    try{     
    pre.setInt(1, a.getOffre_id());
    pre.setInt(2, a.getCandidat_id());
    pre.setString(3, a.getDate());
    pre.setString(4, a.getPdf());
   
    
    
    pre.executeUpdate();
    }
    catch (SQLException e){
      System.out.println(e.getMessage());  
    }
    return 1;}
        return 0;
    }
      public boolean chercher(int id) throws SQLException {
        String req="select * from Candidature";
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
      
       public ObservableList<Candidature> afficherCandidature() throws SQLException {
     Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Candidature> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM Candidature";

        try {
            cnx = Maconnexion.getInstance().getConnection();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            Candidature candidature;
            while (rs.next()){
               candidature = new Candidature(rs.getInt("id"),rs.getInt("candidat_id"),rs.getInt("offre_id"),rs.getString("date_candidature"),rs.getString("pdf"));
               liste.add(candidature);
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

    
}
