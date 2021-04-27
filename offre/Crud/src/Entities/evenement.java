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
public class evenement {
    
    private int id;
    private String nom;
    private String description;
    private String email;
    private String logo;
    private String date;

    public evenement(int id, String nom, String description, String email, String logo, String date) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.email = email;
        this.logo = logo;
        this.date = date;
    }
    
    public evenement(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "evenement{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", email=" + email + ", logo=" + logo + ", date=" + date + '}';
    }
    
    
    
    
    
}
