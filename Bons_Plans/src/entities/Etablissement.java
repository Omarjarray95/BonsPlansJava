/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Ons Ben Othmen
 */
public class Etablissement {
    private int id;
    private String nom;
    private String type;
    private int partenaire;

    public Etablissement(int id, String nom, String type, int partenaire, int responsable) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.partenaire = partenaire;
        this.responsable = responsable;
    }

    public int getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(int partenaire) {
        this.partenaire = partenaire;
    }
    private int responsable;

    public Etablissement(String nom, String type, int responsable) {
        this.nom = nom;
        this.type = type;
        this.responsable = responsable;
    }

    public Etablissement(int id, String nom, String type, int responsable) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.responsable = responsable;
    }

    public int getResponsable() {
        return responsable;
    }

    public void setResponsable(int responsable) {
        this.responsable = responsable;
    }

    public Etablissement(int id, String nom, String type) {
        this.id = id;
        this.nom = nom;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public Etablissement(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Etablissement(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
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
        final Etablissement other = (Etablissement) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Etablissement{" + "id=" + id + '}';
    }
    
    
}
