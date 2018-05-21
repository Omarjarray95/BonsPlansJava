/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.sql.Time;
import java.sql.Date;

/**
 *
 * @author OrbitG
 */
public class Covoiturage {

    private int ID;
    private int ID_USER;
    private String depart;
    private String arrive;
    private float prix;
    private Date date;
    private Date date_sys;
    private String Heure;
    private int nbrPlaces;
    private String comfort;
    private String fumeur;
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID_USER() {
        return ID_USER;
    }

    public void setID_USER(int ID_USER) {
        this.ID_USER = ID_USER;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHeure() {
        return Heure;
    }

    public void setHeure(String Heure) {
        this.Heure = Heure;
    }

    public int getNbrPlaces() {
        return nbrPlaces;
    }

    public void setNbrPlaces(int nbrPlaces) {
        this.nbrPlaces = nbrPlaces;
    }

    public String getComfort() {
        return comfort;
    }

    public void setComfort(String comfort) {
        this.comfort = comfort;
    }

    public String getFumeur() {
        return fumeur;
    }

    public void setFumeur(String fumeur) {
        this.fumeur = fumeur;
    }

    public void setDate_sys(Date date_sys) {
        this.date_sys = date_sys;
    }

    public Date getDate_sys() {
        return date_sys;
    }

    @Override
    public String toString() {
        return "Covoiturage{" + "ID=" + ID + ", ID_USER=" + ID_USER + ", depart=" + depart + ", arrive=" + arrive + ", prix=" + prix + ", date=" + date + ", Heure=" + Heure + ", nbrPlaces=" + nbrPlaces + ", comfort=" + comfort + ", fumeur=" + fumeur + '}';
    }

    public Covoiturage() {
    }

    public Covoiturage(String depart, String arrive, float prix, Date date, String Heure, int nbrPlaces, String comfort, String fumeur, int ID_USER) {
        this.ID_USER = ID_USER;
        this.depart = depart;
        this.arrive = arrive;
        this.prix = prix;
        // this.date_sys=date_sys; 
        this.date = date;
        this.Heure = Heure;
        this.nbrPlaces = nbrPlaces;
        this.comfort = comfort;
        this.fumeur = fumeur;
    }

    public Covoiturage(int ID, String depart, String arrive, float prix, Date date, String Heure, int nbrPlaces, String comfort, String fumeur) {
        this.ID = ID;
        //this.ID_USER = ID_USER;
        this.depart = depart;
        this.arrive = arrive;
        this.prix = prix;
        this.date = date;
        //this.date_sys = date_sys;
        this.Heure = Heure;
        this.nbrPlaces = nbrPlaces;
        this.comfort = comfort;
        this.fumeur = fumeur;
    }

    public Covoiturage(String depart, String arrive, float prix, Date date, String Heure, int nbrPlaces, String comfort, String fumeur) {
        this.depart = depart;
        this.arrive = arrive;
        this.prix = prix;
        this.date = date;
        this.Heure = Heure;
        this.nbrPlaces = nbrPlaces;
        this.comfort = comfort;
        this.fumeur = fumeur;
    }

    public Covoiturage(int ID, String depart, String arrive, float prix, Date date, Date date_sys, String Heure, int nbrPlaces, String comfort, String fumeur, int ID_USER) {
        this.ID = ID;
        this.ID_USER = ID_USER;
        this.depart = depart;
        this.arrive = arrive;
        this.prix = prix;
        this.date = date;
        this.date_sys = date_sys;
        this.Heure = Heure;
        this.nbrPlaces = nbrPlaces;
        this.comfort = comfort;
        this.fumeur = fumeur;
    }

}
