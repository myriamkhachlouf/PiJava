/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1.entities;

/**
 *
 * @author Mohamed
 */
import java.util.*;
 public final class UserSession {

    private static UserSession instance;
    private String userEmail;
    private String userRole;
    

    private UserSession(String userRole, String userEmail) {
        
        this.userRole = userRole;
        this.userEmail = userEmail;
    }

    public static UserSession getInstance(String userRole,String userEmail) {
        if(instance == null) {
            instance = new UserSession(userRole, userEmail);
        }
        return instance;
    }

    public UserSession() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    public String getuserRole() {
        return userRole;
    }

    public String getuserEmail() {
        return userEmail;
    }

    public void cleanUserSession() {
        userRole = "";// or null
        userEmail = "";
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userRole='" + userRole + '\'' +
                ", userEmail=" + userEmail +
                '}';
    }
}