/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.test.entities;

/**
 *
 * @author fac
 */
public class Donnee {
    

private static Recruteur selectedtype = new Recruteur();
    private static Donnee  localdata;
    
    public static void setRecruteur(Recruteur r){
    selectedtype = r;
    }
    
    public static Recruteur getRecruteur(){
        
    return selectedtype;
    
    }
}