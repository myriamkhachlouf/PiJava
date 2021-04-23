/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import Entities.Offres;

/**
 *
 * @author 21624
 */
public class Emplois {
   int idemploi,offre_id,salaire;
   String type_contrat,nom_offre;
   
    public Emplois() {
    }

    public Emplois(int idemploi, int offre_id, int salaire, String type_contrat) {
        this.idemploi = idemploi;
        this.offre_id = offre_id;
        this.salaire = salaire;
        this.type_contrat = type_contrat;
    }

    public int getIdemploi() {
        return idemploi;
    }

    public void setIdemploi(int idemploi) {
        this.idemploi = idemploi;
    }

  

    public int getOffre_id() {
        return offre_id;
    }

    public void setOffre_id(int offre_id) {
        this.offre_id = offre_id;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    public String getType_contrat() {
        return type_contrat;
    }

    public void setType_contrat(String type_contrat) {
        this.type_contrat = type_contrat;
    }

   
    

   
    
}
