/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import edu.test.entities.grille;
import edu.test.entities.Recruteur;
import edu.test.entities.entretien;
import edu.test.utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;

/**
 *
 * @author fac
 */
public class ServiceEntretien {
     
      private Connection con;
    private Statement ste;
    private PreparedStatement stm;

    public ServiceEntretien() {
        con = DataBase.getInstance().getConnection();
    }
    
        public List<entretien> readAll() {

        List<entretien> arr = new ArrayList<>();
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("select * from entretien ");
            while (rs.next()) {
                //int id=rs.getInt(1);
                int Idc = rs.getInt("Idc");
                int Idr = rs.getInt("Idr");
                Date date =rs.getDate("date");
                String lieu = rs.getString("lieu");
                int confirmation = rs.getInt("confirmation");
                int etat = rs.getInt("etat");

                entretien c = new entretien( Idc, Idr, date, lieu, confirmation, etat);
                arr.add(c);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEntretien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
        
        
    }
      public void ajouterEvent(entretien c)   {
       

        try {
            ste = con.createStatement();
            String requeteInsert = "INSERT INTO entretien ( Idc, Idr, date, lieu, confirmation, etat) VALUES (?,?,?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(requeteInsert);
            pst.setInt(1, c.getIdc());
            pst.setInt(2, c.getIdr());
            pst.setDate(3,  c.getDate());
            pst.setString(4, c.getLieu());
            pst.setInt(5, c.getConfirmation());
            pst.setInt(6, c.getEtat());
            
            pst.executeUpdate(); 
        } catch (SQLException ex) {
ex.printStackTrace();
       
       
       
       
        }
           

    }
 public void ajouterpp(entretien c)   {
   /*    

        try {
            ste = con.createStatement();
            String requeteInsert = "INSERT INTO entretien ( Idc, Idr, date, lieu, confirmation, etat) VALUES (?,?,?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(requeteInsert);
            pst.setInt(1, c.getIdc());
            pst.setInt(2, c.getIdr());
            pst.setDate(3,  c.getDate());
            pst.setString(4, c.getLieu());
            pst.setInt(5, c.getConfirmation());
            pst.setInt(6, c.getEtat());               
          
            
            pst.executeUpdate(); 
        } catch (SQLException ex) {
ex.printStackTrace();
        }
           */

           String req ="INSERT INTO entretien (Idc, Idr, date, lieu, confirmation, etat) VALUES (?,?,?,?,?,?)";
        
        try {
            
            PreparedStatement stm = con.prepareStatement(req);
             stm.setInt(1, c.getIdc());
            stm.setInt(2, c.getIdr());
            stm.setDate(3,  c.getDate());
            stm.setString(4, c.getLieu());
            stm.setInt(5, c.getConfirmation());
            stm.setInt(6, c.getEtat());
             
             stm.executeUpdate();
             System.out.println("Entretien ajouté");
                     
        } catch (SQLException ex) {
            System.out.println("probleme");
            System.out.println(ex.getMessage());
        }
   
    }
 
 
 
 
 
  public boolean supprimer(entretien r) throws SQLException {

        String reqeute = "delete from entretien  where (Id = ?) ;";
        try {
            PreparedStatement pst = con.prepareStatement(reqeute);
            pst.setInt(1, r.getId());
            // pst.setString(2, m.setDescription(reqeute));
            if (pst.executeUpdate() != 0) {
                System.out.println("supp©");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return false;
    }
  
  
  public int updateEntretien(int id, entretien t) throws SQLException { 
       if(chercher(id)){
           
        
        stm=con.prepareStatement("UPDATE entretien SET Idc = ? , Idr = ? , date = ? , lieu = ? , confirmation = ? , etat = ? WHERE Id = "+id+"");
    try{     
             stm.setInt(1, t.getIdc());
             stm.setInt(2, t.getIdr());
             stm.setDate(3, t.getDate());
             stm.setString(4, t.getLieu());
             stm.setInt(5, t.getConfirmation());
             stm.setInt(6, t.getEtat());

             
    
    
    stm.executeUpdate();
    }
    catch (SQLException m){
      System.out.println(m.getMessage());  
    }
    return 1;}
        return 0;  
       
    }


public boolean chercher(int id) throws SQLException {
        String req="select * from entretien";
        List<Integer> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEntretien.class.getName()).log(Level.SEVERE, null, ex);
        }
        list.forEach(System.out::println);
        return list.contains(id);
    }
     
}
