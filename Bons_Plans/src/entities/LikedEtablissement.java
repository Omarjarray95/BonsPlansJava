/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author Ons Ben Othmen
 */
public class LikedEtablissement {
    private int id;
    private int etab;
    private int user;

    public LikedEtablissement(int etab, int user) {
        this.etab = etab;
        this.user = user;
    }

    public LikedEtablissement(int id, int etab, int user) {
        this.id = id;
        this.etab = etab;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEtab() {
        return etab;
    }

    public void setEtab(int etab) {
        this.etab = etab;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.etab);
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
        final LikedEtablissement other = (LikedEtablissement) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.etab, other.etab)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LikedEtablissement{" + "id=" + id + ", etab=" + etab + ", user=" + user + '}';
    }

    public LikedEtablissement() {
    }
    
}
