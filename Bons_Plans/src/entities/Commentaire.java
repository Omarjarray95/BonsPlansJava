/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Maissa
 */
public class Commentaire {
    
    private int id;
    private String comment;
    private int id_etab;
    private String created;
    private String photo;
    

    public Commentaire() {
    }

    public Commentaire(int id, String comment,int id_etab,String created) {
        this.id = id;
        this.comment = comment;
        this.id_etab=id_etab;
        this.created=created;
        
    }
    
    public Commentaire(int id, String comment,int id_etab,String created,String photo) {
        this.id = id;
        this.comment = comment;
        this.id_etab=id_etab;
        this.created=created;
        this.photo=photo;
        
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId_etab() {
        return id_etab;
    }

    public void setId_etab(int id_etab) {
        this.id_etab = id_etab;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
    
    
}
