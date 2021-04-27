/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.test.services;




import edu.test.entities.Recruteur;
import edu.test.entities.Recruteur;
import edu.test.utils.DataBase;
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
public class ServiceRecruteur {

  
    
    private Connection con;
    private Statement ste;
    private PreparedStatement pre;

    public ServiceRecruteur() {
        con = DataBase.getInstance().getConnection();
    }

    public List<Recruteur> readAll() {

        List<Recruteur> arr = new ArrayList<>();
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("select * from Recruteur ");
            while (rs.next()) {
                //int id=rs.getInt(1);
                int Id = rs.getInt("Id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String domaine = rs.getString("domaine");

                
                

                Recruteur c = new Recruteur(Id,nom,prenom,domaine);
                arr.add(c);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRecruteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

   /* public void ajoutRecruteur(Recruteur c) {
        try {

            ste = con.createStatement();
            String requeteInsert = "INSERT INTO Recruteur (nomcat) VALUES (?)";

            PreparedStatement pst = con.prepareStatement(requeteInsert);
            pst.setString(1, c.getNomcat());
            

 if (pst.executeUpdate() != 0) {
                System.out.println("Recruteur deleted");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRecruteur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    */

public Recruteur findbyid(int Id) {
        Recruteur c = new Recruteur();
        try {

            PreparedStatement pre = con.prepareStatement("Select * from Recruteur  WHERE Id=? ");
            pre.setInt(1, Id);
            ResultSet rs = pre.executeQuery();//hedhi ki taffichi
            while (rs.next()) {
               
                String nom = rs.getString("nom");
            
            
            
          c.setNom(nom);
             
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return c;
    }


 
public void ajouterEvent(Recruteur c)   {
       

        try {
            ste = con.createStatement();
            String requeteInsert = "INSERT INTO Recruteur (Id,nom,prenom,domaine) VALUES (?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(requeteInsert);
            pst.setInt(1, c.getId());
            pst.setString(2, c.getNom());
            pst.setString(3, c.getPrenom());
            pst.setString(4, c.getDomaine());
            
            pst.executeUpdate(); 
        } catch (SQLException ex) {
ex.printStackTrace();
        }
           

    }
 public void ajouterpp(Recruteur r)   {
       

        try {
            ste = con.createStatement();
            String requeteInsert = "INSERT INTO Recruteur (nom,prenom,domaine) VALUES (?,?,?)";

            PreparedStatement pst = con.prepareStatement(requeteInsert);
            pst.setString(1, r.getNom());
            pst.setString(2, r.getPrenom());
            pst.setString(3, r.getDomaine());
            
          
            
            pst.executeUpdate(); 
        } catch (SQLException ex) {
ex.printStackTrace();
        }
           

    }
 public boolean supprimer(Recruteur r) throws SQLException {

        String reqeute = "delete from Recruteur  where (nom = ?) ;";
        try {
            PreparedStatement pst = con.prepareStatement(reqeute);
            pst.setString(1, r.getNom());
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
 
 
 

     
    public int updateRecruteur(int id, Recruteur t) throws SQLException { 
       if(chercher(id)){
        
        pre=con.prepareStatement("UPDATE recruteur SET nom = ? , prenom = ? , domaine = ?  WHERE Id = "+id+"");
    try{     
             pre.setString(1, t.getNom());
             pre.setString(2, t.getPrenom());
             pre.setString(3, t.getDomaine());
             
    
    
    pre.executeUpdate();
    }
    catch (SQLException m){
      System.out.println(m.getMessage());  
    }
    return 1;}
        return 0;  
       
    }


public boolean chercher(int id) throws SQLException {
        String req="select * from recruteur";
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
//        try {
//            ste = con.createStatement();
//            String requete = "UPDATE Recruteur SET  nom= ?,prenom= ?, domaine=?  where Id=?;";
//
//            PreparedStatement pst = con.prepareStatement(requete);
//            pst.setString(1, newc.getNom());
//            pst.setString(2, newc.getPrenom());
//            pst.setString(3, newc.getDomaine()); 
//            pst.setInt(4, oldc.getId());
//             
//            
//            pst.executeUpdate();
//             
//             } catch (SQLException ex) {
//                 System.err.println(ex.getMessage());
//             }
//    }}
            
          

//            pst.setInt(7, oldc.getId());
//             
//            
//            pst.executeUpdate();
//             
//             } catch (SQLException ex) {
//                 System.err.println(ex.getMessage());
//             }
//            PreparedStatement pst = con.prepareStatement(requete);
//
//          pst.setString(1, p.getNom());
//        
//            pst.setString(2, p.getPrenom());
//          
//            pst.setString(3, p.getDomaine());
//                       pst.setInt(4, p.getId());
//
//
//            if (pst.executeUpdate() != 0) {
//                System.out.println("produit modifie");
//            } else {
//                System.out.println("non");
//            }
//            return true;
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//
//        }
//        return false;
//    }



       



