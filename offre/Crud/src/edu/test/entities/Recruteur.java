/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.test.entities;

/**
 *
 * @author Asus
 */
public class Recruteur {
    private int Id;
    private String nom;
    private String prenom;
    private String domaine ;

    public Recruteur(int Id, String nom, String prenom, String domaine) {
        this.Id = Id;
        this.nom = nom;
        this.prenom = prenom;
        this.domaine = domaine;
    }
 public Recruteur(String nom, String prenom, String domaine) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.domaine = domaine;
    }

    public Recruteur() {
    }

    public int getId() {
        return Id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setId(int id) {
        this.Id = Id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    @Override
    public String toString() {
        return "Recruteur{" + "Id=" + Id + '}';
    }
      
    
  
    
}
