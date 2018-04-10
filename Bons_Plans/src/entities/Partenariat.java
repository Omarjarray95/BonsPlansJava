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
public class Partenariat {
     private int id;
    private int etab;
    private int user;
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Partenariat(int id, int etab, int user, String description) {
        this.id = id;
        this.etab = etab;
        this.user = user;
        this.description = description;
    }

    public Partenariat(int etab, int user, String description) {
        this.etab = etab;
        this.user = user;
        this.description = description;
    }

    public Partenariat(int etab, String description) {
        this.etab = etab;
        this.description = description;
    }
}
