package utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohamed
 */
public class MaConnexion {
    static final String jdbcDriver="com.mysql.jdbc.Driver";
    static final String url="jdbc:mysql://127.0.0.1:3306/finale";
    static final  String userName="root";
    static final String password="";
    private static Connection cnx = null;
    private static MaConnexion instance;
    public Integer connectedUserID = 0; 
    public static String useremail=null; 

    
    private MaConnexion () {
        try {
            Class.forName(jdbcDriver);
            cnx=DriverManager.getConnection(url, userName, password);
            System.out.println("connexion etablie");
      } 
        catch(ClassNotFoundException classNotFound)
        {
                        System.out.println("impossible de charger le pilote");

                    
        }
        catch (SQLException ex) {
            Logger.getLogger(MaConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static MaConnexion getInstance() {
        if (instance==null)
            instance = new MaConnexion();
        return (instance);
    }
    
    public Connection getConnection(){
        return cnx;
    }
    
}
