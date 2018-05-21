/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Maissa
 */
public class Rating {
    public int id; 
    public float rate;
    public int id_etab; 
    
    public Rating(){
        
    }

    public Rating(int id, float rate, int id_etab) {
        this.id = id;
        this.rate = rate;
        this.id_etab = id_etab;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getId_etab() {
        return id_etab;
    }

    public void setId_etab(int id_etab) {
        this.id_etab = id_etab;
    }
    
    
    
    
    
    
    
    
}
