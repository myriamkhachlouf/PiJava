/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author 21624
 */
public class Offres {
   int id,entreprise_id,note;

    
   String nom_offre,image_name,	type;

    public Offres(int id, int entreprise_id, String nom_offre, String image_name, String type,int note) {
        this.id = id;
        this.entreprise_id = entreprise_id;
        this.nom_offre = nom_offre;
        this.image_name = image_name;
        this.type = type;
        this.note=note;
    }

    public Offres() {
        
    }
public void setNote(int note) {
        this.note = note;
    }

    public int getNote() {
        return note;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEntreprise_id() {
        return entreprise_id;
    }

    public void setEntreprise_id(int entreprise_id) {
        this.entreprise_id = entreprise_id;
    }

    public String getNom_offre() {
        return nom_offre;
    }

    public void setNom_offre(String nom_offre) {
        this.nom_offre = nom_offre;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

     @Override
    public String toString() {
       return nom_offre;
    }

    
}
