/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author azizs
 */
public class Candidature {
     int id,candidat_id,offre_id;
     String Date,pdf;

    

    public Candidature(int id, int candidat_id, int offre_id , String Date, String pdf) {
        this.id = id;
        this.candidat_id = candidat_id;
        this.offre_id = offre_id;
        this.Date = Date;
        this.pdf = pdf;
    }

    public Candidature() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public int getCandidat_id() {
        return candidat_id;
    }
    
     public int getOffre_id() {
        return offre_id;
    }

    public String getDate() {
        return Date;
    }

    public String getPdf() {
        return pdf;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setOffre_id(int offre_id) {
        this.offre_id = offre_id;
    }
    public void setCandidat_id(int candidat_id) {
        this.candidat_id = candidat_id;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
    
    
}
