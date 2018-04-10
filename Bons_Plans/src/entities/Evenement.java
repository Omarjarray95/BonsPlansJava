/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.sql.Date;

/**
 *
 * @author Ons Ben Othmen
 */
public class Evenement {
    private int id;
    private int id_etablissement;
    private Date date;
    private String description;
    private String nom;
    private int nbr_personnes;
    private int interesses;

    public Evenement(Date date, String description, String nom) {
        this.date = date;
        this.description = description;
        this.nom = nom;
    }

    public Evenement(int id,int id_etablissement, Date date, String description, String nom, int nbr_personnes, int interesses) {
        this.id_etablissement = id_etablissement;
        this.date = date;
        this.description = description;
        this.nom = nom;
        this.nbr_personnes = nbr_personnes;
        this.interesses = interesses;
        this.id=id;
    }

    public Evenement(int id_etablissement, Date date, String description, String nom, int nbr_personnes, int interesses) {
        this.id_etablissement = id_etablissement;
        this.date = date;
        this.description = description;
        this.nom = nom;
        this.nbr_personnes = nbr_personnes;
        this.interesses = interesses;
    }

    public Evenement(int id_etablissement, Date date, String description, String nom) {
        this.id_etablissement = id_etablissement;
        this.date = date;
        this.description = description;
        this.nom = nom;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_etablissement() {
        return id_etablissement;
    }

    public void setId_etablissement(int id_etablissement) {
        this.id_etablissement = id_etablissement;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbr_personnes() {
        return nbr_personnes;
    }

    public void setNbr_personnes(int nbr_personnes) {
        this.nbr_personnes = nbr_personnes;
    }

    public int getInteresses() {
        return interesses;
    }

    public void setInteresses(int interesses) {
        this.interesses = interesses;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", id_etablissement=" + id_etablissement + ", date=" + date + ", description=" + description + ", nom=" + nom + ", nbr_personnes=" + nbr_personnes + ", interesses=" + interesses + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        hash = 83 * hash + Objects.hashCode(this.id_etablissement);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evenement other = (Evenement) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.id_etablissement, other.id_etablissement)) {
            return false;
        }
        return true;
    }
    
}
