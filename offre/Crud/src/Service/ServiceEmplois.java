/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Emplois;
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
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;

/**
 *
 * @author 21624
 */
public class ServiceEmplois  {
      Connection cnx;
    private List<Emplois> emplois;
    private PreparedStatement pre;
    private Statement ste;
    public ServiceEmplois() {
cnx =Maconnexion.getInstance().getConnection();
        }  
    
   public boolean AddEmploi(Emplois e) throws SQLException  {
  
        String req = "INSERT INTO emploi (id,offre_id,salaire,type_contrat) VALUES ('"+e.getIdemploi()+"','"+e.getOffre_id()+"','"+e.getSalaire()+"','"+e.getType_contrat()+"')";
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
    public ObservableList<Emplois> afficherEmplois(){
     Connection cnx =null;
        Statement st = null;
        ResultSet rs = null;
        ObservableList<Emplois> liste = FXCollections.observableArrayList();
        String requette = "SELECT * FROM emploi";

        try {
            cnx = Maconnexion.getInstance().getConnection();
            st = cnx.createStatement();
            rs = st.executeQuery(requette);
            Emplois CandidatureStages;
            while (rs.next()){
               CandidatureStages = new Emplois(rs.getInt("id"),rs.getInt("offre_id"),rs.getInt("salaire"),rs.getString("type_contrat"));
               liste.add(CandidatureStages);
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
     public void supprimerEmploi(Emplois e){
       String req="delete from emploi where id=?";
       
        try {
            
            PreparedStatement stm;
            stm=cnx.prepareStatement(req);
            stm.setInt(1,e.getIdemploi() );
            int i=stm.executeUpdate();
            System.out.println(i+ " emploi suprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   }
      public int ModifierEmploi(int id,Emplois e) throws SQLException {
        if(chercher(id)){
        
        pre=cnx.prepareStatement("UPDATE emploi SET offre_id=?, salaire = ? , type_contrat = ?   WHERE id = "+id+"");
    try{     
    pre.setInt(1, e.getOffre_id());
     pre.setInt(2, e.getSalaire());
    pre.setString(3, e.getType_contrat());
    
    
    pre.executeUpdate();
    }
    catch (SQLException m){
      System.out.println(m.getMessage());  
    }
    return 1;}
        return 0;
    }
      public boolean chercher(int id) throws SQLException {
        String req="select * from emploi";
        List<Integer> list = new ArrayList<>();
        
        try {
            ste=cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEmplois.class.getName()).log(Level.SEVERE, null, ex);
        }
        list.forEach(System.out::println);
        return list.contains(id);
    }
}
