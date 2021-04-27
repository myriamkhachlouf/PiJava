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
public class formation {
    
    private int id;
    private int reference;
    private int periode;
    private String objectif;
    private int dure;
    private int capacite;
    private int rating;
    
    public formation(){
        
    }

    public formation(int id, int reference, int periode, String objectif, int dure, int capacite, int rating) {
        this.id = id;
        this.reference = reference;
        this.periode = periode;
        this.objectif = objectif;
        this.dure = dure;
        this.capacite = capacite;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public int getPeriode() {
        return periode;
    }

    public void setPeriode(int periode) {
        this.periode = periode;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public int getDure() {
        return dure;
    }

    public void setDure(int dure) {
        this.dure = dure;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "formation{" + "id=" + id + ", reference=" + reference + ", periode=" + periode + ", objectif=" + objectif + ", dure=" + dure + ", capacite=" + capacite + ", rating=" + rating + '}';
    }
    
    
    
}
