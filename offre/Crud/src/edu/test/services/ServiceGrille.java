/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.test.services;
import edu.test.entities.grille;
import edu.test.entities.Recruteur;
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
/**
 *
 * @author Asus
 */
public class ServiceGrille {
     
    private final Connection con;
    private Statement ste;
    private PreparedStatement pre;
    

    public ServiceGrille() {
        con = MaConnexion.getInstance().getConnection();
    }
      public List<grille> readAll() {

        List<grille> arr = new ArrayList<>();
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("select * from grille_evaluation ");
            while (rs.next()) {
                //int id=rs.getInt(1);
                int ide = rs.getInt("entretien_id");
                String commentaire = rs.getString("commentaire");
                String etat = rs.getString("admission");

                
                

                grille c = new grille(ide,commentaire,etat);
                arr.add(c);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRecruteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
      public void ajouterEvent(grille c)   {
       

        try {
            ste = con.createStatement();
            String requeteInsert = "INSERT INTO grille_evaluation (entretien_id,commentaire,admission) VALUES (?,?,?)";

            PreparedStatement pst = con.prepareStatement(requeteInsert);
            pst.setInt(1, c.getIde());
            pst.setString(2, c.getCommentaire());
            pst.setString(3, c.getEtat());
            
            pst.executeUpdate(); 
        } catch (SQLException ex) {
ex.printStackTrace();
        }
           

    }
 public void ajouterpp(grille c)   {
       

        try {
            ste = con.createStatement();
            String requeteInsert = "INSERT INTO grille_evaluation (entretien_id,commentaire,admission) VALUES (?,?,?)";

            PreparedStatement pst = con.prepareStatement(requeteInsert);
           pst.setInt(1, c.getIde());
            pst.setString(2, c.getCommentaire());
            pst.setString(3, c.getEtat());
          
            
            pst.executeUpdate(); 
        } catch (SQLException ex) {
ex.printStackTrace();
        }
           

    }
  public boolean supprimer(grille r) throws SQLException {

        String reqeute = "delete from grille_evaluation  where (Id = ?) ;";
        try {
            PreparedStatement pst = con.prepareStatement(reqeute);
            pst.setInt(1, r.getIde());
            // pst.setString(2, m.setDescription(reqeute));
            if (pst.executeUpdate() != 0) {
                System.out.println("supp©");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return false;
//To change body of generated methods, choose Tools | Templates.
    }
  
   public int updateRecruteur(int id, grille t) throws SQLException { 
       if(chercher(id)){
        
        pre=con.prepareStatement("UPDATE grille_evaluation SET entretien_id = ? , commentaire = ? , admission = ?  WHERE Id = "+id+"");
    try{     
             pre.setInt(1, t.getIde());
             pre.setString(2, t.getCommentaire());
             pre.setString(3, t.getEtat());
             
    
    
    pre.executeUpdate();
    }
    catch (SQLException m){
      System.out.println(m.getMessage());  
    }
    return 1;}
        return 0;  
       
    }


public boolean chercher(int id) throws SQLException {
        String req="select * from grille_evaluation";
        List<Integer> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRecruteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        list.forEach(System.out::println);
        return list.contains(id);
    }

     
}
