/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1.services;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication1.entities.UserSession;
import javaapplication1.interfaces.IService;
import javaapplication1.utils.MaConnexion;
import javaapplication1.entities.users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javax.swing.JOptionPane;

/**
 *
 * @author Mohamed
 */
public class UsersService implements IService<users>{
   
    //
    
    
    @Override
    
    public ArrayList<users> getAll() {
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection cnx = MaConnexion.getInstance().getCnx();
        ArrayList<users> users=new ArrayList<>();
        String req = "SELECT id,Nom,Email,Telephone,Adresse,Domaine,Roles,reset_token FROM Users";
        
        try {
            Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
            while (rst.next()){
                users u= new users();
                u.setId(rst.getInt("id"));
                u.setNom(rst.getString("Nom"));
                u.setEmail(rst.getString("Email"));
                u.setTelephone(rst.getString("Telephone"));
                u.setAdresse(rst.getString("Adresse"));
                u.setDomaine(rst.getString("Domaine"));
                u.setRoles(rst.getString("Roles"));
                u.setReset_token(rst.getString("reset_token"));
                users.add(u);
            }
        }
        catch(SQLException ex){
            Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE,null,ex);
        }
        return (users);    
    }
    
    /**
     *
     * @return
     */
    public ArrayList<users> getOneUserById(int ID) {
        Connection cnx = MaConnexion.getInstance().getCnx();
        ResultSet rs = null;
        PreparedStatement pst = null;
    
        ArrayList<users> users=new ArrayList<>();
        String req = "SELECT id,Nom,Email,Telephone,Adresse,Domaine,Roles,reset_token FROM Users where Users.id=ID";
        try {
            Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
            while (rst.next()){
                users u= new users();
                u.setId(rst.getInt("id"));
                u.setNom(rst.getString("Nom"));
                u.setEmail(rst.getString("Email"));
                u.setTelephone(rst.getString("Telephone"));
                u.setAdresse(rst.getString("Adresse"));
                u.setDomaine(rst.getString("Domaine"));
                u.setRoles(rst.getString("Roles"));
                u.setReset_token(rst.getString("reset_token"));

                users.add(u);
            }
        }
        catch(SQLException ex){
            Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE,null,ex);
        }
        return (users);    
    }
    
    
    @Override
    public void add(users entity) 
    {
        Connection cnx = MaConnexion.getInstance().getCnx();
        try{
            String req="INSERT INTO Users(nom,email,password,telephone,adresse,domaine,roles,reset_token)" + "VALUES(?,?,?,?,?,?,?,?)";                                                                                               
            PreparedStatement pst = cnx.prepareStatement(req);                                                                                                   
            pst.setString(1,entity.getNom());                                                                                                
            pst.setString(2,entity.getEmail()); 
            pst.setString(3,entity.getPassword());
            pst.setString(4,entity.getTelephone());
            pst.setString(5,entity.getAdresse());
            pst.setString(6,entity.getDomaine());
            pst.setString(7,entity.getRoles());
            pst.setString(8,entity.getReset_token());
            pst.executeUpdate();  
            
            }
        catch(SQLException ex)
            {
                 Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE,null,ex);
            }    
    }
    
    public int Login(String mail, String Password,String Roles) throws Exception{
        
        String sql="Select * from users where email=? and password=? and roles=?"; 
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection cnx = MaConnexion.getInstance().getCnx();
        try
        {
            pst=cnx.prepareStatement(sql);
            pst.setString(1, mail);
            pst.setString(2, Password);
            pst.setString(3, Roles);
            rs=pst.executeQuery();
            
            if(rs.next())
            {
             JOptionPane.showMessageDialog(null, "Mail and password are correct");
             
             return rs.getInt("id");
             
            }   
            else
            {
             JOptionPane.showMessageDialog(null, "Invalid Mail or password ");
             return 0;
            }
        } 
        catch(Exception e) 
        {
        JOptionPane.showMessageDialog(null,e);
        }
         return 0;
    }
    
    public users getUserByEmail(String email){
        users u=new users();
        Connection cnx = MaConnexion.getInstance().getCnx();
        String req = "SELECT id,Nom,Email,Telephone,Adresse,Domaine,Roles,reset_token FROM users WHERE Email = ?";
        ResultSet rst = null;
        PreparedStatement pst = null;
        try {
            pst=cnx.prepareStatement(req);
            pst.setString(1, email);
            rst = pst.executeQuery();
            if(rst.next()) {
                u.setId(rst.getInt("id"));
                u.setNom(rst.getString("nom"));
                u.setEmail(rst.getString("email"));
                u.setAdresse(rst.getString("adresse"));
                u.setTelephone(rst.getString("telephone"));
                u.setDomaine(rst.getString("domaine"));
                u.setReset_token(rst.getString("reset_token"));
                return u;
            }
        }
        catch(SQLException ex){
            Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE,null,ex);
        }
        return null;
    }
    
    public users getUserByID(int id){
    
        users u=new users();
        Connection cnx = MaConnexion.getInstance().getCnx();
        
        String req = "SELECT id,Nom,Email,Telephone,Adresse,Domaine,Roles,reset_token FROM users WHERE id = ?";
        
        ResultSet rst = null;
        PreparedStatement pst = null;
   
        try {
            
            pst=cnx.prepareStatement(req);
            pst.setInt(1, id);
            //System.out.println("javaapplication1.services.UsersService.getUserByID()" + pst.g);
            rst = pst.executeQuery();
            
            if(rst.next()) {
            
                u.setId(rst.getInt("id"));
                u.setNom(rst.getString("nom"));
                u.setEmail(rst.getString("email"));
                u.setAdresse(rst.getString("adresse"));
                u.setTelephone(rst.getString("telephone"));
                u.setDomaine(rst.getString("domaine"));
                u.setReset_token(rst.getString("reset_token"));    
                return u;
            }
        }
        catch(SQLException ex){
            Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE,null,ex);
        }
        return (u);
        
    }
    
  /*  public users getUser(String Email){
        PreparedStatement pst = null;
   
        users u=new users();
        String req = "SELECT * FROM Users WHERE Email=?";
        try {
            pst=cnx.prepareStatement(req);
            pst.setString(1, Email);
            ResultSet rst = pst.executeQuery(req);
            u.setId(rst.getInt("id"));
            u.setNom(rst.getString("Nom"));
            u.setEmail(rst.getString("Email"));
            return u;
        }
        catch(SQLException ex){
            Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE,null,ex);
        }
        return (u);
        
    }
*/
    
    public int Delete(int id)
    {
        PreparedStatement pst = null;
        Connection cnx = MaConnexion.getInstance().getCnx();
        String sql="delete from users where id=?";
        try{
            pst= cnx.prepareStatement(sql);
            pst.setInt(1,id);
            pst.executeUpdate();
            return 1;
        }catch (Exception e) {
            Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE,null,e);
            return 0;
        }
    }
    
    public int Save(users entity)
    {
        Connection cnx = MaConnexion.getInstance().getCnx();
        
        PreparedStatement pst;
        
        try{
            String sql="UPDATE users SET nom=?,email=?,telephone=?,adresse=?,domaine=?,reset_token=? WHERE id=?";
            pst= cnx.prepareStatement(sql);
            
            pst.setString(1,entity.getNom());
            pst.setString(2,entity.getEmail());
            pst.setString(3,entity.getTelephone());
            pst.setString(4,entity.getAdresse());
            pst.setString(5,entity.getDomaine()); 
            pst.setString(6,entity.getReset_token()); 
            pst.setInt(7,entity.getId()); 
            pst.executeUpdate();
            return 1;
        }catch (Exception e) {
            Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE,null,e);
        }
            return 0;
        }

    public int SetPassword(String email, String password)
    {
        Connection cnx = MaConnexion.getInstance().getCnx();
        
        PreparedStatement pst;
        
        try{
            String sql="UPDATE users SET password=? WHERE email=?";
            pst= cnx.prepareStatement(sql);
            
            pst.setString(1,password);
            pst.setString(2,email);
            
            pst.executeUpdate();
            return 1;
        }catch (Exception e) {
            Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE,null,e);
        }
            return 0;
        }

    @Override
    public ArrayList<users> getUserById(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
