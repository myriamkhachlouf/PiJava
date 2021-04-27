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
public class grille {
     private int Idg;

    
    private int ide;
    private String commentaire;
    private String etat ;

    public grille() {
    }
    public grille(String ide, String commentaire, String etat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "grille{" + "Idg=" + Idg + ", ide=" + ide + ", commentaire=" + commentaire + ", etat=" + etat + '}';
    }

    public grille(int Idg, int ide, String commentaire, String etat) {
        this.Idg = Idg;
        this.ide = ide;
        this.commentaire = commentaire;
        this.etat = etat;
    }

    public grille(int ide, String commentaire, String etat) {
        this.ide = ide;
        this.commentaire = commentaire;
        this.etat = etat;
    }

    public int getIdg() {
        return Idg;
    }

    public int getIde() {
        return ide;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public String getEtat() {
        return etat;
    }

    public void setIdg(int Idg) {
        this.Idg = Idg;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

   

}
