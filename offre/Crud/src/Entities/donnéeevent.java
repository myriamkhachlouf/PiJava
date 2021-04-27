/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import entities.evenement;

/**
 *
 * @author Admin
 */
public class donnéeevent {
    
    private static evenement selectedtype = new evenement();
    private static donnéeevent  localdata;
    
    public static void setEvent(evenement e){
    selectedtype = e;
    }
    
    public static evenement getEvent(){
        
    return selectedtype;
    
    }
    
    
}
