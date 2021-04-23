/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.scene.control.DatePicker;

/**
 *
 * @author azizs
 */
public class Stage {
    int id,offre_id;
   String type_du_stage,nom_encadrant,date_debut,date_fin;
    
   public Stage(int id, int offre_id, String date_debut, String date_fin, String type_du_stage, String nom_encadrant) {
        this.id = id;
        this.offre_id = offre_id;
        this.type_du_stage = type_du_stage;
        this.nom_encadrant = nom_encadrant;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Stage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOffre_id(int offre_id) {
        this.offre_id = offre_id;
    }

    public void setType_du_stage(String type_du_stage) {
        this.type_du_stage = type_du_stage;
    }

    public void setNom_encadrant(String nom_encadrant) {
        this.nom_encadrant = nom_encadrant;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public int getId() {
        return id;
    }

    public int getOffre_id() {
        return offre_id;
    }

    public String getType_du_stage() {
        return type_du_stage;
    }

    public String getNom_encadrant() {
        return nom_encadrant;
    }

    public String getDate_debut() {
        return date_debut;
    }

   

    public String getDate_fin() {
        return date_fin;
    }

    @Override
    public String toString() {
        return "Stage{" + "id=" + id + ", offre_id=" + offre_id + ", type_de_stage=" + type_du_stage + ", nom_encadrant=" + nom_encadrant + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }
    
}
