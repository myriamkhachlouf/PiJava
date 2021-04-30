/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Bouhejba
 */
public class MyConnection1 {
        static final String jdbcDriver="com.mysql.jdbc.Driver";
    static final String url="jdbc:mysql://127.0.0.1:3306/PIJAVA";
    public String login="root";
    public String pwd="";
    public Connection cn;
    private static MyConnection instabne = null ;

    public MyConnection1() {
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
