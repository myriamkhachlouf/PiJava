/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.test.entities;

import java.sql.Date;
import java.time.LocalDate;


public class entretien {
    private int Id;
    private int Idc;
    private int Idr;
    private Date date;
    private String lieu;
    private int confirmation ;
    private int etat ;

/**
 *
 * @author fac
 */



    


    
    public entretien(int Idc, int Idr, Date date, String lieu, int confirmation, int etat) {
        this.Idc = Idc;
        this.Idr = Idr;
        this.date = date;
        this.lieu = lieu;
        this.confirmation = confirmation;
        this.etat = etat;
    }

    public entretien(int Id, int Idc, int Idr, Date date, String lieu, int confirmation, int etat) {
        this.Id = Id;
        this.Idc = Idc;
        this.Idr = Idr;
        this.date = date;
        this.lieu = lieu;
        this.confirmation = confirmation;
        this.etat = etat;
    }

    public entretien() {
    }

    public entretien(String lieu) {
        this.lieu = lieu;
    }

 
    


    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdc() {
        return Idc;
    }

    public void setIdc(int Idc) {
        this.Idc = Idc;
    }

    public int getIdr() {
        return Idr;
    }

    public void setIdr(int Idr) {
        this.Idr = Idr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(int confirmation) {
        this.confirmation = confirmation;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

 

    @Override
    public String toString() {
        return "entretien {" + "Id=" + Id + ", Idc" + Idc + ", Idr" + Idr +", date" + date +", lieu=" + lieu + ", confirmation" + confirmation +", etat=" + etat + '}';
    }
    
}
