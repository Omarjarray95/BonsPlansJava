/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author DADOU
 */
public class Reclamation {
   
public int id_rec;
public int id_user;
public int id_etablissement;
 public String description;

public Reclamation() {
}

public Reclamation(int id_rec, int id_user, int id_etablissement, String description) {
    this.id_rec = id_rec;
    this.id_user = id_user;
    this.id_etablissement = id_etablissement;
    this.description = description;
}
public Reclamation(String description,int id_etablissement) {
    this.id_etablissement = id_etablissement;
    this.description = description;
}



public int getId_rec() {
    return id_rec;
}

public void setId_rec(int id_rec) {
    this.id_rec = id_rec;
}

public int getId_user() {
    return id_user;
}

public void setId_user(int id_user) {
    this.id_user = id_user;
}

public int getId_etablissement() {
    return id_etablissement;
}

public void setId_etablissement(int id_etablissement) {
    this.id_etablissement = id_etablissement;
}

public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}

@Override
public String toString() {
    return "Reclamation{" + "id_rec=" + id_rec + ", id_user=" + id_user + ", id_etablissement=" + id_etablissement + ", description=" + description + '}';
}



    
    
   

    
    
    
    
}
