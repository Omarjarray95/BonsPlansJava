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
import java.sql.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Ons Ben Othmen
 */
public class Offre {
    private int id;
    private int id_etablissement;
    private Date date_debut;
    private Date date_fin;
    private String description;
    private String offre;
   

    public Offre(Date date_debut, Date date_fin, String description, String offre) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
        this.offre = offre;
    }

    public Offre() {
    }

    public Offre(int id,int id_etablissement, Date date_debut, Date date_fin, String description, String offre) {
        this.id_etablissement = id_etablissement;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
        this.offre = offre;
        this.id=id;
    }

    public Offre(int id_etablissement, Date date_debut, Date date_fin, String description, String offre) {
        this.id_etablissement = id_etablissement;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
        this.offre = offre;
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



    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOffre() {
        return offre;
    }

    public void setOffre(String offre) {
        this.offre = offre;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.id_etablissement);
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
        final Offre other = (Offre) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.id_etablissement, other.id_etablissement)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", id_etablissement=" + id_etablissement + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", description=" + description + ", offre=" + offre + '}';
    }
}
