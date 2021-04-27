/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Admin
 */
public class donnéeformateur {
    
    private static formateur selectedtype = new formateur();
    private static donnéeformateur  localdata;
    
    public static void setFormaeur(formateur f){
    selectedtype = f;
    }
    
    public static formateur getFormateur(){
        
    return selectedtype;
    
    }
    
}
