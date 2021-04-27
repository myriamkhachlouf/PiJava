/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Bouhejba
 */
public class MyConnection {
    public String url="jdbc:mysql://localhost:3306/PIJAVA";
    public String login="root";
    public String pwd="";
    public Connection cn;
    private static MyConnection instabne = null ;

    public MyConnection() {
        try {
            
          cn = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println("Erreur de connexion");
            System.out.println(ex.getMessage());
        }
       
        
    }
    
     public static MyConnection getinstance()
    {if(instabne == null)
    instabne = new MyConnection();  
    return instabne; 
    }
    
     public Connection getConnexion()
    {return cn ; }
    
    
}
