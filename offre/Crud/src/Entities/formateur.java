/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Bouhejba
 */
public class formateur {
    
    private int id;
    private String nom;
    private String prenom;
    private String statut;
    private String typecontrat;
    private String email;
    private int password;

    public formateur(int id, String nom, String prenom, String statut, String typecontrat, String email, int password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.statut = statut;
        this.typecontrat = typecontrat;
        this.email = email;
        this.password = password;
    }
    
    
    public formateur(){
        
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getTypecontrat() {
        return typecontrat;
    }

    public void setTypecontrat(String typecontrat) {
        this.typecontrat = typecontrat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "formateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", statut=" + statut + ", typecontrat=" + typecontrat + ", email=" + email + ", password=" + password + '}';
    }
    
    
    
    
    
}
