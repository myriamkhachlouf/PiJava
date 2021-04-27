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
public class donnéeformation {
    
     private static formation selectedtype = new formation();
    private static donnéeformation  localdata;
    
    public static void setFormation(formation f){
    selectedtype = f;
    }
    
    public static formation getFormation(){
        
    return selectedtype;
    
    }
    
}
