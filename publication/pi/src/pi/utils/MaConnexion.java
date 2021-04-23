/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Mahmoud
 */
public class MaConnexion {
    static final String jdbcDriver = "com.mysql.jdbc.Driver";
     private String url="jdbc:mysql://localhost:3306/pi";
    private String userName="root";
    private String password="";
    private Connection cnx;
    private static MaConnexion instance;
    private MaConnexion(){
        try{
            Class.forName(jdbcDriver );
            cnx=DriverManager.getConnection(url, userName, password);
            System.out.println("connexion établie");
        }
        catch ( ClassNotFoundException classNotFound ) {
System.out.println("Impossible de charger le pilote");
classNotFound.printStackTrace();
//System.exit( 1 );
}
        catch(SQLException ex){
            Logger.getLogger(MaConnexion.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public static MaConnexion getInstance(){
        if (instance == null)
            instance = new MaConnexion();
        return (instance);
    }
    public Connection getCnx(){
        return cnx;
    }
}
